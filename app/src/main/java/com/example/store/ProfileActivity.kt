package com.example.store
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.store.Dialog.Dial_App
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.imageView23
import kotlinx.android.synthetic.main.activity_profile.imageView24
import kotlinx.android.synthetic.main.activity_profile.imageView25
import kotlinx.android.synthetic.main.activity_profile.imageView26
import kotlinx.android.synthetic.main.activity_profile.imageView27
import kotlinx.android.synthetic.main.activity_profile.imageView28
import kotlinx.android.synthetic.main.activity_profile.imageView29
import kotlinx.android.synthetic.main.activity_profile.textView45
import kotlinx.android.synthetic.main.activity_profile.textView46
import kotlinx.android.synthetic.main.activity_profile.textView47
import kotlinx.android.synthetic.main.activity_profile.textView48
import kotlinx.android.synthetic.main.activity_profile.textView49
import kotlinx.android.synthetic.main.activity_profile.textView50
import kotlinx.android.synthetic.main.activity_profile.textView51
import kotlinx.android.synthetic.main.activity_profile.textView52
import kotlinx.android.synthetic.main.activity_profile_2.*
import javax.xml.transform.sax.TemplatesHandler
class ProfileActivity : BaseActiity() {
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_profile_2)
//        window.statusBarColor= Color.parseColor("#ec4646")
//        setContentView(R.layout.activity_profile)
        linearLayout5_74.setOnClickListener {
            finish()
        }


        textView94.setText(phoneNumber)


        Log.i("Advavas",fullName)

        if (!fullName.isNullOrEmpty())
        {
            textView92.setText(fullName)
        }

        imageView56.setOnClickListener {
            var v=Intent(this,MultyActivity_2::class.java)
            v.putExtra("Type","A")
            startActivityForResult(v,155)
        }

        card_1.setOnClickListener {
            var v=Intent(this,MultyActivity_2::class.java)
            v.putExtra("Type","A")
            startActivityForResult(v,155)
        }

        card_2.setOnClickListener {

            var v=Intent(this,MultyActivity_2::class.java)
            v.putExtra("Type","B")
            startActivityForResult(v,255)
        }

        card_3.setOnClickListener {
            var v=Intent(this,MultyActivity_2::class.java)
            v.putExtra("Type","C")
            startActivity(v)
        }


        card_4.setOnClickListener {
            var v=Intent(this,MultyActivity_2::class.java)
            v.putExtra("Type","D")
            startActivity(v)
        }


        card_5.setOnClickListener {
            var v=Intent(this,MultyActivity_2::class.java)
            v.putExtra("Type","E")
            startActivity(v)
        }


        card_6.setOnClickListener {
            var v=Intent(this,MultyActivity_2::class.java)
            v.putExtra("Type","F")
            startActivity(v)
        }

        card_7.setOnClickListener {
        }

        card_8.setOnClickListener {

            var p=   Dialog_Ask(6,"آیا میخواهید از حساب خود خارج شوید?",object : Dial_App.Interface_new_2{
                override fun News(s: String) {
                    if (s.equals("A"))
                    {
//                        Del_Address(S,Pos,v)
                        token=""
                        securityKey=""
                        fullName=""
                        sharedPreferences?.edit()?.putString(Constants.USER_SECURITY_KEY,"")?.apply()
                        sharedPreferences?.edit()?.putString(Constants.USER_TOKEN,"")?.apply()
                        sharedPreferences?.edit()?.putString(Constants.USER_FULLNAME,"")?.apply()
                        sharedPreferences?.edit()?.putString(Constants.storeName,"")?.apply()
                        sharedPreferences?.edit()?.putString(Constants.rulesUrl,"")?.apply()
                        sharedPreferences?.edit()?.putString(Constants.startWork,"")?.apply()
                        sharedPreferences?.edit()?.putString(Constants.endWork,"")?.apply()
                        MainActivity.act?.finish()
                        finish()
                    }
                }

            }, this)
            p.show()

        }
//        imageView23.setOnClickListener {
//            var v=Intent(this,MultyActivity_2::class.java)
//            v.putExtra("Type","A")
//            startActivity(v)
//        }
//        textView46.setOnClickListener {
//            var v=Intent(this,MultyActivity_2::class.java)
//            v.putExtra("Type","A")
//            startActivity(v)
//        }
//        imageView24.setOnClickListener {
//            var v=Intent(this,MultyActivity_2::class.java)
//            v.putExtra("Type","B")
//            startActivity(v)
//        }
//        textView47.setOnClickListener {
//            var v=Intent(this,MultyActivity_2::class.java)
//            v.putExtra("Type","B")
//            startActivity(v)
//        }
//        textView48.setOnClickListener {
//            var v=Intent(this,MultyActivity_2::class.java)
//            v.putExtra("Type","C")
//            startActivity(v)
//        }
//        imageView25.setOnClickListener {
//            var v=Intent(this,MultyActivity_2::class.java)
//            v.putExtra("Type","C")
//            startActivity(v)
//        }
//        textView49.setOnClickListener {
//            var v=Intent(this,MultyActivity_2::class.java)
//            v.putExtra("Type","D")
//            startActivity(v)
//        }
//        imageView26.setOnClickListener {
//            var v=Intent(this,MultyActivity_2::class.java)
//            v.putExtra("Type","D")
//            startActivity(v)
//        }
//        textView50.setOnClickListener {
//            var v=Intent(this,MultyActivity_2::class.java)
//            v.putExtra("Type","E")
//            startActivity(v)
//        }
//        imageView27.setOnClickListener {
//            var v=Intent(this,MultyActivity_2::class.java)
//            v.putExtra("Type","E")
//            startActivity(v)
//        }
//
//        textView51.setOnClickListener {
//            var v=Intent(this,MultyActivity_2::class.java)
//            v.putExtra("Type","F")
//            startActivity(v)
//        }
//
//        imageView28.setOnClickListener {
//            var v=Intent(this,MultyActivity_2::class.java)
//            v.putExtra("Type","F")
//            startActivity(v)
//        }
//
//
//
//
//        textView52.setOnClickListener {
//            token=""
//            securityKey=""
//            fullName=""
//            sharedPreferences?.edit()?.putString(Constants.USER_SECURITY_KEY,"")?.apply()
//            sharedPreferences?.edit()?.putString(Constants.USER_TOKEN,"")?.apply()
//            sharedPreferences?.edit()?.putString(Constants.USER_FULLNAME,"")?.apply()
//            MainActivity.act?.finish()
//            var i=Intent(this,MultyActivity_2::class.java)
//            i.putExtra("Type","W")
//            startActivity(i)
//            finish()
//        }
//
//
//        imageView29.setOnClickListener {
//            token=""
//            securityKey=""
//            fullName=""
//            sharedPreferences?.edit()?.putString(Constants.USER_SECURITY_KEY,"")?.apply()
//            sharedPreferences?.edit()?.putString(Constants.USER_TOKEN,"")?.apply()
//            sharedPreferences?.edit()?.putString(Constants.USER_FULLNAME,"")?.apply()
//            MainActivity.act?.finish()
//            var i=Intent(this,MultyActivity_2::class.java)
//            i.putExtra("Type","W")
//            startActivity(i)
//            finish()
//        }
//
//
//        imageView29.setOnClickListener {
//            finish()
//        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==155)
        {
            if (resultCode== RESULT_OK)
            {
                if (data?.getStringExtra("name")!=null)
                {

                    textView92.setText(data.getStringExtra("name"))
                }
            }
        }

        if (requestCode==255)
        {
            if (resultCode== RESULT_OK)
            {
                if (data?.getStringExtra("data")!=null)
                {

                    textView94.setText(data.getStringExtra("data"))
                }
            }
        }
    }

}