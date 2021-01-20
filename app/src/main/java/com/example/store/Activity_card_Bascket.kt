package com.example.store

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.store.Adapters.adapter_items_card_bascket
import com.example.store.Dialog.Dial_App
import com.example.store.Main_Fragments.ErrorCode500
import com.example.store.Main_Fragments.Login
import com.example.store.Models.RESPONSCARD
import com.example.store.Models.data_card
import com.example.store.Models.model_Item
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity__second__bascket.*
import kotlinx.android.synthetic.main.activity_card__bascket.*
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class Activity_card_Bascket : BaseActiity() {
    var ad_card:adapter_items_card_bascket ? =null
    var Flag:Boolean=false
    var data_card: data_card?=null
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card__bascket)
        Log.i("kvnsndvsdvnfnv",token)
        data_card=data_card()
        ad_card= adapter_items_card_bascket(this)
        DialLoad()
        recy_card.adapter=ad_card
                ad_card?.click(object : Adapter_discounts.Data_dis {
                    override fun Data(I: Int, ID: String, Pos: Int) {
                        if (!isNetConnected())
                        {
                            var I=2;
                            var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object :Dial_App.Interface_new{
                                override fun News() {

                                }
                            },this@Activity_card_Bascket)
                            p.show()
                            return
                        }
                        Log.i("kvnsndv", I.toString())
                        Log.i("dvkmdsvbksdbvdv",ID)
                        Log.i("dvkmdsvbksdbvdv", I.toString())
                        AddEditDeleteItem_Card(I,ID,object :Resuilt{
                            override fun Data(i: Int, S: String, B: Boolean) {
                                if (B)
                                {
                                    Log.i("svdnjdnva","Done")
                                    Flag=true
                                   GetCard("")
                                }
                            }

                        })
                    }

                })
        linearLayout5.setOnClickListener {
            Flag=true
            var i=Intent()
            if (ad_card?.V==null)
            {
                Log.i("vnjfnjnfbjfbs","A")
            }else{
                Log.i("vnjfnjnfbjfbs","B")
            }
            Log.i("vnjfnjnfbjfbs",ad_card?.V?.size.toString())
            i.putExtra("data",ad_card?.V)
            setResult(RESULT_OK,i)
            finish()
        }
        GetCard("")




        btn_send.setOnClickListener {
            var I=Intent(this,Activity_Second_Bascket::class.java)
            I.putExtra("data",data_card)
            startActivity(I)
        }
    }
    fun  GetCard(S:String)
    {
        var body=RequestBody.create(MediaType.parse("text/pain"),S)
        var req=api?.GETCARD("Bearer " + token,body)
        req?.enqueue(object : Callback<RESPONSCARD> {
            override fun onResponse(call: Call<RESPONSCARD>, response: Response<RESPONSCARD>) {
                Dial_Close()
                Log.i("HHKJKILOGPN",response.code().toString())
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
                    Log.i("dvmsvkmsmkvv", code500?.getMessage().toString())
                    Toast.makeText(
                            this@Activity_card_Bascket,
                            "" + code500?.getMessage(),
                            Toast.LENGTH_LONG
                    ).show()
                    return
                }
                if (response.isSuccessful)
                {
                    data_card=response.body()?.data
                    ad_card?.V?.clear()
                    ad_card?.V=response.body()?.data?.orderItems
                    ad_card?.notifyDataSetChanged()
                    if (response.body()?.data?.orderItems!=null)
                    {
                        textView12.setText(response.body()?.data?.orderItems!!.size.toString())
                    }else{
                        textView12.setText("0")
                    }















                    var data=response.body()
                    if (data?.data?.pricePayForShow!=null)
                    {
                        textView14.setText(data.data?.pricePayForShow)
                    }else{
                        textView14.setText(" 0 تومان ")
                    }



                    if (data?.data?.discountPrice!=null)
                    {
                        if (data.data?.discountPrice!! >0)
                        {
                            textView16.setText(data.data?.discountPrice!!.toString())
                        }else{
                            textView16.setText("0 تومان")
                        }
                    }else{
                        textView16.setText("0 تومان")
                    }




                    if (data?.data?.pricePayForShow!=null)
                    {
                        textView18.setText(data.data?.pricePayForShow)
                    }else{
                        textView18.setText(" 0 تومان ")
                    }










                }
                if (response.code()==401)
                {
                    Login(securityKey,object : Login {
                        override fun onLoginCompleted(success: Boolean) {
                            if (success)
                            {
                                GetCard(S)
                            }
                        }

                    })
                }
            }
            override fun onFailure(call: Call<RESPONSCARD>, t: Throwable) {
                   Dial_Close()
                    Log.i("toldmsfgth","1")
                    var I=2;
                    var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object : Dial_App.Interface_new{
                        override fun News() {
                            finish()
                        }
                    },this@Activity_card_Bascket)
                    p.show()


            }

        })
    }


    override fun onBackPressed() {
        if (Flag)
        {
            var i=Intent()
            i.putExtra("data",ad_card?.V)
            setResult(RESULT_OK,i)
        }
        super.onBackPressed()
    }
    }
