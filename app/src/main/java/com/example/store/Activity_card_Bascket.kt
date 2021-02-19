package com.example.store
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
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
    var adddress:ModelAddress?=null
    var data_card: data_card?=null
    companion object{
        var act:Activity?=null
    }
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card__bascket)
        Log.i("kvnsndvsdvnfnv",token)
        data_card=data_card()
        act=this
        ad_card= adapter_items_card_bascket(this)
        DialLoad()
        recy_card.layoutManager=LinearLayoutManager(this)
        recy_card.adapter=ad_card
        recy_card.setNestedScrollingEnabled(false)
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

            var I= Intent(this,MultyActivity_2::class.java)
            I.putExtra("Type","Map2")
            I.putExtra("Type_2","A")
//            I.putExtra("Pos",Pos)
//            I.putExtra("address",address)
            startActivity(I)
            finish()

//            if (adddress?.id.isNullOrEmpty())
//            {
//                var I= Intent(this,MultyActivity_2::class.java)
//                I.putExtra("Type","Map2")
////            I.putExtra("Pos",Pos)
////            I.putExtra("address",address)
//                startActivityForResult(I,14151)
//            }else{
//                Checkout(adddress?.id.toString(),"")
//            }

//            var I=Intent(this,Activity_Second_Bascket::class.java)
//            I.putExtra("data",data_card)
//            startActivity(I)
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
                    Log.i("hagasdgy", response.body().toString())
                    data_card=response.body()?.data
                    ad_card?.V?.clear()
                    if (data_card?.deliveryPriceForShow!=null)
                    {
                        textView96_8959.setText(data_card?.deliveryPriceForShow)
                    }


                    ad_card?.V=response.body()?.data?.orderItems
                    ad_card?.notifyDataSetChanged()
                    textView9751.setText(data_card?.orderItems?.size.toString())
                    if (!data_card?.deliveryPriceForShow.isNullOrEmpty())
                    {
                        textView96_8959.setText(data_card?.deliveryPriceForShow)
                    }


                    if (response.body()?.data?.orderItems!=null)
                    {
                        if (response.body()?.data?.orderItems?.size==0)
                        {
                            var i=Intent()
                            i.putExtra("data",ad_card?.V)
                            setResult(RESULT_OK,i)
                            finish()
                        }
                    }else{
                        var i=Intent()
                        i.putExtra("data",ad_card?.V)
                        setResult(RESULT_OK,i)
                        finish()
                    }


//                    if (response.body()?.data?.orderItems!=null)
//                    {
//                        textView12.setText(response.body()?.data?.orderItems!!.size.toString())
//                    }else{
//                        textView12.setText("0")
//                    }


















                    var data=response.body()
                    if (!data?.data?.priceForShow.isNullOrEmpty())
                    {
                        textView14_2.setText(data?.data?.priceForShow)
                    }else{
                        textView14_2.setText(" 0 تومان ")
                    }



                    Log.i("cvmnxbv", data?.data?.discountPriceForShow.toString())

                        if (!data?.data?.discountPriceForShow.isNullOrEmpty())
                        {

                            textView16_2.setText(data?.data?.discountPriceForShow!!.toString())
                        }else{
                            textView16_2.setText("0 تومان")
                        }






                    if (data?.data?.pricePayForShow!=null)
                    {
//                        textView18_2.setText(data.data?.pricePayForShow)
                        dveifoh.setText(data.data?.pricePayForShow)
                    }else{
//                        textView18_2.setText(" 0 تومان ")
                        dveifoh.setText(" 0 تومان ")
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==14151)
        {
            if (resultCode==Activity.RESULT_OK)
            {
                adddress=data?.getSerializableExtra("data") as ModelAddress
                Log.i("adlvmasv", adddress?.id.toString())
                Log.i("adlvmasv", adddress?.fullLocation.toString())
                Checkout(adddress?.id.toString(),"")
            }
        }
    }

    fun  Checkout( AddressId:String, DiscountCode:String)
    {
        DialLoad()
        var model= Model_Checkout()
        model.AddressId=AddressId
        model.DiscountCode=DiscountCode
        Log.i("dvssnvknds",AddressId)
        Log.i("dvssnvknds",DiscountCode)
        var req=api?.Checkout("Bearer "+token,model)
        req?.enqueue(object : retrofit2.Callback<RESPONSCARD> {
            override fun onResponse(call: Call<RESPONSCARD>, response: Response<RESPONSCARD>) {
                Dial_Close()
                Log.i("qerewrrt", response.code().toString())
                if (response.code()==401)
                {
                    Login(securityKey,object : Login {
                        override fun onLoginCompleted(success: Boolean) {
                            if (success)
                            {
                                Checkout(AddressId,DiscountCode)
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
                            this@Activity_card_Bascket,
                            "" + code500?.getMessage(),
                            Toast.LENGTH_LONG
                    ).show()
                    Log.i("knknvsz", code500?.getMessage().toString())
                    return
                }
                if (response.isSuccessful)
                {
                    var i=Intent(this@Activity_card_Bascket,Activity_Second_Bascket::class.java)
                    i.putExtra("data",response.body()?.data)
                    i.putExtra("id_address",adddress?.id)
                    startActivity(i)
//                    zarinPalPayment(response.body()?.data?.pricePay?.toLong()!!, response.body()?.data?.id.toString())
//                    zarinPalPayment(response.body()?.data?.pricePay?.toLong()!!,response.body()?.data?.id.toString())
                }
            }

            override fun onFailure(call: Call<RESPONSCARD>, t: Throwable) {
                Dial_Close()
                var I = 2;
                var p = Dialapp(
                        2,
                        "اتصال خود را به اینترنت بررسی کنید",
                        object : Dial_App.Interface_new {
                            override fun News() {

                            }
                        },
                        this@Activity_card_Bascket
                )
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
