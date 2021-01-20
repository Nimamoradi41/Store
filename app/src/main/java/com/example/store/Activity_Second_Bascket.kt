package com.example.store

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.store.Adapters.Adapter_second_bascket
import com.example.store.Dialog.Dial_App
import com.example.store.Main_Fragments.ErrorCode500
import com.example.store.Main_Fragments.Login
import com.example.store.Models.RESPONSADRESS
import com.example.store.Models.data_card
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity__second__bascket.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_frag__address.*
import kotlinx.android.synthetic.main.fragment_frag__address.view.*
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class Activity_Second_Bascket : BaseActiity() {
    var adapter_address:Adapter_second_bascket ?=null
    var adapter_address_marsole:Adapter_second_bascket_marsole ?=null
    var data_card: data_card?=null
    var address:ModelAddress ?=null
    var Pos=0;
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__second__bascket)
        data_card= data_card()
        address=ModelAddress()
        data_card= intent.getSerializableExtra("data") as data_card?
        GetAddress()
        recyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true)
        adapter_address_marsole=Adapter_second_bascket_marsole(data_card?.orderItems!!,this)
        recyclerView.adapter=adapter_address_marsole


        if (data_card?.deliveryTimeMessage!=null)
        {
            checkBox2.setText(data_card?.deliveryTimeMessage)
        }

        btn_send_2.setOnClickListener {

        }


        imageView47.setOnClickListener {
            var I= Intent(this,MultyActivity_2::class.java)
            I.putExtra("Type","Map2")
            I.putExtra("Pos",Pos)
            I.putExtra("address",address)
            startActivityForResult(I,14151)
        }
        goo.setOnClickListener {
            var I= Intent(this,MultyActivity_2::class.java)
            I.putExtra("Type","Map2")
            I.putExtra("Pos",Pos)
            I.putExtra("address",address)
            startActivityForResult(I,14151)
        }
        imageView45.setOnClickListener {
            var I=Intent(this,MultyActivity_2::class.java)
            I.putExtra("Type","Map2")
            I.putExtra("Pos",Pos)
            I.putExtra("address",address)
            startActivityForResult(I,14151)
        }
    }
    fun  Checkout()
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
                                Checkout()
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
//                        recy_itemaddress.visibility=View.GONE
                    }else{
                        if (response.body()?.data?.size==0)
                        {
//                            recy_itemaddress.visibility=View.GONE
                        }else{
                            Log.i("svnskvnavb", response.body()?.data?.size?.toString().toString())
                            adapter_address?.items=response.body()?.data
                            adapter_address?.notifyDataSetChanged()
                        }

                    }


                }
            }

            override fun onFailure(call: Call<RESPONSADRESS>, t: Throwable) {
                Dial_Close()
                var I = 2;
                var p = Dialapp(
                        2,
                        "اتصال خود را به اینترنت بررسی کنید",
                        object : Dial_App.Interface_new {
                            override fun News() {

                            }
                        },
                        this@Activity_Second_Bascket
                )
                p.show()
            }

        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==14151)
        {
            if (resultCode== Activity.RESULT_OK)
            {
//                recy_itemaddress.visibility=View.VISIBLE
                var v= ModelAddress()
                v= data?.getSerializableExtra("data") as ModelAddress
                address=v
                Log.i("fbgnhscdv",v.fullLocation.toString())
                Pos=data.getIntExtra("Pos",-1)
                textView65.setText(v.fullLocation.toString())
//               adapter_address?.items?.add(v)
//                adapter_address?.notifyItemInserted(adapter_address?.items?.size!! - 1)
            }
        }
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
//                        recy_itemaddress.visibility=View.GONE
                    }else{
                        if (response.body()?.data?.size==0)
                        {
//                            recy_itemaddress.visibility=View.GONE
                        }else{
                            Log.i("svnskvnavb", response.body()?.data?.size?.toString().toString())
//                            adapter_address?.items=response.body()?.data
//                            adapter_address?.notifyDataSetChanged()
                            address=response.body()?.data?.get(0)
                            textView65.setText(response.body()?.data?.get(0)?.fullLocation.toString())
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