package com.example.store

import android.Manifest
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.TypedValue
import androidx.core.animation.doOnEnd
import androidx.core.app.ActivityCompat
import com.example.store.Dialog.Dial_App
import com.example.store.Main_Fragments.Login
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreen : BaseActiity() {
    var animate=false
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        textsize= getDensityName(this)!!
        Log.i("jkhhloo", textsize.toString())
//        textView82.setTextSize(TypedValue.COMPLEX_UNIT_SP, textsize.toFloat())
//        textView82.textSize=
        Log.i("caskvskvas", textView82.layoutParams.width.toString())
        var va=ValueAnimator.ofInt(0,450)
        va.addUpdateListener {
            textView82.layoutParams.width= it.animatedValue as Int
            textView82.requestLayout()
        }
        va.setDuration(2000).doOnEnd {
            if (!isNetConnected())
            {
                Log.i("toldmsfgth", "1")
                var I=2;
                var p=   Dialapp(2, "اتصال خود را به اینترنت بررسی کنید", object : Dial_App.Interface_new {
                    override fun News() {
                        finish()
                    }
                }, this)
                p.show()
                return@doOnEnd
            }
            Log.i("GNHJMK", "2")
            if (securityKey.isNullOrEmpty())
            {
                Handler().postDelayed(Runnable {
                    var i = Intent(this, Actvity_Confirm::class.java)
                    startActivity(i)
                    finish()
                }, 2000)
            } else{
                Login(securityKey, object : Login {
                    override fun onLoginCompleted(success: Boolean) {
                        if (success) {
                            GetHome_Splash()
//                       GetHome()
//                       startActivity(Intent(this@SplashScreen,MainActivity::class.java))
                        }
                    }
                })
            }
        }
        va.start()
        Log.i("GNHJMK", token)
//        window.statusBarColor=Color.parseColor("#ec4646")

    }
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }



}