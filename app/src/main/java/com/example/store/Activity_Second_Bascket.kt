package com.example.store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.store.Adapters.Adapter_second_bascket
import com.example.store.Dialog.Dial_App
import com.example.store.Main_Fragments.ErrorCode500
import com.example.store.Main_Fragments.Login
import com.example.store.Models.RESPONSADRESS
import com.example.store.Models.data_card
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import kotlinx.android.synthetic.main.activity__second__bascket.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_frag__address.view.*
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class Activity_Second_Bascket : BaseActiity() {
    var adapter_address:Adapter_second_bascket ?=null
    var data_card: data_card?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__second__bascket)
        data_card= data_card()
        data_card= intent.getSerializableExtra("data") as data_card?
        adapter_address=Adapter_second_bascket();
//        recy_itemaddress.adapter=adapter_address

        GetAddress()



    }
    fun GetAddress()
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
                                GetAddress()
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
                        this@Activity_Second_Bascket,
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
                        recy_itemaddress.visibility=View.GONE
                    }else{
                        if (response.body()?.data?.size==0)
                        {
                            recy_itemaddress.visibility=View.GONE
                        }else{
                            Log.i("svnskvnavb", response.body()?.data.toString())
                            adapter_address?.items=response.body()?.data
                        }

                    }


                }
            }

            override fun onFailure(call: Call<RESPONSADRESS>, t: Throwable) {
                Dial_Close()
                var I=3
                var p=   Dialapp(I,"لطفا دوباره تلاش کنید",object : Dial_App.Interface_new{
                    override fun News() {
                        finish()
                    }
                }, this@Activity_Second_Bascket)
                p.show()
            }

        })
    }
}