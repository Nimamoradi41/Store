package com.example.store.Main_Fragments

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.store.Adapter_discounts
import com.example.store.Adapters.adapter_Spacial_4
import com.example.store.Dialog.Dial_App
import com.example.store.Models.GetProductModel
import com.example.store.Models.ResponseMoreData
import com.example.store.R
import com.example.store.Resuilt
import com.example.store.products
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import kotlinx.android.synthetic.main.fragment_frag__a_l_l.view.*
import kotlinx.android.synthetic.main.fragment_frag__a_l_l.view.recy_itemsss
import kotlinx.android.synthetic.main.fragment_frag__all__all.view.*
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class Frag_All_All : BaseFragment() {

    var ad_mains: adapter_Spacial_4?= null
    var pro: ArrayList<products>?= null
    var page=1
    var Type=-1
    var CateId=""
    var  Model: GetProductModel ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Type= arguments?.getInt("t",-8)!!
        pro= arguments?.getSerializable("data_2") as  ArrayList<products>?
        if (Type==1)
        {
            CateId= arguments?.getString("cateid").toString()
        }


    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       var V=inflater.inflate(R.layout.fragment_frag__all__all, container, false)
        V.recy_itemsall_334.layoutManager= GridLayoutManager(activity,2)
//        SetCate_2(V, pro!!)
        SetCate_2(V, pro!!)
        Model= GetProductModel()
        V.recy_itemsall_334.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView,dx,dy)
                val linearLayoutManager = recyclerView.getLayoutManager() as LinearLayoutManager
                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == ad_mains?.list?.size!! - 1) {
                    if (ad_mains?.loadmore!!) {
                        Getdata(155)
                    }
                }
            }

        })
        return V
    }
    private fun SetCate_2(v: View,list:ArrayList<products>) {
        ad_mains= adapter_Spacial_4(activity!!, list)
        v.recy_itemsall_334.adapter=ad_mains
        ad_mains?.click(object : Adapter_discounts.Data_dis {
            override fun Data(I: Int, ID: String, Pos: Int) {

                if (!isNetConnected())
                {
                    var I=2;
                    var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object : Dial_App.Interface_new{
                        override fun News() {

                        }
                    }, context!!)
                    p.show()
                    return
                }
                Log.i("kvnsndv", I.toString())
                AddEditDeleteItem_Card(I,ID,object : Resuilt {
                    override fun Data(i: Int, S: String, B: Boolean) {
                        Log.i("kvnsndv", "ASA")
                        if (B)
                        {
                            activity?.setResult(RESULT_OK)
                            var v=ad_mains?.list?.get(Pos)
                            v?.currentReserved=I
                            ad_mains?.list?.set(Pos, v!!)
                            ad_mains?.notifyItemChanged(Pos)
//                            MainActivity.mainActivityViewModel?.count?.value=i
                        }
                    }

                })
            }

        })
    }


    public fun Getdata(Order:Int)
    {
        DialLoad()
        var Order_Body=RequestBody.create(MediaType.parse("text//plain"),"")
        if (Type==1)
        {
//            Model?.CategoryId=CateId
            var D=ArrayList<String>()
            D.add(CateId)
            Model?.CategoryIds=D
        }
        page=page+1
        Model?.type=Type
        var body_page= RequestBody.create(MediaType.parse("text/plain"),page.toString())
        var req=api?.GetMoreData("Bearer " +token,Model,body_page,Order_Body)
        req?.enqueue(object  : retrofit2.Callback<ResponseMoreData> {
            override fun onResponse(call: Call<ResponseMoreData>, response: Response<ResponseMoreData>) {
                Dial_Close()
                Log.i("dvmksvakvd", response.code().toString())
                if (response.code() == 500)
                {
                    var code500: ErrorCode500? = null
                    val gson = Gson()
                    val adapter: TypeAdapter<ErrorCode500> =
                            gson.getAdapter(ErrorCode500::class.java)
                    try {
                        if (response.errorBody() != null) code500 = adapter.fromJson(
                                response.errorBody()!!.string()
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    Toast.makeText(
                            activity,
                            "" + code500?.getMessage(),
                            Toast.LENGTH_LONG
                    ).show()
                    return
                }
                if (response.code()==401)
                {
                    Login(securityKey,object :Login{
                        override fun onLoginCompleted(success: Boolean) {
                            if (success)
                            {
                                Getdata(Order)
                            }
                        }

                    })
                }

                if (response.isSuccessful)
                {
                    ad_mains?.loadmore= response.body()?.data?.moreDate!!
                    ad_mains?.list?.addAll(response.body()?.data?.products!!)
                    ad_mains?.notifyItemRangeChanged(0, ad_mains?.list?.size!!)
                }
            }

            override fun onFailure(call: Call<ResponseMoreData>, t: Throwable) {
                Dial_Close()
                page=page-1
                var I=2;
                var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object :Dial_App.Interface_new{
                    override fun News() {

                    }
                }, context!!)
                p.show()


            }

        })
    }


}