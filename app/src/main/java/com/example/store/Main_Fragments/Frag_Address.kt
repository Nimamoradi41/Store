package com.example.store.Main_Fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.store.Adapters.adapter_address
import com.example.store.Dialog.Dial_App
import com.example.store.MapActivity
import com.example.store.ModelAddress
import com.example.store.Models.RESPONSADRESS
import com.example.store.Models.ResponDelAddress
import com.example.store.R
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import kotlinx.android.synthetic.main.fragment_frag__address.*
import kotlinx.android.synthetic.main.fragment_frag__address.view.*
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class Frag_Address : BaseFragment() {
    var ad_address: adapter_address?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_frag__address, container, false)
        ad_address= adapter_address(activity!!)
        v.rect_address.adapter=ad_address



        ad_address?.DATA(object : adapter_address.data_Type{
            override fun Del(S: String,Pos:Int) {
                if (!isNetConnected())
                {
                    Dial_Close()
                    var I=2;
                    var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object : Dial_App.Interface_new{
                        override fun News() {

                        }
                    }, context!!)
                    p.show()
                    return
                }else{
                    var p=   Dialog_Ask(6,"آدرس حذف بشود؟",object : Dial_App.Interface_new_2{
                        override fun News(s: String) {
                             if (s.equals("A"))
                             {
                                 Del_Address(S,Pos,v)
                             }
                        }

                    }, context!!)
                    p.show()

                }

            }

            override fun Edit(S: ModelAddress, Pos: Int) {
                var I=Intent(activity, MapActivity::class.java)
                I.putExtra("Type","B")
                I.putExtra("DATA_2",S)
                I.putExtra("Pos",Pos)
                startActivityForResult(I,1457)
            }

        })


        v.cvdgnfh.setOnClickListener {
            var I=Intent(activity,MapActivity::class.java)
            I.putExtra("Type","A")
            startActivityForResult(I,14151)
        }



        GetAddress(v)
        return v
    }
    fun GetAddress(v:View)
    {
        DialLoad()
        var req=api?.GETADDRESS("Bearer " +token)
        req?.enqueue(object : retrofit2.Callback<RESPONSADRESS> {
            override fun onResponse(call: Call<RESPONSADRESS>, response: Response<RESPONSADRESS>) {
                Dial_Close()
                Log.i("svnskvnavb", response.code().toString())
                if (response.code()==401)
                {
                    Login(securityKey,object : Login {
                        override fun onLoginCompleted(success: Boolean) {
                            if (success)
                            {
                                GetAddress(v)
                            }
                        }

                    })
                }
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
                            context,
                            "" + code500?.getMessage(),
                            Toast.LENGTH_LONG
                    ).show()
                    Log.i("knknvsz", code500?.getMessage().toString())
                    return
                }
                if (response.isSuccessful)
                {
                    Log.i("svnskvnavb", response.code().toString())
                    if (response.body()?.data==null)
                    {
                        v.no_item_Card.visibility=View.VISIBLE
                    }else{
                        if (response.body()?.data?.size==0)
                        {
                            v.no_item_Card.visibility=View.VISIBLE
                        }else{
                            v.no_item_Card.visibility=View.GONE
                            ad_address?.list=response.body()?.data
                            ad_address?.notifyDataSetChanged()
                        }

                    }


                }
            }

            override fun onFailure(call: Call<RESPONSADRESS>, t: Throwable) {
                Dial_Close()
                var I=3
                var p=   Dialapp(I,"لطفا دوباره تلاش کنید",object : Dial_App.Interface_new{
                    override fun News() {
                       activity?.finish()
                    }
                }, context!!)
                p.show()
            }

        })
    }
    public fun Del_Address(ID:String,i:Int,V:View)
    {
        DialLoad()
        var Body= RequestBody.create(MediaType.parse("text/plain"),ID)
        var req=api?.DeleteAddress("Bearer " +token,Body)
        req?.enqueue(object  : retrofit2.Callback<ResponDelAddress> {
            override fun onResponse(call: Call<ResponDelAddress>, response: Response<ResponDelAddress>) {
                Dial_Close()
                if (response.code()==401)
                {
                    Login(securityKey,object : Login {
                        override fun onLoginCompleted(success: Boolean) {
                            if (success)
                            {
                                Del_Address(ID,i,V)
                            }
                        }

                    })
                }
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
                            context,
                            "" + code500?.getMessage(),
                            Toast.LENGTH_LONG
                    ).show()
                    Log.i("knknvsz", code500?.getMessage().toString())
                    return
                }

                if (response.isSuccessful)
                {
                    ad_address?.list?.removeAt(i)
                    ad_address?.notifyItemRemoved(i)
                    ad_address?.notifyItemRangeChanged(0, ad_address?.list?.size!!)
                    if (ad_address?.list?.size==0)
                    {
                        V.no_item_Card.visibility=View.VISIBLE
                    }else{
                        V.no_item_Card.visibility=View.GONE
                    }
                }
            }

            override fun onFailure(call: Call<ResponDelAddress>, t: Throwable) {
                Dial_Close()
                var I=2;
                var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object : Dial_App.Interface_new{
                    override fun News() {

                    }
                }, context!!)
                p.show()
            }

        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==14151)
        {
            if (resultCode==Activity.RESULT_OK)
            {
                no_item_Card.visibility=View.GONE
                var v= ModelAddress()
                v= data?.getSerializableExtra("data") as ModelAddress
                ad_address?.list?.add(v)
                ad_address?.notifyItemInserted(ad_address?.list?.size!! - 1)
            }
        }
        if (requestCode==1457)
        {
            if (resultCode==Activity.RESULT_OK)
            {
                no_item_Card.visibility=View.GONE
                var v= ModelAddress()
                var p=data?.getIntExtra("pos",-8)
                v= data?.getSerializableExtra("data") as ModelAddress
                ad_address?.list?.remove(p)
                ad_address?.list?.set(p!!,v)
                ad_address?.notifyItemChanged(p!!)
//                ad_address?.list?.add(v)
//                ad_address?.notifyItemInserted(ad_address?.list?.size!! - 1)
            }
        }
    }


}