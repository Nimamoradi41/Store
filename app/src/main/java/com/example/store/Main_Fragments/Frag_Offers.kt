package com.example.store.Main_Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.store.Dialog.Dial_App
import com.example.store.Models.ResGetDiscounts
import com.example.store.Models.ResPonseProfile
import com.example.store.R
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import kotlinx.android.synthetic.main.fragment_frag__myaccunt_2.view.*
import kotlinx.android.synthetic.main.fragment_frag__offers.view.*
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class Frag_Offers : BaseFragment() {

    var ad: Adapter_Offer? = null
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_frag__offers, container, false)
        ad = Adapter_Offer(context!!,v.holder_54)
        v.recy_offers.adapter = ad
        if (isNetConnected())
        {
            ResGetDiscounts(v)
        }else{
            var I = 2;
            var p = Dialapp(
                    2,
                    "اتصال خود را به اینترنت بررسی کنید",
                    object : Dial_App.Interface_new {
                        override fun News() {
                            activity?.finish()
                        }
                    },
                    context!!
            )
            p.show()
        }
        return v
    }


    fun ResGetDiscounts(V: View) {
        DialLoad()
        var req = api?.GetDiscounts("Bearer " + token)
        req?.enqueue(object : retrofit2.Callback<ResGetDiscounts> {
            override fun onResponse(call: Call<ResGetDiscounts>, response: Response<ResGetDiscounts>) {
                Log.i("fkvskfnb", response.code().toString())
                Dial_Close()
                if (response.code() == 401) {
                    Login(securityKey, object : Login {
                        override fun onLoginCompleted(success: Boolean) {
                            if (success) {
                                ResGetDiscounts(V)
                            }
                        }

                    })
                }
                if (response.code() == 500) {
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
                if (response.isSuccessful) {
                    Dial_Close()
                   if (response.body()?.data!=null)
                   {

                       if (response.body()?.data!!.size==0)
                       {
                           V.no_item_Card.visibility=View.VISIBLE
                       }else{
                           V.no_item_Card.visibility=View.GONE
                           ad?.list=response.body()?.data
                           ad?.notifyDataSetChanged()
                       }
                   }else{
                       V.no_item_Card.visibility=View.VISIBLE
                   }
                }
            }


            override fun onFailure(call: Call<ResGetDiscounts>, t: Throwable) {
                Log.i("fkvskfnb", t.message.toString())
                Dial_Close()
                var I = 3
                var p = Dialapp(I, "لطفا دوباره تلاش کنید", object : Dial_App.Interface_new {
                    override fun News() {
                        activity?.finish()
                    }
                }, activity!!)
                p.show()
            }

        })

    }
}