package com.example.store

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Environment
import android.provider.Settings
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.store.Main_Fragments.ErrorCode500
import com.example.store.Main_Fragments.Login
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.koushikdutta.ion.Ion
import com.koushikdutta.ion.ProgressCallback
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity__pass_word.*
import kotlinx.android.synthetic.main.activity__pass_word.editTextNumber1
import kotlinx.android.synthetic.main.activity__pass_word.editTextNumber2
import kotlinx.android.synthetic.main.activity__pass_word.editTextNumber3
import kotlinx.android.synthetic.main.activity__pass_word.editTextNumber4
import kotlinx.android.synthetic.main.activity__pass_word.textView18
import kotlinx.android.synthetic.main.activity_actvity__confirm.*
import kotlinx.android.synthetic.main.fragment_frag_verfivcation.*
import kotlinx.android.synthetic.main.fragment_frag_verfivcation.view.*
import kotlinx.android.synthetic.main.fragment_frag_verfivcation_3.view.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException


class Activity_PassWord : BaseActiity() {
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__pass_word)
//        window.statusBarColor= Color.parseColor("#ec4646")
        editTextNumber1.requestFocus()
        textView90.alpha=0.2f
        textView90.isEnabled=false
        textView90.setOnClickListener {
            if (it.isEnabled)
            {
               Confirm_1()
            }
        }
        phoneNumber= intent.getStringExtra("data").toString()
        imageView57.setOnClickListener {
            finish()
        }
        textView18.setText(Html.fromHtml("  کد ارسال شده  به شماره <font color='#902C2C2C'><b>  $phoneNumber </b></font> را وارد کنید "))
        phoneNumber= intent.getStringExtra("data").toString()
        button12_3.setOnClickListener {
            if (editTextNumber2.text.trim().toString().isNullOrEmpty())
            {
                Snackbar.make(holder_4, "کد را وارد کنید", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (editTextNumber1.text.trim().toString().isNullOrEmpty())
            {
                Snackbar.make(holder_4, "کد را وارد کنید", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (editTextNumber3.text.trim().toString().isNullOrEmpty())
            {
                Snackbar.make(holder_4, "کد را وارد کنید", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (editTextNumber4.text.trim().toString().isNullOrEmpty())
            {
                Snackbar.make(holder_4, "کد را وارد کنید", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Log.i("bwrwrweessa", phoneNumber)
            Confirm_2()
        }
        TextWTACHERS()
        TextWTACHERS_Ontoch()
        Start_Counter()
    }
    fun Start_Counter()
    {
        textView90.isEnabled=false
        textView90.alpha=0.5f
        val maxCounter: Long = 30000
        val diff: Long = 1000

        object : CountDownTimer(maxCounter, diff) {
            override fun onTick(millisUntilFinished: Long) {
                val diff = maxCounter - millisUntilFinished
                //here you can have your logic to set text to edittext
            }

            override fun onFinish() {
                textView90.isEnabled=true
                textView90.alpha=1f
            }
        }.start()
    }
    fun  Confirm_1()
    {
        if (isNetConnected())
        {
            DialLoad()
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
                                this@Activity_PassWord,
                                "" + code500?.getMessage(),
                                Toast.LENGTH_LONG
                        ).show()
                        return
                    }
                    if (response.isSuccessful) {
                        if (response.body()?.isSuccess!!) {
                            if (response.body()?.data != null) {
                                if (response.body()?.data?.status == 1) {
//                                    var i = Intent(this@Activity_PassWord, Activity_PassWord::class.java)
////                                    i.putExtra("Type", "Z")
//                                    i.putExtra("data", phoneNumber)
//                                    startActivity(i)
                                    Start_Counter()
//                                    finish()
                                } else {
                                    Snackbar.make(holder_2, "مشکلی در ورود به سیستم به وجود آمده", Snackbar.LENGTH_SHORT).show()
                                }
                            } else {
                                Snackbar.make(holder_2, "مشکلی در ورود به سیستم به وجود آمده", Snackbar.LENGTH_SHORT).show()
                            }
                        } else {
                            Snackbar.make(holder_2, "مشکلی در ورود به سیستم به وجود آمده", Snackbar.LENGTH_SHORT).show()
                        }
//                         sharedPreferences?.edit()?.putString(Constants.USER_PHONE_NUMBER, phoneNumber)?.apply()

                    } else {
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
    fun  Confirm_2()
    {
        if (isNetConnected())
        {
            Start_Counter()
            DialLoad()
            var vvs=editTextNumber1.text.toString()+
                    editTextNumber2.text.toString()+
                    editTextNumber3.text.toString()+
                    editTextNumber4.text.toString()
            Log.i("skvnsljvsnl",vvs)
            var json=""
            Log.i("vmmvdlsjsgvxfsfxsgcd", json)
            json= JSONObject().put("confirmCode", vvs)
                .put("phone", phoneNumber).toString()
            var body= RequestBody.create(MediaType.parse("text/plain"), json)
            var call=api?.confirmSms(body)
            Log.i("vmmvdlsjsgvxfsfxsgcd", json)
            call?.enqueue(object : Callback<ConfirmSmsResponse> {
                override fun onResponse(
                    call: Call<ConfirmSmsResponse>, response: Response<ConfirmSmsResponse>
                ) {
                    Dial_Close()
                    Log.i("dftfuwtefduac", response.code().toString())
//                    Log.i("dftfuwtefduac", response.body()?.statusCode.toString())
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
                            this@Activity_PassWord,
                            "" + code500?.getMessage(),
                            Toast.LENGTH_LONG
                        ).show()
                        return
                    }
                    if (response.isSuccessful) {
                        if (response.body()?.data?.status==2)
                        {
                            Log.i("lmdldlvdllmdvlmvdlmv", response.body()?.data?.securityKey.toString())
                            sharedPreferences?.edit()?.putString(
                                Constants.USER_SECURITY_KEY,
                                response.body()?.data?.securityKey
                            )?.apply()
                            sharedPreferences?.edit()?.putString(
                                Constants.USER_PHONE_NUMBER,
                                phoneNumber
                            )?.apply()
                            securityKey = response.body()?.data?.securityKey.toString()
                            var json = ""
                            try {
                                json = JSONObject()
                                    .put("SecurityKey", securityKey)
                                    .put("DeviceType", "1")
                                    .put("AppVersion", "1")
                                    .put("Imei", "reza")
                                    .toString()
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                            DialLoad()
                            var textBody = RequestBody.create(MediaType.parse("text/plain"), json)
                            var req: Call<LoginResponse> = api!!.login(textBody)
                            req.enqueue(object : Callback<LoginResponse> {
                                override fun onResponse(
                                    call: Call<LoginResponse>,
                                    response: Response<LoginResponse>
                                ) {
                                    Dial_Close()
                                    Log.i("djdjcjdcnc_2", response.code().toString())
                                    if (response.code() == 500) {
                                        var code500: ErrorCode500? = null
                                        val gson = Gson()
                                        val adapter: TypeAdapter<ErrorCode500> =
                                            gson.getAdapter(ErrorCode500::class.java)
                                        try {
                                            if (response.errorBody() != null) code500 =
                                                adapter.fromJson(
                                                    response.errorBody()!!.string()
                                                )
                                        } catch (e: IOException) {
                                            e.printStackTrace()
                                        }
                                        Toast.makeText(
                                            this@Activity_PassWord,
                                            "" + code500?.getMessage(),
                                            Toast.LENGTH_LONG
                                        ).show()
                                        return
                                    }
                                    if (response.isSuccessful) {
                                        Log.i("dvdgfghjkikgfgdfsds", "200")
                                        Log.i("dvdgfghjkikgfgdfsds", "200")
                                        Log.i("poypr", "A")
                                        if(!response.body()?.data?.appVersion?.allowedToLogin!!)
                                        {
                                            Dexter.withActivity(this@Activity_PassWord).withPermissions(
                                                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                                            ).withListener(object : MultiplePermissionsListener {
                                                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                                                    if (report.areAllPermissionsGranted()) {
                                                        if (isNetConnected()) {
                                                            Log.i("sdvkadnv","A")
                                                            Log.i("svknasnvjdnvaka","True")
                                                            val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/Store.apk")
                                                            if (file.exists()) file.delete()
                                                            var url = response.body()?.data?.appVersion?.url
                                                            if (!url?.startsWith("http://")!! && !url.startsWith("https://")) url = "http://$url"
                                                            Ion.with(this@Activity_PassWord)
                                                                .load(url)
                                                                .progressHandler(ProgressCallback { downloaded, total ->

                                                                    Log.i("MyTagg", "onProgress: $downloaded : $total")
                                                                })
                                                                .write(file)
                                                                .setCallback { e, result ->
                                                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                                                        if (!packageManager.canRequestPackageInstalls()) {
                                                                            Log.i("dvnkavjnanjvanv","UIYIYI")
                                                                            startActivityForResult(Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES).setData(
                                                                                Uri.parse(String.format("package:%s", packageName))), 1234)
                                                                        } else {
                                                                            Log.i("dvnkavjnanjvanv","ASD")
                                                                            InstallUpdate(result!!)
                                                                        }
                                                                    } else {
                                                                        Log.i("dvnkavjnanjvanv","HJKHKK")
                                                                        InstallUpdate(result!!)
                                                                    }
                                                                }
                                                        }
                                                    } else {
                                                        if (report.isAnyPermissionPermanentlyDenied) {
                                                            Toast.makeText(this@Activity_PassWord,"با توجه به اینکه شما مجوزهای دسترسی را قبول نکردید ، امکان استفاده از اپلیکیشن وجود ندارد",Toast.LENGTH_LONG).show()
                                                        }
                                                    }
                                                }

                                                override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>, token: PermissionToken) {
                                                    Toast.makeText(this@Activity_PassWord,"با توجه به اینکه شما مجوزهای دسترسی را قبول نکردید ، امکان استفاده از اپلیکیشن وجود ندارد",Toast.LENGTH_LONG).show()
                                                }
                                            }).check()
                                        }
                                        else{
                                            var Data = response.body()?.data
                                            sharedPreferences?.edit()?.putString(
                                                Constants.USER_TOKEN,
                                                Data?.token
                                            )?.apply()
                                            sharedPreferences?.edit()?.putString(
                                                Constants.USER_SECURITY_KEY,
                                                Data?.securityKey
                                            )?.apply()
                                            sharedPreferences?.edit()?.putString(
                                                Constants.USER_PHONE_NUMBER,
                                                phoneNumber
                                            )?.apply()
                                            sharedPreferences?.edit()?.putString(
                                                Constants.USER_PHONE_NUMBER,
                                                phoneNumber
                                            )?.apply()
                                            sharedPreferences?.edit()?.putString(
                                                Constants.storeName,
                                                Data?.storeSetting?.storeName
                                            )?.apply()
                                            sharedPreferences?.edit()?.putString(
                                                Constants.rulesUrl,
                                                Data?.storeSetting?.rulesUrl
                                            )?.apply()
                                            sharedPreferences?.edit()?.putString(
                                                Constants.startWork,
                                                Data?.storeSetting?.startWork
                                            )?.apply()
                                            sharedPreferences?.edit()?.putString(
                                                Constants.endWork,
                                                Data?.storeSetting?.endWork
                                            )?.apply()
                                            sharedPreferences?.edit()?.putString(
                                                Constants.minPriceOrder,
                                                Data?.storeSetting?.minPriceOrder.toString()
                                            )?.apply()
                                            sharedPreferences?.edit()?.putString(
                                                Constants.id_2,
                                                Data?.storeSetting?.id.toString()
                                            )?.apply()
                                            securityKey = Data?.securityKey.toString()
                                            token = Data?.token.toString()
                                            storeName = Data?.storeSetting?.storeName.toString()
                                            rulesUrl = Data?.storeSetting?.rulesUrl.toString()
                                            sharedPreferences?.edit()?.putString(
                                                Constants.fullName,
                                                Data?.fullName?.toString()
                                            )?.apply()
                                            fullName = Data?.fullName!!
                                            startWork = Data?.storeSetting?.startWork.toString()
                                            endWork = Data?.storeSetting?.endWork.toString()
                                            minPriceOrder = Data?.storeSetting?.minPriceOrder.toString()!!
                                            id_Store = Data.storeSetting?.id.toString()
                                            securityKey = Data?.securityKey.toString()
                                            token = Data?.token.toString()
//                                    phoneNumber=phoneNumber
                                            phoneNumber = phoneNumber
                                            Log.i("wwdsazcwdf", Data?.securityKey.toString())
                                            Log.i("mkoptr", Data?.token.toString())
                                            GetHome()
                                        }
                                    } else {
                                        Dial_Close()
                                        Log.i("sdvkskvkas","A")
                                        Snackbar.make(
                                            holder_4,
                                            "کد وارد  شده اشتباه می باشد",
                                            Snackbar.LENGTH_SHORT
                                        ).show()
//                                    Log.i("dvdgfghjkikgfgdfsds","not200")
//                                    var i=Intent(activity,MultyActivity_2::class.java)
//                                    i.putExtra("Type","W")
//                                    startActivity(i)
//                                    activity?.finish()
                                    }
                                }
                                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                                loging.onLoginCompleted(false)
                                    Dial_Close()
//                                var i=Intent(context,MultyActivity_2::class.java)
//                                i.putExtra("Type","W")
//                                startActivity(i)
//                                activity?.finish()
                                }

                            })
                        }else{
                            Snackbar.make(holder_4, "کد اعتبارسنجی وارد شده اشتباه می باشد", Snackbar.LENGTH_SHORT).show()
                        }
//                        Login(response.body()?.data?.securityKey.toString(),object  :Login{
//                            override fun onLoginCompleted(success: Boolean) {
//                                if (success)
//                                {
//                                    Log.i("vvmvmmvfkfkfbbn","A")
//                                    GetHome()
//                                }
//                            }
//
//                        })

                    }
                }

                override fun onFailure(call: Call<ConfirmSmsResponse>, t: Throwable) {
                    Dial_Close()
                    Log.i("dvkmfkvkdmvs", t.message.toString())
                    Snackbar.make(holder_4, "دوباره تلاش کنید", Snackbar.LENGTH_SHORT).show()
                }

            })




        }else{
            Snackbar.make(holder_4, "اتصال خود را به اینترنت چک کنید", Snackbar.LENGTH_SHORT).show()
        }
    }
    override fun  GetHome()
    {
        var req=api?.GETHOME("Bearer " + token)
        req?.enqueue(object : Callback<RESPOSNHOME> {
            override fun onResponse(call: Call<RESPOSNHOME>, response: Response<RESPOSNHOME>) {
                Dial_Close()
                Log.i("zcvmzkmvzkmvmzv", response.code().toString())
                if (response.isSuccessful) {
                    Actvity_Confirm.Act?.finish()
                    var I = Intent(this@Activity_PassWord, MainActivity::class.java)
                    I.putExtra("DATA", response.body()?.getData())
                    startActivity(I)
                    finish()
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
                Log.i("fsmvbsmlfbad", t.message.toString())
                Dial_Close()
                Snackbar.make(holder_4, "اتصال خود را به اینترنت چک کنید", Snackbar.LENGTH_SHORT).show()
            }

        })

    }
    fun TextWTACHERS() {
        editTextNumber1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                if (editTextTextPersonName.text.trim())
                if (p0.toString().trim().isEmpty()) {
//                    V.editTextNumber1.setBackgroundResource(R.drawable.ic_box1)
                } else {
//                    V.editTextNumber1.setBackgroundResource(R.drawable.ic_box_2)
                    editTextNumber2.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        editTextNumber2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().trim().isEmpty()) {
//                    V.editTextNumber2.setBackgroundResource(R.drawable.ic_box1)
                    editTextNumber1.requestFocus()
                } else {
//                    V.editTextNumber2.setBackgroundResource(R.drawable.ic_box_2)
                    editTextNumber3.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        editTextNumber3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (editTextNumber3.text.trim().isEmpty()) {
//                    V.editTextNumber3.setBackgroundResource(R.drawable.ic_box1)
                    editTextNumber2.requestFocus()
                } else {
//                    V.editTextNumber3.setBackgroundResource(R.drawable.ic_box_2)
                    editTextNumber4.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        editTextNumber4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().isEmpty()) {
//                    V.editTextNumber4.setBackgroundResource(R.drawable.ic_box1)
                    editTextNumber3.requestFocus()
                } else {
//                    V.editTextNumber4.setBackgroundResource(R.drawable.ic_box_2)

                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }
    fun TextWTACHERS_Ontoch() {
        editTextNumber1.setOnTouchListener(View.OnTouchListener { v, event ->
            editTextNumber1.isCursorVisible = false
//            editTextTextPersonName.setFocusableInTouchMode(true)
            false
        })
        editTextNumber2.setOnTouchListener(View.OnTouchListener { v, event ->
            editTextNumber2.isCursorVisible = false
//            editTextTextPersonName.setFocusableInTouchMode(true)
            false
        })
        editTextNumber3.setOnTouchListener(View.OnTouchListener { v, event ->
            editTextNumber3.isCursorVisible = false
//            editTextTextPersonName.setFocusableInTouchMode(true)
            false
        })
        editTextNumber4.setOnTouchListener(View.OnTouchListener { v, event ->
            editTextNumber4.isCursorVisible = false
//            editTextTextPersonName.setFocusableInTouchMode(true)
            false
        })

    }
}