package com.example.store.Main_Fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.store.ConfirmSmsResponse
import com.example.store.MultyActivity_2
import com.example.store.R
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import kotlinx.android.synthetic.main.fragment_frag__phone__number.view.*
import kotlinx.android.synthetic.main.fragment_frag_verfivcation_2.view.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class Frag_Phone_Number : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        var v= inflater.inflate(R.layout.fragment_frag__phone__number, container, false)
        var v=inflater.inflate(R.layout.fragment_frag_verfivcation_2, container, false)


//        var cc="<b style=\"color:blueviolet\"></b>"


        if (!phoneNumber.isNullOrEmpty())
        {
            v.editTextTextPersonName4.setText(phoneNumber.toString())
        }

        v.button12.setOnClickListener {
//            if (!v.checkBox.isChecked)
//            {
//                Snackbar.make(v.holder_2, "ابتدا باید قوانین رو تایید کنید", Snackbar.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }

            if (v.editTextTextPersonName4.text.trim().toString().isNullOrEmpty())
            {
              var v2=  Snackbar.make(v.holder_2, "شماره تلفن را وارد کنید", Snackbar.LENGTH_SHORT)
                return@setOnClickListener
            }

//            if (v.editTextNumber.text.trim().toString().length<11)
//            {
//                Snackbar.make(v.holder, "شماره تلفن را اشتباه را وارد کردید", Snackbar.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }

            phoneNumber=v.editTextTextPersonName4.text.trim().toString()
            securityKey="Test"

//            sharedPreferences?.edit()?.putString(Constants.USER_PHONE_NUMBER, phoneNumber)?.apply()

            Confirm_1(v)





        }

        return v;
    }
     fun  Confirm_1(v: View)
     {
         if (isNetConnected())
         {
          DialLoad()
             phoneNumber=v.editTextTextPersonName4.text.trim().toString()
             var json=""
                 json= JSONObject().put("confirmCode", "")
                     .put("phone", phoneNumber).toString()
//                     .put("phone", "").toString()
             var body=RequestBody.create(MediaType.parse("text/plain"), json)
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
                             context,
                             "" + code500?.getMessage(),
                             Toast.LENGTH_LONG
                         ).show()
                         return
                     }
                     if (response.isSuccessful) {
                         if (response.body()?.isSuccess!!) {
                             if (response.body()?.data != null) {
                                 if (response.body()?.data?.status == 1) {
                                     var i = Intent(activity, MultyActivity_2::class.java)
                                     i.putExtra("Type", "Z")
                                     i.putExtra("data", phoneNumber)
                                     startActivity(i)
                                     activity?.finish()
                                 } else {
                                     Snackbar.make(
                                         v.holder,
                                         "مشکلی در ورود به سیستم به وجود آمده",
                                         Snackbar.LENGTH_SHORT
                                     ).show()
                                 }
                             } else {
                                 Snackbar.make(
                                     v.holder,
                                     "مشکلی در ورود به سیستم به وجود آمده",
                                     Snackbar.LENGTH_SHORT
                                 ).show()
                             }
                         } else {
                             Snackbar.make(
                                 v.holder,
                                 "مشکلی در ورود به سیستم به وجود آمده",
                                 Snackbar.LENGTH_SHORT
                             ).show()
                         }
//                         sharedPreferences?.edit()?.putString(Constants.USER_PHONE_NUMBER, phoneNumber)?.apply()

                     } else {
                         Snackbar.make(
                             v.holder,
                             "مشکلی در ورود به سیستم به وجود آمده",
                             Snackbar.LENGTH_SHORT
                         ).show()
                     }


                 }

                 override fun onFailure(call: Call<ConfirmSmsResponse>, t: Throwable) {
                     Dial_Close()
                     Log.i("dvkmfkvkdmvs", t.message.toString())
                     Snackbar.make(v.holder, "دوباره تلاش کنید", Snackbar.LENGTH_SHORT).show()
                 }

             })




         }else{
             Snackbar.make(v.holder, "اتصال خود را به اینترنت چک کنید", Snackbar.LENGTH_SHORT).show()
         }
     }




}