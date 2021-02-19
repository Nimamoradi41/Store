package com.example.store

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.example.store.Dialog.Dial_App
import com.example.store.Main_Fragments.ErrorCode500
import com.example.store.Main_Fragments.Login
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.karumi.dexter.Dexter
import com.karumi.dexter.DexterBuilder
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.koushikdutta.async.future.FutureCallback
import com.koushikdutta.ion.Ion
import com.koushikdutta.ion.ProgressCallback
import kotlinx.android.synthetic.main.custome_dial_app.view.*
import kotlinx.android.synthetic.main.custome_dial_app.view.button7
import kotlinx.android.synthetic.main.custome_dial_app.view.imageView10
import kotlinx.android.synthetic.main.custome_dial_app.view.textView22
import kotlinx.android.synthetic.main.custome_dial_app_2.view.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException
import java.util.jar.Manifest


open class BaseActiity : AppCompatActivity() {
    var sharedPreferences: SharedPreferences? = null
    var mytag_sharedpreferences="Shop"
    var phoneNumber = ""
    var api:Api ?= null
    var fullName = ""
    var gender = 0
    var storeName = ""
    var rulesUrl = ""
    var startWork = ""
    var endWork = ""
    var minPriceOrder = ""
    var id_Store = ""
    var token = ""
    var securityKey = ""
    var Density_Device=""
    var Dialog_load:Dialog?=null
    companion object{
        var api:Api ?= null
        var textsize=0
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
                }, this@BaseActiity)
                var v: BaseActiity = this@BaseActiity
                if (v.isFinishing) {
                    p.show()
                }


            }

        })
    }
    public open fun  GetHome_Splash()
    {
        var req=api?.GETHOME("Bearer " + token)
        req?.enqueue(object : Callback<RESPOSNHOME> {
            override fun onResponse(call: Call<RESPOSNHOME>, response: Response<RESPOSNHOME>) {
                Log.i("zcvmzkmvzkmvmzv", response.code().toString())
                if (response.isSuccessful) {
                    var vs = Intent(this@BaseActiity, MainActivity::class.java)
                    vs.putExtra("DATA", response.body()?.data)
                    startActivity(vs)
                    finish()
//                    MainActivity.mainActivityViewModel?.data?.value=response.body()?.data
//                    MainActivity.mainActivityViewModel?.count?.value=response.body()?.data?.cartCount

                }
                if (response.code() == 401) {
                    Login(securityKey, object : Login {
                        override fun onLoginCompleted(success: Boolean) {
                            if (success) {
                                GetHome_Splash()
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
                }, this@BaseActiity)
                p.show()

            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        textsize = getDensityName(this)?.toInt()!!
        sharedPreferences = getSharedPreferences(mytag_sharedpreferences, MODE_PRIVATE)
        Update_Data()
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.heightPixels
        val wi = width.toDouble() / dm.xdpi.toDouble()
        val hi = height.toDouble() / dm.ydpi.toDouble()
        val x = Math.pow(wi, 2.0)
        val y = Math.pow(hi, 2.0)
        val screenInches = Math.sqrt(x + y)
        val densityDpi = (dm.density * 160f)
        Log.i("vbshdvasdhcva", "${screenInches.toFloat()}")
        Log.i("vbshdvasdhcva", "${resources.displayMetrics.density}")
        Log.i("vbshdvasdhcva_3", "${densityDpi}")

        // MyDevice 6.0593605
        // MyDevice 6.0593605






    }
      fun getDensityName(context: Context): Int? {
        val density = context.resources.displayMetrics.density
        if (density >= 4.0) {
            textsize=18
            sharedPreferences?.edit()?.putInt("textsize",18)?.apply()
//            return "xxxhdpi"
            return 18
        }
        if (density >= 3.0) {
            textsize=16
            sharedPreferences?.edit()?.putInt("textsize",16)?.apply()
//            return "xxhdpi"
            return 16
        }
        if (density >= 2.0) {
            textsize=14
            sharedPreferences?.edit()?.putInt("textsize",14)?.apply()
//            return "xhdpi"
            return 14
        }
        if (density >= 1.5) {
            textsize=12
            sharedPreferences?.edit()?.putInt("textsize",12)?.apply()
//            return "hdpi"
            return 12
        }
        return if (density >= 1.0) {
            textsize=10
            sharedPreferences?.edit()?.putInt("textsize",10)?.apply()
//            "mdpi"
            10
        } else  10



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
                    Dial_Close()
                    if(!response.body()?.data?.appVersion?.allowedToLogin!!)
                    {
                        Dexter.withActivity(this@BaseActiity).withPermissions(
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
                                        Ion.with(this@BaseActiity)
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
                                        Toast.makeText(this@BaseActiity,"با توجه به اینکه شما مجوزهای دسترسی را قبول نکردید ، امکان استفاده از اپلیکیشن وجود ندارد",Toast.LENGTH_LONG).show()
                                    }
                                }
                            }

                            override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>, token: PermissionToken) {
                                Toast.makeText(this@BaseActiity,"با توجه به اینکه شما مجوزهای دسترسی را قبول نکردید ، امکان استفاده از اپلیکیشن وجود ندارد",Toast.LENGTH_LONG).show()
                            }
                        }).check()
                    }
                        else{
                        var Data = response.body()?.data
                        Log.i("nimamorafigurg", Data?.securityKey.toString())
                        Log.i("mkoptr", Data?.token.toString())
                        sharedPreferences?.edit()?.putString(Constants.USER_TOKEN, Data?.token)?.apply()
                        sharedPreferences?.edit()?.putString(Constants.USER_SECURITY_KEY, Data?.securityKey)?.apply()
                        sharedPreferences?.edit()?.putString(Constants.storeName, Data?.storeSetting?.storeName)?.apply()
                        sharedPreferences?.edit()?.putString(Constants.rulesUrl, Data?.storeSetting?.rulesUrl)?.apply()
                        sharedPreferences?.edit()?.putString(Constants.startWork, Data?.storeSetting?.startWork)?.apply()
                        sharedPreferences?.edit()?.putString(Constants.endWork, Data?.storeSetting?.endWork)?.apply()
                        sharedPreferences?.edit()?.putString(Constants.minPriceOrder, Data?.storeSetting?.minPriceOrder!!.toString())?.apply()
                        sharedPreferences?.edit()?.putString(Constants.id_2, Data?.storeSetting?.id.toString())?.apply()
                        sharedPreferences?.edit()?.putString(Constants.fullName, Data?.fullName?.toString())?.apply()
                        fullName = Data?.fullName!!
                        securityKey = Data?.securityKey.toString()
                        fullName = Data?.fullName!!
                        storeName = Data?.storeSetting?.storeName.toString()
                        rulesUrl = Data?.storeSetting?.rulesUrl.toString()
                        startWork = Data?.storeSetting?.startWork.toString()
                        endWork = Data?.storeSetting?.endWork.toString()
                        minPriceOrder = Data?.storeSetting?.minPriceOrder.toString()!!
                        id_Store = Data.storeSetting?.id.toString()
                        token = Data?.token.toString()
                        loging.onLoginCompleted(true)
                    }

                } else {
                    Log.i("dvdgfghjkikgfgdfsds", "not200")
                    var i = Intent(this@BaseActiity, Actvity_Confirm::class.java)
                    i.putExtra("Type", "W")
                    startActivity(i)
                    finish()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loging.onLoginCompleted(false)
                Dial_Close()
                var p = Dialapp(2, "اتصال خود را به اینترنت بررسی کنید", object : Dial_App.Interface_new {
                    override fun News() {
                        finish()
                    }
                }, this@BaseActiity)
                p.show()
            }
        })
    }

    fun InstallUpdate(result: File) {
        val intent: Intent
        Log.i("ahbahbcabhs","F")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.i("ahbahbcabhs","A")
//            val apkUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID +".fileprovider", result)
            val apkUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID +".contentprovider", result)
            Log.i("ahbahbcabhs","c")
            intent = Intent(Intent.ACTION_INSTALL_PACKAGE)
            intent.data = apkUri
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        } else {
            Log.i("ahbahbcabhs","B")
            val apkUri = Uri.fromFile(result)
            intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive")
            Log.i("ahbahbcabhs","F")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
        finish()
    }
    private fun Update_Data() {
        token = sharedPreferences?.getString(Constants.USER_TOKEN, "").toString()
        textsize= sharedPreferences?.getInt("testsize",0)!!
        gender = sharedPreferences!!.getInt(Constants.USER_GENDER, 0)
        fullName= sharedPreferences!!.getString(Constants.fullName, "").toString()
        phoneNumber = sharedPreferences!!.getString(Constants.USER_PHONE_NUMBER, "")!!
        securityKey = sharedPreferences!!.getString(Constants.USER_SECURITY_KEY, "")!!
        storeName = sharedPreferences!!.getString(Constants.storeName, "")!!
        rulesUrl = sharedPreferences!!.getString(Constants.rulesUrl, "")!!
        startWork = sharedPreferences!!.getString(Constants.startWork, "")!!
        endWork = sharedPreferences!!.getString(Constants.endWork, "")!!
        minPriceOrder = sharedPreferences!!.getString(Constants.minPriceOrder, "").toString()
        id_Store = sharedPreferences!!.getString(Constants.id_2, "")!!
        api=AppStart.getApi()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1234 && resultCode == Activity.RESULT_OK) {
            if (packageManager.canRequestPackageInstalls()) {
                InstallUpdate(File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/Store.apk"))
            }

        }
    }
}