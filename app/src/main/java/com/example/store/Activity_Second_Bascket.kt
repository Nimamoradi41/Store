package com.example.store

import android.R
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.store.Adapters.Adapter_second_bascket
import com.example.store.Dialog.Dial_App
import com.example.store.Main_Fragments.ErrorCode500
import com.example.store.Main_Fragments.Login
import com.example.store.Models.*
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.zarinpal.ewallets.purchase.ZarinPal
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity__pass_word.*
import kotlinx.android.synthetic.main.activity__second__bascket.*
import kotlinx.android.synthetic.main.activity_card__bascket.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_frag__address.*
import kotlinx.android.synthetic.main.fragment_frag__address.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.security.AccessController.getContext


class Activity_Second_Bascket : BaseActiity() {
    var adapter_address:Adapter_second_bascket ?=null
    var id_address=""
    var adapter_address_marsole:Adapter_second_bascket_marsole ?=null
    var data_card: data_card?=null
    var address:ModelAddress ?=null
    var Pos=0;
    var off=""
    companion object{
        var act:Activity?=null
    }
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.store.R.layout.activity__second__bascket)
        act =this
        recyclerView.isNestedScrollingEnabled=false
        data_card=data_card()


//            data_card=intent.getSerializableExtra("data") as data_card
            address= (intent.getSerializableExtra("data_1") as ModelAddress?)!!
            id_address= address?.id.toString()
            Log.i("agvsgvagvc", id_address)
            Checkout_main(id_address?.toString(), off)





        linearLayout66.setOnClickListener {
            finish()
        }
//        textView9751_4_5.setText(data_card?.orderItems?.size.toString())
        btn_offer.setOnClickListener {
            if (txt_fff.text.isNullOrEmpty())
            {
                Toast.makeText(this, "کد تخفیف را وارد کنید", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Log.i("vksnvnkdndnvk", txt_fff.text.trim().toString())
            var vs=txt_fff.text
         Off(vs.toString())
        }





        var paymentData = intent.data
        ZarinPal.getPurchase(this)
                .verificationPayment(paymentData)
                { isPaymentSuccess, refID, paymentRequest ->
//                    getFromSP()

//                    txt_discount_price.text = discountPriceForShow
//                    txt_total_price.text = priceForShow
//                    txt_pay_price.text = pricePayForShow
//                    txt_total_count.text = totalCount.toString()
//                    clearCheckOutInfo()
                    if (isPaymentSuccess) {
                        Log.i("svsmbsddsv", "Ok")
                        DialLoad()
                  var dd=PaymentModel(
                      100, refID, paymentRequest.authority,
                      paymentRequest.email,
                      paymentRequest.mobile,
                      paymentRequest.description
                  )
                        SavePayment(token, dd, paymentRequest.amount)
                    }
                    else {
                        DialLoad()
                        val ff =   PaymentModel(
                            0, refID, paymentRequest.authority,
                            paymentRequest.email,
                            paymentRequest.mobile,
                            paymentRequest.description
                        )
                        SavePayment_2(token, ff)

                    }



                }


        checkBox2.setOnClickListener {
            if (checkBox2.getTag().equals("0"))
            {
                checkBox2.getTag().equals("1")
            }
        }
        checkBox3.setOnClickListener {
            if (checkBox3.getTag().equals("0"))
            {
                checkBox3.getTag().equals("1")
            }
        }

        btn_send_2.setOnClickListener {
         if (address?.id.isNullOrEmpty())
         {
             Log.i("smlsvmlav", address?.id.toString())
             Toast.makeText(this, "آدرس را انتخاب کنید", Toast.LENGTH_LONG).show()
             return@setOnClickListener
         }
            if (!checkBox2.isChecked)
            {
                Toast.makeText(this, "زمان تحویل را تایید کنید", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }



//            var v=Intent(this@Activity_Second_Bascket,Activity_Resuilt_Payment::class.java)
//            v.putExtra("Type","A")
//            v.putExtra("Price","35000 تومان")
//            v.putExtra("Id_Payment","545841584")
//            startActivity(v)
//            finish()

//            Checkout(address?.id.toString(),off)
            if (checkBox3.visibility==View.VISIBLE)
            {
                if (!checkBox3.isChecked)
                {
                    Toast.makeText(this, "پس کرایه را تایید کنید", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
            }


            Checkout(id_address, off)




        }
//        data_card= data_card()
//        address=ModelAddress()
//        data_card= intent.getSerializableExtra("data") as data_card?
//        GetAddress()


        cardView.setOnClickListener {
            var I= Intent(this, MultyActivity_2::class.java)
            I.putExtra("Type", "Map2")
            I.putExtra("Type_2", "B")
//            I.putExtra("Pos",Pos)
//            I.putExtra("address",address)
            startActivityForResult(I, 878)

        }

        imageView47.setOnClickListener {
            var I= Intent(this, MultyActivity_2::class.java)
            I.putExtra("Type", "Map2")
            I.putExtra("Pos", Pos)
            I.putExtra("address", address)
            startActivityForResult(I, 14151)
        }
        goo.setOnClickListener {
            var I= Intent(this, MultyActivity_2::class.java)
            I.putExtra("Type", "Map2")
            I.putExtra("Pos", Pos)
            I.putExtra("address", address)
            startActivityForResult(I, 14151)
        }
        imageView45.setOnClickListener {
            var I=Intent(this, MultyActivity_2::class.java)
            I.putExtra("Type", "Map2")
            I.putExtra("Pos", Pos)
            I.putExtra("address", address)
            startActivityForResult(I, 14151)
        }
    }

    private fun setdata() {
        var data=data_card
        if (!data?.priceForShow.isNullOrEmpty())
        {
            textView14_2_4_5.setText(data?.priceForShow)
        }else{
            textView14_2_4_5.setText(" 0 تومان ")
        }


        if (data_card?.deliveryPriceForShow!=null)
        {
            textView96_46.setText(data_card?.deliveryPriceForShow)
        }
//
//
//
        if (!data?.discountPriceForShow.isNullOrEmpty())
        {
            textView16_2_4_5.setText(data?.discountPriceForShow)
        }else{
            textView16_2_4_5.setText(" 0 تومان ")
        }
//
//
//
//        if (!data?.pricePayForShow.isNullOrEmpty())
//        {
//            textView18_2_3.setText(data?.pricePayForShow)
//        }else{
//            textView18_2_3.setText(" 0 تومان ")
//        }
//
//
        if (!data?.pricePayForShow.isNullOrEmpty())
        {
            dveifoh_5_6.setText(data?.pricePayForShow)
        }else{
            dveifoh_5_6.setText(" 0 تومان ")
        }


        if(data_card?.deliveryPriceMessage!=null)
        {
            checkBox3.visibility=View.VISIBLE
            checkBox3.setText(data_card?.deliveryPriceMessage)
        }else{
            checkBox3.visibility=View.GONE
        }

        if(!data_card?.deliveryPriceForShow.isNullOrEmpty())
        {
            textView96_46.setText(data_card?.deliveryPriceForShow)
        }


        recyclerView.layoutManager=LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        if (data_card?.orderItems!=null)
        {
            adapter_address_marsole=Adapter_second_bascket_marsole(data_card?.orderItems!!, this)
            recyclerView.adapter=adapter_address_marsole
        }
        if (data_card?.deliveryTimeMessage!=null)
        {
            checkBox2.setText(data_card?.deliveryTimeMessage)
        }




    }




    fun  SavePayment(token: String, data: PaymentModel, Amount: Long)
    {
        val req = api?.SavePayment(
            "Bearer " +
                    token, data
        )
        Log.i("sdvsdbsfadf", token)
        req?.enqueue(object : Callback<model_savepayment> {
            override fun onResponse(
                call: Call<model_savepayment>,
                response: Response<model_savepayment>
            ) {
                Log.i("avavadva", response.code().toString())
                Dial_Close()
                if (response.code() == 401) {
                    Login(securityKey, object : Login {
                        override fun onLoginCompleted(success: Boolean) {
                            if (success) {
                                SavePayment(token, data, Amount)
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
                        this@Activity_Second_Bascket,
                        "" + code500?.getMessage(),
                        Toast.LENGTH_LONG
                    ).show()
                    Log.i("knknvsz", code500?.getMessage().toString())
                    return
                }
                if (response.isSuccessful) {
                    Dial_Close()
//                    zarinPalPayment(response.body()?.data?.pricePay?.toLong()!!, response.body()?.data?.id.toString())
                    if (response.body()?.data!!) {
                        var v = Intent(
                            this@Activity_Second_Bascket,
                            Activity_Resuilt_Payment::class.java
                        )
                        v.putExtra("Type", "A")
                        v.putExtra("Price", Amount.toString())
                        v.putExtra("Id_Payment", data.refId.toString())
                        startActivity(v)
                        finish()
                    } else {
                        var v = Intent(
                            this@Activity_Second_Bascket,
                            Activity_Resuilt_Payment::class.java
                        )
                        v.putExtra("Type", "B")
                        startActivity(v)
                        finish()
                    }
                }


            }

            override fun onFailure(call: Call<model_savepayment>, t: Throwable) {
                Log.i("svnskvn", t.message.toString())
                Dial_Close()
                var I = 2;
                var p = Dialapp(
                    2,
                    "اتصال خود را به اینترنت بررسی کنید",
                    object : Dial_App.Interface_new {
                        override fun News() {
                            SavePayment(token, data, Amount)
                        }
                    },
                    this@Activity_Second_Bascket
                )
                p.show()


            }
        })
    }




    fun  Off(S: String)
    {
        DialLoad()
        var ds=Model_Checkout()
        ds.DiscountCode=S
        ds.addressId=id_address
//        var body= RequestBody.create(MediaType.parse("text/pain"),S)
        var req=api?.Checkout("Bearer " + token, ds)
        req?.enqueue(object : Callback<RESPONSCARD> {
            @SuppressLint("UseCompatLoadingForDrawables")
            override fun onResponse(call: Call<RESPONSCARD>, response: Response<RESPONSCARD>) {
                Dial_Close()
                Log.i("grgthhr", response.code().toString())
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
                    Log.i("dvmsvkmsmkvv", code500?.getMessage().toString())
                    Toast.makeText(
                        this@Activity_Second_Bascket,
                        "" + code500?.getMessage(),
                        Toast.LENGTH_LONG
                    ).show()
                    return
                }
                if (response.isSuccessful) {
                    Log.i("toldmsfgth", "Ok")
                    Log.i("toldmsfgth", response.body().toString())
                    data_card = response.body()?.data
                    if (response.body()?.data?.discuntMessage != null) {
                        Toast.makeText(
                            this@Activity_Second_Bascket,
                            data_card?.discuntMessage,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    if (!response.body()?.data?.discountCode.isNullOrEmpty()) {
                        val img: Drawable =
                           resources.getDrawable(com.example.store.R.drawable.ic_check_circle)
                        img.setBounds(0, 0, 60, 60)
                        txt_fff.setCompoundDrawables(img, null, null, null)
                        btn_offer.setTextColor(Color.parseColor("#049A13"))
                    }

                    off = response.body()?.data?.discountCode.toString()
                    txt_fff.setText(off)

//                    if (response.body()?.data?.discountPrice!! >0)
//                    {
//                        Toast.makeText(this@Activity_Second_Bascket,"تخفیف اعمال شد",Toast.LENGTH_LONG).show()
//                        off=txt_fff.text.toString()
//                    }else{
//                        Toast.makeText(this@Activity_Second_Bascket,data_card?.discuntMessage,Toast.LENGTH_LONG).show()
//                    }
                    adapter_address_marsole?.items_2?.clear()
                    adapter_address_marsole?.items_2 = response.body()?.data?.orderItems!!
                    adapter_address_marsole?.notifyDataSetChanged()
                    data_card = response.body()?.data
                    setdata()
//                    if (response.body()?.data?.orderItems!=null)
//                    {
//                        if (response.body()?.data?.orderItems?.size==0)
//                        {
//                            var i=Intent()
//                            i.putExtra("data",ad_card?.V)
//                            setResult(RESULT_OK,i)
//                            finish()
//                        }
//                    }else{
//                        var i=Intent()
//                        i.putExtra("data",ad_card?.V)
//                        setResult(RESULT_OK,i)
//                        finish()
//                    }
//
//
//                    if (response.body()?.data?.orderItems!=null)
//                    {
//                        textView12.setText(response.body()?.data?.orderItems!!.size.toString())
//                    }else{
//                        textView12.setText("0")
//                    }


//                    var data=response.body()
//                    if (data?.data?.pricePayForShow!=null)
//                    {
//                        textView14.setText(data.data?.pricePayForShow)
//                    }else{
//                        textView14.setText(" 0 تومان ")
//                    }
//
//
//
//                    if (data?.data?.discountPrice!=null)
//                    {
//                        if (data.data?.discountPrice!! >0)
//                        {
//                            textView16.setText(data.data?.discountPrice!!.toString())
//                        }else{
//                            textView16.setText("0 تومان")
//                        }
//                    }else{
//                        textView16.setText("0 تومان")
//                    }
//
//
//
//
//                    if (data?.data?.pricePayForShow!=null)
//                    {
//                        textView18.setText(data.data?.pricePayForShow)
//                    }else{
//                        textView18.setText(" 0 تومان ")
//                    }


                }
                if (response.code() == 401) {
                    Login(securityKey, object : Login {
                        override fun onLoginCompleted(success: Boolean) {
                            if (success) {
                                Off(S)
                            }
                        }

                    })
                }
            }

            override fun onFailure(call: Call<RESPONSCARD>, t: Throwable) {
                Dial_Close()
                Log.i("toldmsfgth", t.message.toString())
                var I = 2;
                var p = Dialapp(
                    2,
                    "اتصال خود را به اینترنت بررسی کنید",
                    object : Dial_App.Interface_new {
                        override fun News() {
                            finish()
                        }
                    },
                    this@Activity_Second_Bascket
                )
                p.show()


            }

        })
    }
    fun SavePayment_2(token: String, data: PaymentModel)
    {
        val req = api?.SavePayment(
            "Bearer " +
                    token, data
        )
        req?.enqueue(object : Callback<model_savepayment> {
            override fun onResponse(
                call: Call<model_savepayment>,
                response: Response<model_savepayment>
            ) {
                Log.i("avavadva", response.code().toString())
                Dial_Close()
                if (response.code() == 401) {
                    Login(securityKey, object : Login {
                        override fun onLoginCompleted(success: Boolean) {
                            if (success) {
                                SavePayment_2(token, data)
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
                        this@Activity_Second_Bascket,
                        "" + code500?.getMessage(),
                        Toast.LENGTH_LONG
                    ).show()
                    Log.i("knknvsz", code500?.getMessage().toString())
                    return
                }
                if (response.isSuccessful) {
//                    zarinPalPayment(response.body()?.data?.pricePay?.toLong()!!, response.body()?.data?.id.toString())
                    var v =
                        Intent(this@Activity_Second_Bascket, Activity_Resuilt_Payment::class.java)
                    v.putExtra("Type", "B")
                    startActivity(v)
                    finish()
                }
            }

            override fun onFailure(call: Call<model_savepayment>, t: Throwable) {
                Dial_Close()
                var I = 2;
                var p = Dialapp(
                    2,
                    "اتصال خود را به اینترنت بررسی کنید",
                    object : Dial_App.Interface_new {
                        override fun News() {
                            SavePayment_2(token, data)
                        }
                    },
                    this@Activity_Second_Bascket
                )
                p.show()


            }
        })

    }
    fun  Checkout(AddressId: String, DiscountCode: String)
    {
        DialLoad()
        var model= Model_Checkout()
        model.AddressId=AddressId
        model.DiscountCode=DiscountCode
        Log.i("dvssnvknds", AddressId)
        Log.i("dvssnvknds", DiscountCode)
        var req=api?.Checkout("Bearer " + token, model)
        req?.enqueue(object : retrofit2.Callback<RESPONSCARD> {
            override fun onResponse(call: Call<RESPONSCARD>, response: Response<RESPONSCARD>) {
                Dial_Close()
                Log.i("svnskvnavb", response.code().toString())
                if (response.code() == 401) {
                    Login(securityKey, object : Login {
                        override fun onLoginCompleted(success: Boolean) {
                            if (success) {
                                Checkout(AddressId, DiscountCode)
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
                        this@Activity_Second_Bascket,
                        "" + code500?.getMessage(),
                        Toast.LENGTH_LONG
                    ).show()
                    Log.i("knknvsz", code500?.getMessage().toString())
                    return
                }
                if (response.isSuccessful && response.body()?.data != null && response.body()?.isSuccess == true) {

                    if (response.body()?.data?.deliveryTimeMessage != null) {
                        if (response.body()?.data?.deliveryTimeMessage.equals(data_card?.deliveryTimeMessage)) {
                            zarinPalPayment(
                                response.body()?.data?.pricePay?.toLong()!!,
                                response.body()?.data?.id.toString()
                            )
                        } else {
                            textView9751_4_5.setText(data_card?.orderItems?.size.toString())
                            data_card = response.body()?.data
                            checkBox2.isChecked = false
                            off = ""
                            checkBox3.isChecked = false
                            setdata()
                        }
                    } else {
                        Snackbar.make(
                            holder_889,
                            "مشکلی در پرداخت به وجود آمده",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }


                } else {
                    Snackbar.make(holder_889, "مشکلی در پرداخت به وجود آمده", Snackbar.LENGTH_LONG)
                        .show()
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
                    this@Activity_Second_Bascket
                )
                p.show()
            }

        })
    }


    fun  Checkout_main(AddressId: String, DiscountCode: String)
    {
        DialLoad()
        var model= Model_Checkout()
        model.AddressId=AddressId
        model.DiscountCode=DiscountCode
        Log.i("dvssnvknds", AddressId)
        Log.i("dvssnvknds", DiscountCode)
        var req=api?.Checkout("Bearer " + token, model)
        req?.enqueue(object : retrofit2.Callback<RESPONSCARD> {
            override fun onResponse(call: Call<RESPONSCARD>, response: Response<RESPONSCARD>) {
                Dial_Close()
                Log.i("svnskvnavb", response.code().toString())
                if (response.code() == 401) {
                    Login(securityKey, object : Login {
                        override fun onLoginCompleted(success: Boolean) {
                            if (success) {
                                Checkout(AddressId, DiscountCode)
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
                        this@Activity_Second_Bascket,
                        "" + code500?.getMessage(),
                        Toast.LENGTH_LONG
                    ).show()
                    Log.i("knknvsz", code500?.getMessage().toString())
                    return
                }
                if (response.isSuccessful && response.body()?.data != null && response.body()?.isSuccess == true) {

                    data_card = response.body()?.data
                    textView9751_4_5.setText(data_card?.orderItems?.size.toString())


                    if (data_card?.deliveryPriceForShow != null) {
                        textView96_46.setText(data_card?.deliveryPriceForShow)
                    }


                    if (!checkBox2.getTag().equals("1")) {
                        checkBox2.isChecked = false
                    }
                    if (!checkBox3.getTag().equals("1")) {
                        checkBox3.isChecked = false
                    }
                    textView65.setText(address?.fullLocation)
                    if (!response.body()?.data?.discountCode.isNullOrEmpty()) {
                        val img: Drawable =
                            resources.getDrawable(com.example.store.R.drawable.ic_check_circle)
                        img.setBounds(0, 0, 60, 60)
                        txt_fff.setCompoundDrawables(img, null, null, null)
                        btn_offer.setTextColor(Color.parseColor("#049A13"))
                    }
                    setdata()
                } else {
                    Snackbar.make(holder_889, "مشکلی در پرداخت به وجود آمده", Snackbar.LENGTH_LONG)
                        .show()
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
                            finish()
                        }
                    },
                    this@Activity_Second_Bascket
                )
                p.show()
            }

        })
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==878)
        {
            if (resultCode== RESULT_OK)
            {
                if (data?.getSerializableExtra("data_1")!=null)
                {
                    var cs= data?.getSerializableExtra("data_1") as ModelAddress?
                    Checkout_main(cs?.id.toString(), off)
                }
            }
        }
        if (requestCode==14151)
        {
            if (resultCode== Activity.RESULT_OK)
            {
//                recy_itemaddress.visibility=View.VISIBLE
                var v= ModelAddress()
                v= data?.getSerializableExtra("data") as ModelAddress
                address=v
                Log.i("fbgnhscdv", v.fullLocation.toString())
                Pos=data.getIntExtra("Pos", -1)
                textView65.setText(v.fullLocation.toString())
//               adapter_address?.items?.add(v)
//                adapter_address?.notifyItemInserted(adapter_address?.items?.size!! - 1)
            }
        }
    }
    fun GetAddress()
    {
        DialLoad()
        var req=api?.GETADDRESS("Bearer " + token)
        req?.enqueue(object : retrofit2.Callback<RESPONSADRESS> {
            override fun onResponse(call: Call<RESPONSADRESS>, response: Response<RESPONSADRESS>) {
                Dial_Close()
                Log.i("svnskvnavb", response.code().toString())
                if (response.code() == 401) {
                    Login(securityKey, object : Login {
                        override fun onLoginCompleted(success: Boolean) {
                            if (success) {
                                GetAddress()
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
                        this@Activity_Second_Bascket,
                        "" + code500?.getMessage(),
                        Toast.LENGTH_LONG
                    ).show()
                    Log.i("knknvsz", code500?.getMessage().toString())
                    return
                }
                if (response.isSuccessful) {
                    Log.i("svnskvnavb", response.code().toString())
                    if (response.body()?.data == null) {
//                        recy_itemaddress.visibility=View.GONE
                    } else {
                        if (response.body()?.data?.size == 0) {
//                            recy_itemaddress.visibility=View.GONE
                        } else {
                            Log.i("svnskvnavb", response.body()?.data?.size?.toString().toString())
//                            adapter_address?.items=response.body()?.data
//                            adapter_address?.notifyDataSetChanged()
                            address = response.body()?.data?.get(0)
                            textView65.setText(response.body()?.data?.get(0)?.fullLocation.toString())
                        }

                    }


                }
            }

            override fun onFailure(call: Call<RESPONSADRESS>, t: Throwable) {
                Dial_Close()
                var I = 3
                var p = Dialapp(I, "لطفا دوباره تلاش کنید", object : Dial_App.Interface_new {
                    override fun News() {
                        finish()
                    }
                }, this@Activity_Second_Bascket)
                p.show()
            }

        })
    }


    private fun zarinPalPayment(amount: Long, cartId: String) {

        Log.i("svmsmkvd", amount.toString())
        val purchase = ZarinPal.getPurchase(this)
        val payment = ZarinPal.getPaymentRequest()
        /*Get Merchant Id from Zarin pal*/

        payment.merchantID = "4e90b1c8-dd4c-4bee-8638-2d2cae8e8897"  // Reza

//        payment.merchantID = "41b5ff10-3d02-11ea-8bf5-000c295eb8fc" // AniParde






        /*The price with toman = 1000 toman not rial */

        payment.amount = amount

        /*Desc For : Why should pay ?*/




        payment.description = "شماره سند : $cartId"
        payment.setCallbackURL("return://zarinpalpayment")
        /*Create the request*/
        DialLoad()
        purchase.startPayment(payment) { status, authority, paymentGatewayUri, intent ->
           Dial_Close()
            if (status == 100) {
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "خطا در ایجاد درخواست پرداخت",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }






}