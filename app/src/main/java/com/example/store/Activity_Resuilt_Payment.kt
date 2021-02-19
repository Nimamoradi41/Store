package com.example.store

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity__resuilt__payment.*

class Activity_Resuilt_Payment : AppCompatActivity() {
    var Type:String ?=null
    var Id_Payment:String ?=null
    var Price:String ?=null
    @SuppressLint("ResourceAsColor")
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__resuilt__payment)
        Type=intent.getStringExtra("Type")
        button10.setOnClickListener {
            MainActivity.act?.finish()
            Activity_card_Bascket.act?.finish()
            Activity_Second_Bascket.act?.finish()
            startActivity(Intent(this,SplashScreen::class.java))
            finish()
        }
        if (Type.equals("A"))
        {
          Price=intent.getStringExtra("Price")
          Id_Payment=intent.getStringExtra("Id_Payment")
            textView75.setText(Price)
            textView76.setText(Id_Payment)
        }else if (Type.equals("B"))
        {
            Type_View.setBackgroundResource(R.color.Payment_false)
            imageView48.setImageResource(R.drawable.ic_payment_false)
            textView72.setText("متاسفانه پرداخت با موفقیت\n" +
                    " انجام نشد")
            textView72.setTextColor(R.color.Payment_false)
            card_payment.visibility=View.GONE
            button10.setTextColor(Color.WHITE)
            button10.setText("تلاش دوباره")
            button10.setBackgroundResource(R.drawable.shape_40)
        }
    }
    override fun onBackPressed() {
        if (MainActivity.act!=null)
        {
            MainActivity.act?.finish()
        }

        if (Activity_card_Bascket.act!=null)
        {
            Activity_card_Bascket.act?.finish()
        }

        if (Activity_Second_Bascket.act!=null)
        {
            Activity_Second_Bascket.act?.finish()
        }

        super.onBackPressed()
    }
}