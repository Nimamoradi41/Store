package com.example.store

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.store.Dialog.Dial_App
import com.example.store.Main_Fragments.ErrorCode500
import com.example.store.Main_Fragments.Login
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import kotlinx.android.synthetic.main.custome_dial_app.view.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


open class BaseActiity : AppCompatActivity() {
    var sharedPreferences: SharedPreferences? = null
    var mytag_sharedpreferences="Shop"
    var phoneNumber = ""
    var api:Api ?= null
    var fullName = ""
    var gender = 0
    var token = ""
    var securityKey = ""
    var Dialog_load:Dialog?=null
    companion object{
        var api:Api ?= null

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        sharedPreferences = getSharedPreferences(mytag_sharedpreferences, MODE_PRIVATE)
        Update_Data()
    }

    public fun DialLoad( ) {
        Dialog_load = Dialog(this)
        Dialog_load?.setCancelable(false)
        val inflater = LayoutInflater.from(this)
        val view: View = inflater.inflate(R.layout.layout_loading, null, false)
        Dialog_load?.setContentView(view)
        Dialog_load?.window?.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT)
        Dialog_load?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        Dialog_load?.show()






    }
    public fun Dial_Close() {
        if (Dialog_load!=null)
        {
            Dialog_load?.dismiss()
        }
    }
    public fun Dialapp(Type: Int, S: String, I: Dial_App.Interface_new, context: Context): Dialog {
        var d = Dialog(context)
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.custome_dial_app, null, false)
        d.setContentView(view)
        d.window?.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT)
        d.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        if (Type==2)
        {
            view.textView22.setText(S)
            view.button7.setText("متوجه شدم")
            view.imageView10.setImageResource(R.drawable.ic_bad_req)
        }


        if (Type==3)
        {
            view.textView22.setText(S)
            view.button7.setText("تلاش دوباره")
            view.imageView10.setImageResource(R.drawable.ic_refresh)
        }

        view.button7.setOnClickListener {
            d.dismiss()
            I.News()
        }
        return d
    }


    fun isNetConnected(): Boolean {
        val cn = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nf = cn.activeNetworkInfo
        return if (nf != null && nf.isConnected) {
            true
        } else {
            try {
                //                showAlertDialog_new(R.drawable.ic_icons, "ارتباط اینترنت شما قطع است . لطفا آن را بررسی کنید", 0, null);
            } catch (e: java.lang.Exception) {
            }
            false
        }
    }
    fun  AddEditDeleteItem_Card(Count: Int, Id: String, result: Resuilt)
    {
        DialLoad()
        var data_item=AddEditDeleteItemModel()
        data_item.Count= Count.toString()
        data_item.ProductId=Id
        var req=api?.AddEditDeleteItem_Card("Bearer " + token, data_item)
        req?.enqueue(object : Callback<Respons_AddEditDeleteItem_Card> {
            override fun onResponse(call: Call<Respons_AddEditDeleteItem_Card>, response: Response<Respons_AddEditDeleteItem_Card>) {
                Log.i("ksdvknsdv", response.code().toString())
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
                            this@BaseActiity,
                            "" + code500?.getMessage(),
                            Toast.LENGTH_LONG
                    ).show()
                    return
                }
                if (response.isSuccessful) {
                    result.Data(response.body()?.data!!, "", true)
                    Log.i("fvsfknvsdggu", response.body().toString())
                }
                if (response.code() == 401) {
                    Login(securityKey, object : Login {
                        override fun onLoginCompleted(success: Boolean) {
                            if (success) {
                                AddEditDeleteItem_Card(Count, Id, result)
                            }
                        }

                    })
                }
            }

            override fun onFailure(call: Call<Respons_AddEditDeleteItem_Card>, t: Throwable) {
                Dial_Close()
                result.Data(-1, "مشکلی در اتصال به اینترنت به وجود آمده", false)
            }

        })

    }
    fun Login(Seacurity: String, loging: Login)
    {
        var json = ""
        json = JSONObject()
            .put("SecurityKey", Seacurity)
            .put("DeviceType", "1")
            .put("AppVersion", "1")
            .put("Imei", "reza")
            .toString()
        var textBody = RequestBody.create(MediaType.parse("text/plain"), json)
        var req: Call<LoginResponse> = api!!.login(textBody)
        req.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.i("djdjcjdcnc", response.code().toString())
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
                            this@BaseActiity,
                            "" + code500?.getMessage(),
                            Toast.LENGTH_LONG
                    ).show()
                    return
                }
                if (response.isSuccessful) {
                    Log.i("dvdgfghjkikgfgdfsds", "200")
                    var Data = response.body()?.data
                    Log.i("nimamorafigurg", Data?.securityKey.toString())
                    Log.i("mkoptr", Data?.token.toString())
                    sharedPreferences?.edit()?.putString(Constants.USER_TOKEN, Data?.token)?.apply()
                    sharedPreferences?.edit()?.putString(Constants.USER_SECURITY_KEY, Data?.securityKey)?.apply()
                    securityKey = Data?.securityKey.toString()
                    token = Data?.token.toString()
                    loging.onLoginCompleted(true)
                }
                else {
                    Log.i("dvdgfghjkikgfgdfsds", "not200")
                    var i = Intent(this@BaseActiity, MultyActivity_2::class.java)
                    i.putExtra("Type", "W")
                    startActivity(i)
                    finish()
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loging.onLoginCompleted(false)
                var p = Dialapp(2, "اتصال خود را به اینترنت بررسی کنید", object : Dial_App.Interface_new {
                    override fun News() {
                        finish()
                    }
                }, this@BaseActiity)
                p.show()
            }
        })
    }

    private fun Update_Data() {
        token = sharedPreferences?.getString(Constants.USER_TOKEN, "").toString()
        gender = sharedPreferences!!.getInt(Constants.USER_GENDER, 0)
        fullName= sharedPreferences!!.getString(Constants.USER_FULLNAME, "").toString()
        phoneNumber = sharedPreferences!!.getString(Constants.USER_PHONE_NUMBER, "")!!
        securityKey = sharedPreferences!!.getString(Constants.USER_SECURITY_KEY, "")!!
        api=AppStart.getApi()
    }
}