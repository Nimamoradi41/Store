package com.example.store.Main_Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.store.Adapters.adapter_list_order
import com.example.store.Dialog.Dial_App
import com.example.store.Models.ResponseOrder
import com.example.store.Models.data_Order
import com.example.store.Models.orderItems
import com.example.store.R
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import kotlinx.android.synthetic.main.fragment_blankrf.view.*
import kotlinx.android.synthetic.main.fragment_frag__myaccunt.view.*
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import javax.security.auth.callback.Callback

class Frag_list_orders : BaseFragment() {
  var Ad_adapter:adapter_list_order ?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        var V=inflater.inflate(R.layout.fragment_blankrf, container, false)
        var r=ArrayList<data_Order>()
        Ad_adapter= adapter_list_order(context!!,r)
        V.recy_list_order.adapter=Ad_adapter
        GetOrder(V)
        return  V
    }


    fun  GetOrder(V:View)
    {
        var req=api?.GetOrder("Bearer " +token)

        req?.enqueue(object  : retrofit2.Callback<ResponseOrder> {
            override fun onResponse(call: Call<ResponseOrder>, response: Response<ResponseOrder>) {
                Log.i("lmbsmbsd", response.code().toString())
                if (response.code()==401)
                {
                    Login(securityKey,object : Login {
                        override fun onLoginCompleted(success: Boolean) {
                            if (success)
                            {
                                GetOrder(V)
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


                    Log.i("lmbsmbsd", response.body()?.data?.size!!.toString())
                    Ad_adapter?.list= response.body()?.data!!
                    Ad_adapter?.notifyDataSetChanged()

                    if (response.body()?.data!=null)
                    {
                        if (response.body()?.data?.size==0)
                        {
                            V.no_item_Card.visibility=View.VISIBLE
                        }else{
                            V.no_item_Card.visibility=View.GONE
                        }

                    }else{
                        V.no_item_Card.visibility=View.VISIBLE
                    }

                }
            }

            override fun onFailure(call: Call<ResponseOrder>, t: Throwable) {
                Log.i("fkvskfnb", t.message.toString())
                Dial_Close()
                var I=3
                var p=   Dialapp(I,"لطفا دوباره تلاش کنید",object : Dial_App.Interface_new{
                    override fun News() {
                         GetOrder(V)
                    }
                }, context!!)
                p.show()
            }

        })
    }


}