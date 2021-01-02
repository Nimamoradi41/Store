package com.example.store.Main_Fragments

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.store.Dialog.Dial_App
import com.example.store.Models.ResPonseProfile
import com.example.store.R
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import kotlinx.android.synthetic.main.fragment_frag__myaccunt.*
import kotlinx.android.synthetic.main.fragment_frag__myaccunt.view.*
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import javax.security.auth.callback.Callback

class Frag_Myaccunt : BaseFragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_frag__myaccunt, container, false)

        GetProfile(v)
//        val datePickerDialog = DatePickerDialog.newInstance(
//                { _, year, monthOfYear, dayOfMonth ->
//                    y1 = year
//                    m1 = monthOfYear + 1
//                    d1 = dayOfMonth
//                    startDate = "$year/${convertTime(m1)}/${convertTime(dayOfMonth)}"
//
//                    txt_start_date.text = dayOfMonth.toString() + " " + numToMonth(m1) + " " + year
//
////                    TransitionManager.beginDelayedTransition(constraint_parent)
////                    txt_start_date_title.visibility = View.VISIBLE
////                    txt_end_date_title.visibility = View.VISIBLE
////
////                    val constraintSet = ConstraintSet()
////                    constraintSet.clone(constraint_parent)
////                    constraintSet.connect(
////                        btn_start_date.id,
////                        ConstraintSet.TOP,
////                        txt_start_date_title.id,
////                        ConstraintSet.BOTTOM,
////                        dp2px(16)
////                    )
////                    constraint_parent.setConstraintSet(constraintSet)
//
//                    selectedStartDate = startDate
//                    Log.i("svknsmvAA", selectedStartDate)
//                    if (!selectedEndDate.isNullOrEmpty()) {
//                        isValidDate =
//                                !(y1 > y2 || y1 == y2 && m1 > m2 || y1 == y2 && m1 == m2 && d1 > d2)
//                    }
//                },
//                Integer.parseInt(date.split("/")[0]),
//                Integer.parseInt(date.split("/")[1]) - 1,
//                Integer.parseInt(date.split("/")[2])
//        )

//        datePickerDialog.show(childFragmentManager, "Datepickerdialog")
        return v
    }


    fun GetProfile(V:View)
    {
        DialLoad()
        var req=api?.GetProfile("Bearer " +token)
        req?.enqueue(object : retrofit2.Callback<ResPonseProfile> {
            override fun onResponse(call: Call<ResPonseProfile>, response: Response<ResPonseProfile>) {
                Log.i("fkvskfnb", response.code().toString())
                Dial_Close()
                if (response.code()==401)
                {
                    Login(securityKey,object : Login {
                        override fun onLoginCompleted(success: Boolean) {
                            if (success)
                            {
                                GetProfile(V)
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
                    if (response.body()?.data?.firstName!=null)
                    {
                        V.edt_name.setText(response.body()?.data?.firstName)
                    }

                    if (response.body()?.data?.lastLogin!=null)
                    {
                        V.edt_famili.setText(response.body()?.data?.firstName)
                    }


                    if (response.body()?.data?.birthDayFa!=null)
                    {
                        V.edy_birthday.setText(response.body()?.data?.birthDayFa)
                    }


                    if (response.body()?.data?.phone!=null)
                    {
                        V.edt_tel.setText(response.body()?.data?.phone)
                    }
                    Log.i("fkvskfnb",response.body()?.data?.gender.toString())

                   if (response.body()?.data?.gender!=null)
                   {
                       if (response.body()?.data?.gender==1)
                       {
                           V.rap_2.check(R.id.men)
                       }


                       if (response.body()?.data?.gender==2)
                       {
                           V.rap_2.check(R.id.famel)
                       }
                   }





                }
            }


            override fun onFailure(call: Call<ResPonseProfile>, t: Throwable) {
                Log.i("fkvskfnb", t.message.toString())
                Dial_Close()
                var I=3
                var p=   Dialapp(I,"لطفا دوباره تلاش کنید",object : Dial_App.Interface_new{
                    override fun News() {
                        activity?.finish()
                    }
                }, context!!)
                p.show()
            }

        })

    }



    fun EditProfile(V:View)
    {
        DialLoad()
        var req=api?.GetProfile("Bearer " +token)

        req?.enqueue(object : retrofit2.Callback<ResPonseProfile> {
            override fun onResponse(call: Call<ResPonseProfile>, response: Response<ResPonseProfile>) {
                Dial_Close()
                if (response.code()==401)
                {
                    Login(securityKey,object : Login {
                        override fun onLoginCompleted(success: Boolean) {
                            if (success)
                            {
                                EditProfile(V)
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

//                    V.edt_name.setText(response.body()?.data?.fullName)
//                    V.edt_famili.setText(response.body()?.data?.fullName)
                    V.edy_birthday.setText(response.body()?.data?.birthDayFa)
                    V.edt_tel.setText(response.body()?.data?.phone)

                    if (response.body()?.data?.gender==1)
                    {
                        V.rap_2.check(R.id.men)
                    }


                    if (response.body()?.data?.gender==2)
                    {
                        V.rap_2.check(R.id.famel)
                    }



                }
            }

            override fun onFailure(call: Call<ResPonseProfile>, t: Throwable) {
                Dial_Close()
                var I=3
                var p=   Dialapp(I,"لطفا دوباره تلاش کنید",object : Dial_App.Interface_new{
                    override fun News() {
                        activity?.finish()
                    }
                }, context!!)
                p.show()
            }

        })
    }
    }

