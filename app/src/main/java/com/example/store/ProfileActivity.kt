package com.example.store
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_profile.*
import javax.xml.transform.sax.TemplatesHandler
class ProfileActivity : AppCompatActivity() {
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_profile)
        linearLayout5.setOnClickListener {
            finish()
        }
        imageView23.setOnClickListener {
            var v=Intent(this,MultyActivity_2::class.java)
            v.putExtra("Type","A")
            startActivity(v)
        }
        textView46.setOnClickListener {
            var v=Intent(this,MultyActivity_2::class.java)
            v.putExtra("Type","A")
            startActivity(v)
        }
        imageView24.setOnClickListener {
            var v=Intent(this,MultyActivity_2::class.java)
            v.putExtra("Type","B")
            startActivity(v)
        }
        textView47.setOnClickListener {
            var v=Intent(this,MultyActivity_2::class.java)
            v.putExtra("Type","B")
            startActivity(v)
        }
        textView48.setOnClickListener {
            var v=Intent(this,MultyActivity_2::class.java)
            v.putExtra("Type","C")
            startActivity(v)
        }
        imageView25.setOnClickListener {
            var v=Intent(this,MultyActivity_2::class.java)
            v.putExtra("Type","C")
            startActivity(v)
        }
        textView49.setOnClickListener {
            var v=Intent(this,MultyActivity_2::class.java)
            v.putExtra("Type","D")
            startActivity(v)
        }
        imageView26.setOnClickListener {
            var v=Intent(this,MultyActivity_2::class.java)
            v.putExtra("Type","D")
            startActivity(v)
        }
        textView50.setOnClickListener {
            var v=Intent(this,MultyActivity_2::class.java)
            v.putExtra("Type","E")
            startActivity(v)
        }
        imageView27.setOnClickListener {
            var v=Intent(this,MultyActivity_2::class.java)
            v.putExtra("Type","E")
            startActivity(v)
        }

        textView51.setOnClickListener {
            var v=Intent(this,MultyActivity_2::class.java)
            v.putExtra("Type","F")
            startActivity(v)
        }

        imageView28.setOnClickListener {
            var v=Intent(this,MultyActivity_2::class.java)
            v.putExtra("Type","F")
            startActivity(v)
        }




        textView52.setOnClickListener {
            finish()
        }


        imageView29.setOnClickListener {
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

}