package com.example.store.Main_Fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.store.Dialog.Dial_App
import com.example.store.Models.Defult_Response
import com.example.store.Models.ResPonseProfile
import com.example.store.R
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import kotlinx.android.synthetic.main.fragment_frag__accunt__bank.*
import kotlinx.android.synthetic.main.fragment_frag__accunt__bank.view.*
import kotlinx.android.synthetic.main.fragment_frag__myaccunt_2.*
import kotlinx.android.synthetic.main.fragment_frag__myaccunt_2.view.*
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class Frag_Accunt_Bank : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_frag__accunt__bank, container, false)

        GetProfile(v)

        v.button3.setOnClickListener {

            if (v.edt_bankname.text.isNullOrEmpty())
            {
                v.edt_bankname.setError("نام بانک  را وارد کنید")
                return@setOnClickListener
            }





            if (v.edt_numbercart.text.isNullOrEmpty())
            {
                v.edt_numbercart.setError("  شماره کارت  را وارد کنید")
                return@setOnClickListener
            }



            if (v.edt_numbercart.text.length<16)
            {
                v.edt_numbercart.setError("  شماره کارت  اشتباه است")
                return@setOnClickListener
            }












            Edit_Profile_Bank(v)
        }
        return v;
    }


    fun Edit_Profile_Bank(V:View){
       var Data=Editprofile_Bank()

        Data.cartNumber= edt_numbercart.text?.toString()
        Data.shabaNumber=edt_shaba.text?.toString()
        Data.bankName=edt_bankname.text?.toString()

        var req=api?.EditBankinfo("Bearer " + token,Data)
        req?.enqueue(object : retrofit2.Callback<Defult_Response> {
            override fun onResponse(call: Call<Defult_Response>, response: Response<Defult_Response>) {
                Dial_Close()
                if (response.code() == 401) {
                    Login(securityKey, object : Login {
                        override fun onLoginCompleted(success: Boolean) {
                            if (success) {
                                Edit_Profile_Bank(V)
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
//                    V.edt_name.setText(response.body()?.data?.fullName)
//                    V.edt_famili.setText(response.body()?.data?.fullName)
//                    var cc=Intent()
//                    cc.putExtra("data",response.body()?.data?.cartNumber)
                    Toast.makeText(requireActivity(),"اطلاعات با موفقیت ثبت شد",Toast.LENGTH_LONG).show()
                    activity?.finish()

                }
            }

            override fun onFailure(call: Call<Defult_Response>, t: Throwable) {
                Dial_Close()
                var I = 2;
                var p = Dialapp(
                        2,
                        "اتصال خود را به اینترنت بررسی کنید",
                        object : Dial_App.Interface_new {
                            override fun News() {

                            }
                        },
                        context!!
                )
                p.show()
            }

        })

    }



    fun GetProfile(V: View)
    {
        DialLoad()
        var req=api?.GetProfile("Bearer " + token)
        req?.enqueue(object : retrofit2.Callback<ResPonseProfile> {
            override fun onResponse(call: Call<ResPonseProfile>, response: Response<ResPonseProfile>) {
                Log.i("fkvskfnb", response.code().toString())
                Dial_Close()
                if (response.code() == 401) {
                    Login(securityKey, object : Login {
                        override fun onLoginCompleted(success: Boolean) {
                            if (success) {
                                GetProfile(V)
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
                    if (response.body()?.data?.bankName != null) {
                        V.edt_bankname.setText(response.body()?.data?.bankName)
                    }

                    if (response.body()?.data?.cartNumber != null) {
                        V.edt_numbercart.setText(response.body()?.data?.cartNumber)

                        var cc=Intent()
                        cc.putExtra("data",response.body()?.data?.cartNumber)
                        activity?.setResult(Activity.RESULT_OK,cc)
                    }



                    if (response.body()?.data?.shabaNumber != null) {
                        V.edt_shaba.setText(response.body()?.data?.shabaNumber)
                    }else{
                        V.edt_shaba.setText("")
                    }






                }
            }


            override fun onFailure(call: Call<ResPonseProfile>, t: Throwable) {
                Log.i("fkvskfnb", t.message.toString())
                Dial_Close()
                var I = 3
                var p = Dialapp(I, "لطفا دوباره تلاش کنید", object : Dial_App.Interface_new {
                    override fun News() {
                        activity?.finish()
                    }
                }, context!!)
                p.show()
            }

        })

    }

}