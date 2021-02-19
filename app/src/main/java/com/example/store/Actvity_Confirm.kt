package com.example.store

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.store.Main_Fragments.ErrorCode500
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_actvity__confirm.*
import kotlinx.android.synthetic.main.fragment_frag__phone__number.view.*
import kotlinx.android.synthetic.main.fragment_frag_verfivcation_2.view.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class Actvity_Confirm : BaseActiity() {

    companion object{
        var Act:Activity ?=null
    }
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actvity__confirm)
//        window.statusBarColor= Color.parseColor("#7209b7")
        if (!phoneNumber.isNullOrEmpty())
        {
            editTextTextPersonName4.setText(phoneNumber.toString())
        }
        Act=this
        button12.setOnClickListener {
//            if (!v.checkBox.isChecked)
//            {
//                Snackbar.make(v.holder_2, "ابتدا باید قوانین رو تایید کنید", Snackbar.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }

            if (editTextTextPersonName4.text.trim().toString().isNullOrEmpty())
            {
                Snackbar.make(holder_2, "شماره تلفن را وارد کنید", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            if (v.editTextNumber.text.trim().toString().length<11)
//            {
//                Snackbar.make(v.holder, "شماره تلفن را اشتباه را وارد کردید", Snackbar.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }

            phoneNumber=editTextTextPersonName4.text.trim().toString()
            securityKey="Test"

//            sharedPreferences?.edit()?.putString(Constants.USER_PHONE_NUMBER, phoneNumber)?.apply()

            Confirm_1()





        }

    }
    fun  Confirm_1()
    {
        if (isNetConnected())
        {
            DialLoad()
            phoneNumber=editTextTextPersonName4.text.trim().toString()
            var json=""
            json= JSONObject().put("confirmCode", "")
                .put("phone", phoneNumber).toString()
//                     .put("phone", "").toString()
            var body= RequestBody.create(MediaType.parse("text/plain"), json)
            var call=api?.confirmSms(body)
            call?.enqueue(object : Callback<ConfirmSmsResponse> {
                override fun onResponse(
                    call: Call<ConfirmSmsResponse>, response: Response<ConfirmSmsResponse>
                ) {
                    Dial_Close()
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
                            this@Actvity_Confirm,
                            "" + code500?.getMessage(),
                            Toast.LENGTH_LONG
                        ).show()
                        return
                    }
                    if (response.isSuccessful) {
                        if (response.body()?.isSuccess!!) {
                            if (response.body()?.data != null) {
                                if (response.body()?.data?.status == 1) {
                                    var i = Intent(this@Actvity_Confirm, Activity_PassWord::class.java)
//                                    i.putExtra("Type", "Z")
                                    i.putExtra("data", phoneNumber)
                                    startActivity(i)
//                                    finish()
                                } else {
                                    Log.i("cjnasdvbjajb","A")
                                    Snackbar.make(holder_2, "مشکلی در ورود به سیستم به وجود آمده", Snackbar.LENGTH_SHORT).show()
                                }
                            } else {
                                Log.i("cjnasdvbjajb","B")
                                Snackbar.make(holder_2, "مشکلی در ورود به سیستم به وجود آمده", Snackbar.LENGTH_SHORT).show()
                            }
                        } else {
                            Log.i("cjnasdvbjajb","C")
                            Snackbar.make(holder_2, "مشکلی در ورود به سیستم به وجود آمده", Snackbar.LENGTH_SHORT).show()
                        }
//                         sharedPreferences?.edit()?.putString(Constants.USER_PHONE_NUMBER, phoneNumber)?.apply()

                    } else {
                        Log.i("cjnasdvbjajb","D")
                        Snackbar.make(holder_2, "مشکلی در ورود به سیستم به وجود آمده", Snackbar.LENGTH_SHORT).show()
                    }


                }

                override fun onFailure(call: Call<ConfirmSmsResponse>, t: Throwable) {
                    Dial_Close()
                    Log.i("dvkmfkvkdmvs", t.message.toString())
                    Snackbar.make(holder_2, "دوباره تلاش کنید", Snackbar.LENGTH_SHORT).show()
                }

            })




        }else{
            Snackbar.make(holder_2, "اتصال خود را به اینترنت چک کنید", Snackbar.LENGTH_SHORT).show()
        }
    }
}