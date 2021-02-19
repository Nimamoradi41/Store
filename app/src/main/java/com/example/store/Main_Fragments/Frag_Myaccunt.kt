package com.example.store.Main_Fragments

import android.R.attr.typeface
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.store.Dialog.Dial_App
import com.example.store.Models.ResPonseProfile
import com.example.store.R
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import ir.hamsaa.persiandatepicker.Listener
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import kotlinx.android.synthetic.main.fragment_frag__myaccunt_2.*
import kotlinx.android.synthetic.main.fragment_frag__myaccunt_2.view.*
import kotlinx.android.synthetic.main.fragment_frag__myaccunt_2.view.btn_save
import kotlinx.android.synthetic.main.fragment_frag__myaccunt_2.view.edt_codemeli
import kotlinx.android.synthetic.main.fragment_frag__myaccunt_2.view.edt_famili
import kotlinx.android.synthetic.main.fragment_frag__myaccunt_2.view.edt_name
import kotlinx.android.synthetic.main.fragment_frag__myaccunt_2.view.edt_tel
import kotlinx.android.synthetic.main.fragment_frag__myaccunt_2.view.edy_birthday_2
import kotlinx.android.synthetic.main.fragment_frag__myaccunt_2.view.rap_2
import kotlinx.android.synthetic.main.fragment_frag__myaccunt_2.*
import kotlinx.android.synthetic.main.fragment_frag__myaccunt_2.view.*
import retrofit2.Call
import retrofit2.Response
import java.io.IOException


class Frag_Myaccunt : BaseFragment() {
    var Gender:Int ?=null
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_frag__myaccunt_2, container, false)

        if (isNetConnected())
        {
            GetProfile(v)
        }else{
            var I = 2;
            var p = Dialapp(
                    2,
                    "اتصال خود را به اینترنت بررسی کنید",
                    object : Dial_App.Interface_new {
                        override fun News() {
                        activity?.finish()
                        }
                    },
                    context!!
            )
            p.show()
        }



        v.btn_save.setOnClickListener {
            if (v.edt_name.text.isNullOrEmpty())
            {
                v.edt_name.setError("نام را وارد کنید")
                return@setOnClickListener
            }


            if (v.edt_famili.text.isNullOrEmpty())
            {
                v.edt_famili.setError("نام خانوادگی  را وارد کنید")
                return@setOnClickListener
            }


            if (v.edt_codemeli.text.isNullOrEmpty())
            {
                v.edt_codemeli.setError("  کد ملی  را وارد کنید")
                return@setOnClickListener
            }





            if (v.edt_codemeli.text?.length!! >10|| v.edt_codemeli.text?.length!! <10)
            {
                v.edt_codemeli.setError("کد ملی اشتباه است")
                return@setOnClickListener
            }

//            if (v.edy_birthday.text.isNullOrEmpty())
//            {
//                v.edy_birthday.setError(" تاریخ تولد  را وارد کنید")
//                return@setOnClickListener
//            }




             if (rap_2.checkedRadioButtonId==R.id.men)
             {
                 Gender=1
             }else{
                 Gender=2
             }


            EditProfile(v)


        }

        v.edy_birthday_2.setOnClickListener {
            showDataPicker(v)
        }
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
                    if (response.body()?.data?.firstName != null) {
                        V.edt_name.setText(response.body()?.data?.firstName)
                    }

                    if (response.body()?.data?.lastLogin != null) {
                        V.edt_famili.setText(response.body()?.data?.lastName)
                    }


                    if (response.body()?.data?.birthDayFa != null) {
                        V.edy_birthday_2.setText(response.body()?.data?.birthDayFa)
                    }



                    if (response.body()?.data?.meliCode != null) {
                        V.edt_codemeli.setText(response.body()?.data?.meliCode)
                    }else{
                        V.edt_codemeli.setText(phoneNumber)
                    }


                    if (response.body()?.data?.phone != null) {
                        V.edt_tel.setText(response.body()?.data?.phone)
                    }
                    Log.i("fkvskfnb", response.body()?.data?.gender.toString())
                    Gender= response.body()?.data?.gender
                    if (response.body()?.data?.gender != null) {
                        if (response.body()?.data?.gender == 1) {
                            V.rap_2.check(R.id.men)
                        }


                        if (response.body()?.data?.gender == 2) {
                            V.rap_2.check(R.id.famel)
                        }
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
                }, activity!!)
                p.show()
            }

        })

    }




    fun EditProfile(V: View)
    {
        var v=Edit_Profile()
        v.birthDayFa= edy_birthday_2.text?.toString()
        v.meliCode=edt_codemeli.text?.toString()
        v.lastName=edt_famili.text?.toString()
        v.firstName=edt_name.text?.toString()
        Log.i("dmvsndvdv", Gender.toString())
        Log.i("dmvsndvdv", edt_codemeli.text?.toString().toString())
        Log.i("dmvsndvdv", edt_famili.text?.toString().toString())
        Log.i("dmvsndvdv", edy_birthday_2.text?.toString().toString())
        Log.i("dmvsndvdv", edt_name.text?.toString().toString())
        v.gender=Gender
        DialLoad()
        var req=api?.EditProfile("Bearer " + token,v)
        req?.enqueue(object : retrofit2.Callback<ResPonseProfile> {
            override fun onResponse(call: Call<ResPonseProfile>, response: Response<ResPonseProfile>) {
                Dial_Close()
                if (response.code() == 401) {
                    Login(securityKey, object : Login {
                        override fun onLoginCompleted(success: Boolean) {
                            if (success) {
                                EditProfile(V)
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
                    var v=Intent()
                    v.putExtra("name",V.edt_name.text.toString()+" "+V.edt_famili.text.toString())
                    activity?.setResult(Activity.RESULT_OK,v)
                    activity?.finish()

                }
            }

            override fun onFailure(call: Call<ResPonseProfile>, t: Throwable) {
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

    fun showDataPicker(v:View)
    {
      var   picker = PersianDatePickerDialog(context)
                .setPositiveButtonString("باشه")
                .setNegativeButton("بیخیال")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1300)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setActionTextColor(Color.GRAY)
                .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
                .setShowInBottomSheet(true)
                .setListener(object : Listener {
                    override fun onDateSelected(persianCalendar: PersianCalendar?) {
                        Log.d("svdbgbs", "onDateSelected: " + persianCalendar?.getGregorianChange()) //Fri Oct 15 03:25:44 GMT+04:30 1582
                        Log.d("svdbgbs", "onDateSelected: " + persianCalendar?.getTimeInMillis()) //1583253636577
                        Log.d("svdbgbs", "onDateSelected: " + persianCalendar?.getTime()) //Tue Mar 03 20:10:36 GMT+03:30 2020
                        Log.d("svdbgbs", "onDateSelected: " + persianCalendar?.getDelimiter()) //  /
                        Log.d("svdbgbs", "onDateSelected: " + persianCalendar?.getPersianLongDate()) // سه‌شنبه  13  اسفند  1398
                        Log.d("svdbgbs", "onDateSelected: " + persianCalendar?.getPersianLongDateAndTime()) //سه‌شنبه  13  اسفند  1398 ساعت 20:10:36
                        Log.d("svdbgbs", "onDateSelected: " + persianCalendar?.getPersianMonthName()) //اسفند
                        Log.d("svdbgbs", "onDateSelected: " + persianCalendar?.isPersianLeapYear()) //false
                        v.edy_birthday_2.setText(persianCalendar?.getPersianYear().toString()
                                + "/" + persianCalendar?.getPersianMonth() + "/" +
                                persianCalendar?.getPersianDay())
                        v.edy_birthday_2.setError("",null)
                    }




                    override fun onDismissed() {

                    }

                })

        picker.show()
    }
    }

