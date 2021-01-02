package com.example.store.Main_Fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.store.*
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import kotlinx.android.synthetic.main.fragment_frag__phone__number.view.*
import kotlinx.android.synthetic.main.fragment_frag_verfivcation.view.*
import kotlinx.android.synthetic.main.fragment_frag_verfivcation.view.button5
import kotlinx.android.synthetic.main.fragment_frag_verfivcation.view.holder
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class Frag_verfivcation : BaseFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        phoneNumber= arguments?.getString("data_2").toString()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        var v=inflater.inflate(R.layout.fragment_frag_verfivcation, container, false)
        v.button5.setOnClickListener {
//            if (v.editTextNumber2.text.trim().toString().isNullOrEmpty())
//            {
//                Snackbar.make(v.holder,"کد تاید اشتباه است",Snackbar.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            if (v.editTextNumber.text.trim().toString().isNullOrEmpty())
//            {
//                Snackbar.make(v.holder,"کد تاید اشتباه است",Snackbar.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            if (v.editTextNumber3.text.trim().toString().isNullOrEmpty())
//            {
//                Snackbar.make(v.holder,"کد تاید اشتباه است",Snackbar.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            if (v.editTextNumber4.text.trim().toString().isNullOrEmpty())
//            {
//                Snackbar.make(v.holder,"کد تاید اشتباه است",Snackbar.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
            Log.i("bwrwrweessa",phoneNumber)
            Confirm_2(v)
        }
        TextWTACHERS(v)
        TextWTACHERS_Ontoch(v)
        return  v;
    }

    fun  Confirm_2(v: View)
    {
        if (isNetConnected())
        {
            DialLoad()
            var json=""
            Log.i("vmmvdlsjsgvxfsfxsgcd",json)
            json= JSONObject().put("confirmCode", "1111")
                .put("phone",phoneNumber).toString()
            var body= RequestBody.create(MediaType.parse("text/plain"), json)
            var call=api?.confirmSms(body)
            Log.i("vmmvdlsjsgvxfsfxsgcd",json)
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
                    if (response.isSuccessful)
                    {
                        DialLoad()
                        Log.i("lmdldlvdllmdvlmvdlmv",response.body()?.data?.securityKey.toString())
                        sharedPreferences?.edit()?.putString(Constants.USER_SECURITY_KEY,response.body()?.data?.securityKey)?.apply()
                        sharedPreferences?.edit()?.putString(Constants.USER_PHONE_NUMBER,phoneNumber)?.apply()
                        Login(response.body()?.data?.securityKey.toString(),object  :Login{
                            override fun onLoginCompleted(success: Boolean) {
                                if (success)
                                {
                                    Log.i("vvmvmmvfkfkfbbn","A")
                                    GetHome()
                                }
                            }

                        })

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
    fun  GetHome()
    {
        var req=api?.GETHOME("Bearer "+token)
        req?.enqueue(object :Callback<RESPOSNHOME>{
            override fun onResponse(call: Call<RESPOSNHOME>, response: Response<RESPOSNHOME>) {
                Dial_Close()
                Log.i("zcvmzkmvzkmvmzv",response.code().toString())
                if (response.isSuccessful)
                {
                    var I=Intent(context,MainActivity::class.java)
                    I.putExtra("DATA",response.body()?.getData())
                    startActivity(I)
                    activity?.finish()
                }
                if (response.code()==401)
                {
                    Login(securityKey,object :Login{
                        override fun onLoginCompleted(success: Boolean) {
                            if (success)
                            {
                                GetHome()
                            }
                        }

                    })
                }
            }
            override fun onFailure(call: Call<RESPOSNHOME>, t: Throwable) {
                Log.i("fsmvbsmlfbad", t.message.toString())
                Toast.makeText(context,"ERROR",Toast.LENGTH_SHORT).show()
            }

        })

    }
    fun TextWTACHERS(V:View) {
        V.editTextNumber1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                if (editTextTextPersonName.text.trim())
                if (p0.toString().trim().isEmpty()) {
                    V.editTextNumber1.setBackgroundResource(R.drawable.ic_box1)
                } else {
                    V.editTextNumber1.setBackgroundResource(R.drawable.ic_box_2)
                    V.editTextNumber2.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        V.editTextNumber2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().trim().isEmpty()) {
                    V.editTextNumber2.setBackgroundResource(R.drawable.ic_box1)
                    V.editTextNumber1.requestFocus()
                } else {
                    V.editTextNumber2.setBackgroundResource(R.drawable.ic_box_2)
                    V.editTextNumber3.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        V.editTextNumber3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (V.editTextNumber3.text.trim().isEmpty()) {
                    V.editTextNumber3.setBackgroundResource(R.drawable.ic_box1)
                    V.editTextNumber2.requestFocus()
                } else {
                    V.editTextNumber3.setBackgroundResource(R.drawable.ic_box_2)
                    V.editTextNumber4.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        V.editTextNumber4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().isEmpty()) {
                    V.editTextNumber4.setBackgroundResource(R.drawable.ic_box1)
                    V.editTextNumber3.requestFocus()
                } else {
                    V.editTextNumber4.setBackgroundResource(R.drawable.ic_box_2)

                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }
    fun TextWTACHERS_Ontoch(V:View) {
        V.editTextNumber1.setOnTouchListener(View.OnTouchListener { v, event ->
            V.editTextNumber1.isCursorVisible = false
//            editTextTextPersonName.setFocusableInTouchMode(true)
            false
        })
        V.editTextNumber2.setOnTouchListener(View.OnTouchListener { v, event ->
            V.editTextNumber2.isCursorVisible = false
//            editTextTextPersonName.setFocusableInTouchMode(true)
            false
        })
        V.editTextNumber3.setOnTouchListener(View.OnTouchListener { v, event ->
            V.editTextNumber3.isCursorVisible = false
//            editTextTextPersonName.setFocusableInTouchMode(true)
            false
        })
        V.editTextNumber4.setOnTouchListener(View.OnTouchListener { v, event ->
            V.editTextNumber4.isCursorVisible = false
//            editTextTextPersonName.setFocusableInTouchMode(true)
            false
        })

    }
}