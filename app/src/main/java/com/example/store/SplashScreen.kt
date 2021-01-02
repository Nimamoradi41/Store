package com.example.store

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.example.store.Dialog.Dial_App
import com.example.store.Main_Fragments.Login
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class SplashScreen : BaseActiity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Log.i("GNHJMK",token)
        if (!isNetConnected())
        {
            Log.i("toldmsfgth","1")
            var I=2;
         var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object :Dial_App.Interface_new{
                override fun News() {
                     finish()
                }
            },this)
             p.show()
            return
        }
        Log.i("GNHJMK","2")
        if (securityKey.isNullOrEmpty())
        {
            Handler().postDelayed(Runnable {
               var i=Intent(this,MultyActivity_2::class.java)
               i.putExtra("Type","W")
               startActivity(i)
                finish()
            },2000)
        } else{
          Login(securityKey,object :Login{
              override fun onLoginCompleted(success: Boolean) {
                   if (success)
                   {

                       GetHome()
//                      GetHome()
//                       startActivity(Intent(this@SplashScreen,MainActivity::class.java))
                   }
              }
          })
        }
    }
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }

    fun  GetHome()
    {
    var req=api?.GETHOME("Bearer " +token)
        req?.enqueue(object :Callback<RESPOSNHOME>{
            override fun onResponse(call: Call<RESPOSNHOME>, response: Response<RESPOSNHOME>) {
              Log.i("zcvmzkmvzkmvmzv",response.code().toString())
              if (response.isSuccessful)
              {
                 var I=Intent(this@SplashScreen,MainActivity::class.java)
                 I.putExtra("DATA",response.body()?.getData())
                 startActivity(I)
                 finish()
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
                var I=3
                var p=   Dialapp(I,"لطفا دوباره تلاش کنید",object :Dial_App.Interface_new{
                    override fun News() {
                       GetHome()
                    }
                },this@SplashScreen)
                p.show()

            }

        })
    }

}