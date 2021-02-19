package com.example.store.Main_Fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.store.*
import com.example.store.Adapters.adapter_Spacial_4
import com.example.store.Dialog.Dial_App
import com.example.store.Models.GetProductModel
import com.example.store.Models.ResponseMoreData
import com.example.store.Models.data_accses
import com.example.store.VIEWMODEL.MainActivityViewModel
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import kotlinx.android.synthetic.main.fragment_frag__a_l_l.view.*
import kotlinx.android.synthetic.main.fragment_frag__all__all.view.*
import kotlinx.android.synthetic.main.fragment_frag__under__cate.view.*
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class Frag_ALL_Dis : BaseFragment() {
    var ad_mains: adapter_Spacial_4?= null
    var  products:discounts ?=null
    var page=1
    var Type=-1
    var CateId=""
    var  Model: GetProductModel ?=null
    var modelmain: MainActivityViewModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        products= arguments?.getSerializable("data2") as discounts
        Type= arguments?.getInt("TYpe",-1)!!

        if (Type==1)
        {
            CateId= arguments?.getString("catId").toString()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       var V=inflater.inflate(R.layout.fragment_frag__a_l_l, container, false)
        V.recy_itemsss.layoutManager=GridLayoutManager(activity,2)
        SetCate_2(V, products?.products!!)
        V.linearLayout5896.setOnClickListener {
            activity?.finish()
        }
        Model= GetProductModel()
        modelmain = ViewModelProviders.of(activity!!)[MainActivityViewModel::class.java]
        modelmain?.change_Data?.observe(activity!!, object : Observer<data_accses> {
            override fun onChanged(t: data_accses?) {
                Log.i("svmsnbsbnhn", t?.Pos.toString())
                Log.i("svmsnbsbnhn", t?.v?.count.toString())
                var vc = ad_mains?.list?.get(t?.Pos!!)
                var f=t?.v?.currentReserved
                vc?.currentReserved = f!!
                Log.i("svmsnbsbnhn", f.toString())
                ad_mains?.list?.set(t?.Pos!!, vc!!)
                ad_mains?.notifyItemChanged(t?.Pos!!)
            }
        })
        V.recy_itemsss.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView,dx,dy)
                val linearLayoutManager = recyclerView.getLayoutManager() as LinearLayoutManager
                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == ad_mains?.list?.size!! - 1) {
                    if (ad_mains?.loadmore!!) {
                        Getdata(0)
                    }
                }
            }

        })
        return V
    }
    private fun SetCate_2(v: View,list:ArrayList<products>) {
        val dm = DisplayMetrics()
        activity?.getWindowManager()?.getDefaultDisplay()?.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.widthPixels
        ad_mains= adapter_Spacial_4(activity!!,list,width)
        v.rootView.recy_itemsss.adapter=ad_mains
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
                            var v=ad_mains?.list?.get(Pos)
                            v?.currentReserved=I
                            ad_mains?.list?.set(Pos, v!!)
                            ad_mains?.notifyItemChanged(Pos)
                            var vs= Intent()
                            MultyActivity_2.Count=i
//                            vs.putExtra("count",i)
//                            activity?.setResult(Activity.RESULT_OK,vs)
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
            var a=ArrayList<String>()
            a.add(CateId)
            Model?.CategoryIds=a
        }
        page=page+1
        Model?.type=Type
        var body_page=RequestBody.create(MediaType.parse("text/plain"),page.toString())
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