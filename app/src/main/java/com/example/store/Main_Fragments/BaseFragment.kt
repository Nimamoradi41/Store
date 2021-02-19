package com.example.store.Main_Fragments

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.store.*
import com.example.store.Dialog.Dial_App
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import kotlinx.android.synthetic.main.custome_dial_app.view.*
import kotlinx.android.synthetic.main.custome_dial_app.view.button7
import kotlinx.android.synthetic.main.custome_dial_app.view.imageView10
import kotlinx.android.synthetic.main.custome_dial_app.view.textView22
import kotlinx.android.synthetic.main.custome_dial_app_2.view.*
import kotlinx.android.synthetic.main.fragment_frag__phone__number.view.*
import kotlinx.android.synthetic.main.layout_loading.view.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

open class BaseFragment : Fragment() {
    var sharedPreferences: SharedPreferences? = null
    var mytag_sharedpreferences="Shop"
    var api:Api ?= null
    var phoneNumber = ""
    var securityKey = ""
    var token = ""
    var fullName = ""
    var gender = 0
    var storeName = ""
    var rulesUrl = ""
    var startWork = ""
    var endWork = ""
    var Density_Device=""
    var minPriceOrder = 0
    var id = ""
    var Dialog_load:Dialog ?=null
    fun getDensityName(context: Context): String? {
        val density = context.resources.displayMetrics.density
        if (density >= 4.0) {
            BaseActiity.textsize =18
            return "xxxhdpi"
        }
        if (density >= 3.0) {
            BaseActiity.textsize =16
            return "xxhdpi"
        }
        if (density >= 2.0) {
            BaseActiity.textsize =14
            return "xhdpi"
        }
        if (density >= 1.5) {
            BaseActiity.textsize =12
            return "hdpi"
        }
        return if (density >= 1.0) {
            BaseActiity.textsize =10
            "mdpi"
        } else  "ldpi"



    }
    public open fun  GetHome()
    {
        var req=api?.GETHOME("Bearer " + token)
        req?.enqueue(object : Callback<RESPOSNHOME> {
            override fun onResponse(call: Call<RESPOSNHOME>, response: Response<RESPOSNHOME>) {
                Log.i("zcvmzkmvzkmvmzv", response.code().toString())
                if (response.isSuccessful) {
                    MainActivity.mainActivityViewModel?.data?.value = response.body()?.data
                    MainActivity.mainActivityViewModel?.count?.value = response.body()?.data?.cartCount
                }
                if (response.code() == 401) {
                    Login(securityKey, object : Login {
                        override fun onLoginCompleted(success: Boolean) {
                            if (success) {
                                GetHome()
                            }
                        }

                    })
                }
            }

            override fun onFailure(call: Call<RESPOSNHOME>, t: Throwable) {
                var I = 3
                var p = Dialapp(I, "لطفا دوباره تلاش کنید", object : Dial_App.Interface_new {
                    override fun News() {
                        GetHome()
                    }
                }, activity!!)

                p.show()

//                var v:BaseActiity=this@BaseActiity
//                if ((context is ac)v.isFinishing) {
//                    p.show()
//                }


            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        sharedPreferences = activity?.getSharedPreferences(
            mytag_sharedpreferences, AppCompatActivity.MODE_PRIVATE
        )


        Log.i("vnnnvnvnvnnvnv",securityKey)
        Update_Data()
        Density_Device = getDensityName(requireActivity()).toString()

    }


    public fun DialLoad( ) {
        Dialog_load = Dialog(requireActivity())
        Dialog_load?.setCancelable(false)
        val inflater = LayoutInflater.from(requireActivity())
        val view: View = inflater.inflate(R.layout.layout_loading, null, false)
        Dialog_load?.setContentView(view)
        Dialog_load?.window?.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT)
        Dialog_load?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        Dialog_load?.show()






    }
    public fun Dialog_Ask(Type: Int, S: String, I: Dial_App.Interface_new_2, context: Context):Dialog {
        var d = Dialog(context)
        val inflater = LayoutInflater.from(context)
        d.setCancelable(false)
        val view: View = inflater.inflate(R.layout.custome_dial_app_2, null, false)
        view.textView22.setText(S)
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


        view.button11.setOnClickListener {
            d.dismiss()
            I.News("B")
        }

        view.button7.setOnClickListener {
            d.dismiss()
            I.News("A")
        }
        return d
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

       fun   AddEditDeleteItem_Card( Count:Int, Id:String,result:Resuilt)
    {
        DialLoad()
        var data_item=AddEditDeleteItemModel()
        data_item.Count= Count.toString()
        data_item.ProductId=Id
        Log.i("cldvmfmlbmgblfb",Count.toString())
        Log.i("cldvmfmlbmgblfb", Id)
        var req=api?.AddEditDeleteItem_Card("Bearer " +token,data_item)
        req?.enqueue(object :Callback<Respons_AddEditDeleteItem_Card> {
            override fun onResponse(call: Call<Respons_AddEditDeleteItem_Card>, response: Response<Respons_AddEditDeleteItem_Card>) {
              Dial_Close()
                Log.i("ksdvknsdv", response.code().toString())
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
                    return
                }
                if (response.isSuccessful)
                {
                    result.Data(response.body()?.data!!,"",true)
                    Log.i("fvsfknvsdggu", response.body().toString())
                }
                if (response.code()==401)
                {
                    Login(securityKey,object :Login{
                        override fun onLoginCompleted(success: Boolean) {
                            if (success)
                            {
                                AddEditDeleteItem_Card(Count,Id,result)
                            }
                        }

                    })
                }
            }

            override fun onFailure(call: Call<Respons_AddEditDeleteItem_Card>, t: Throwable) {
                Dial_Close()
                Log.i("ksdvknsdv", t.message.toString())
                result.Data(-1,"مشکلی در اتصال به اینترنت به وجود آمده",false)
                var I=3
                var p=   Dialapp(I,"لطفا دوباره تلاش کنید",object : Dial_App.Interface_new{
                    override fun News() {
                        AddEditDeleteItem_Card(Count,Id,result)
                    }
                }, context!!)
                p.show()
            }

        })

    }

    fun Login(Seacurity:String,loging:Login )
    {
        var json = ""
        try {
            json = JSONObject()
                .put("SecurityKey", Seacurity)
                .put("DeviceType", "1")
                .put("AppVersion", "1")
                .put("Imei", "reza")
                .toString()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        var textBody = RequestBody.create(MediaType.parse("text/plain"), json)
        var req: Call<LoginResponse> = api!!.login(textBody)
        req.enqueue(object :Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.i("djdjcjdcnc_2", response.code().toString())
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
                    Log.i("dvdgfghjkikgfgdfsds","200")
                    Log.i("dvdgfghjkikgfgdfsds","200")
                    Log.i("poypr","A")
                    var Data=response.body()?.data
                    sharedPreferences?.edit()?.putString(Constants.USER_TOKEN,Data?.token)?.apply()
                    sharedPreferences?.edit()?.putString(Constants.USER_SECURITY_KEY,Data?.securityKey)?.apply()
                    sharedPreferences?.edit()?.putString(Constants.USER_PHONE_NUMBER,phoneNumber)?.apply()
                    sharedPreferences?.edit()?.putString(Constants.storeName, Data?.storeSetting?.storeName)?.apply()
                    sharedPreferences?.edit()?.putString(Constants.rulesUrl, Data?.storeSetting?.rulesUrl)?.apply()
                    sharedPreferences?.edit()?.putString(Constants.startWork, Data?.storeSetting?.startWork)?.apply()
                    sharedPreferences?.edit()?.putString(Constants.endWork, Data?.storeSetting?.endWork)?.apply()
                    sharedPreferences?.edit()?.putString(Constants.minPriceOrder, Data?.storeSetting?.minPriceOrder.toString())?.apply()
                    sharedPreferences?.edit()?.putString(Constants.id_2, Data?.storeSetting?.id.toString())?.apply()
                    securityKey = Data?.securityKey.toString()
                    storeName = Data?.storeSetting?.storeName.toString()
                    rulesUrl = Data?.storeSetting?.rulesUrl.toString()
                    sharedPreferences?.edit()?.putString(Constants.fullName, Data?.fullName?.toString())?.apply()
                    fullName= Data?.fullName!!
                    startWork = Data?.storeSetting?.startWork.toString()
                    endWork = Data?.storeSetting?.endWork.toString()
                    minPriceOrder = Data?.storeSetting?.minPriceOrder!!
                    id = Data.storeSetting?.id.toString()
                    securityKey= Data?.securityKey.toString()
                    token= Data?.token.toString()
                    phoneNumber=phoneNumber
                    Log.i("wwdsazcwdf", Data?.securityKey.toString())
                    Log.i("mkoptr", Data?.token.toString())
                    loging.onLoginCompleted(true)
                }else{
                    loging.onLoginCompleted(false)
                    Log.i("dvdgfghjkikgfgdfsds","not200")
                    var i=Intent(activity,Actvity_Confirm::class.java)
                    i.putExtra("Type","W")
                    startActivity(i)
                    activity?.finish()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loging.onLoginCompleted(false)
                var i=Intent(context,Actvity_Confirm::class.java)
                i.putExtra("Type","W")
                startActivity(i)
                activity?.finish()
            }

        })
    }


    fun isNetConnected(): Boolean {
        val cn = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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
    private fun Update_Data() {
        token = sharedPreferences?.getString(Constants.USER_TOKEN, "").toString()
        gender = sharedPreferences!!.getInt(Constants.USER_GENDER, 0)
        fullName= sharedPreferences!!.getString(Constants.USER_FULLNAME, "").toString()
        phoneNumber = sharedPreferences!!.getString(Constants.USER_PHONE_NUMBER, "")!!
        securityKey = sharedPreferences!!.getString(Constants.USER_SECURITY_KEY, "")!!
        storeName= sharedPreferences?.getString(Constants.storeName,"").toString()!!
        rulesUrl=sharedPreferences?.getString(Constants.rulesUrl,"").toString()!!
        api=AppStart.getApi()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base2, container, false)
    }



}