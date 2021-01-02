package com.example.store

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.store.Main_Fragments.*
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_multy.*

class MultyActivity_2 : BaseActiity() {
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multy)
        var Type=intent.getStringExtra("Type")

        if (Type.equals("A"))
        {
            textView1er.setText("اطلاعات حساب من")
            supportFragmentManager.beginTransaction().add(R.id.Conta, Frag_Myaccunt()).commit()
        }

        if (Type.equals("W"))
        {
            holder.visibility=View.GONE
            supportFragmentManager.beginTransaction().add(R.id.Conta, Frag_Phone_Number()).commit()
        }
        if (Type.equals("Z"))
        {
            holder.visibility=View.GONE
            var v=Frag_verfivcation()
            var b=Bundle()
            b.putString("data_2",intent.getStringExtra("data"))
            v.arguments=b
            supportFragmentManager.beginTransaction().add(R.id.Conta, v).commit()
        }
        if (Type.equals("C"))
        {
            textView1er.setText("آدرس های من")
            supportFragmentManager.beginTransaction().add(R.id.Conta, Frag_Address()).commit()
        }
        if (Type.equals("F"))
        {
            textView1er.setText("قوانین")
            supportFragmentManager.beginTransaction().add(R.id.Conta, Frag_Ruls()).commit()
        }
        if (Type.equals("D"))
        {
            textView1er.setText("کد تخفیف و هدیه")
            supportFragmentManager.beginTransaction().add(R.id.Conta, Frag_Offers()).commit()
        }

        if (Type.equals("B"))
        {
            textView1er.setText("اطلاعات بانکی")
            supportFragmentManager.beginTransaction().add(R.id.Conta, Frag_Accunt_Bank()).commit()
        }
        if (Type.equals("E"))
        {
            textView1er.setText("سوابق خرید")
            supportFragmentManager.beginTransaction().add(R.id.Conta, Frag_list_orders()).commit()
        }



        if (Type.equals("P"))
        {
            holder.visibility=View.GONE
            var ccc=Frag_ALL_Dis()
            var bbb=Bundle()
            bbb.putSerializable("data2",intent.getSerializableExtra("data"))
            ccc.arguments=bbb
            supportFragmentManager.beginTransaction().add(R.id.Conta,ccc).commit()
        }



        if (Type.equals("G"))
        {
            holder.visibility=View.GONE
            var v=Frag_All_All()
            var b=Bundle()
            b.putSerializable("data_2",intent.getSerializableExtra("data"))
            b.putInt("t",intent.getIntExtra("t",-1))
            v.arguments=b
            supportFragmentManager.beginTransaction().add(R.id.Conta, v).commit()
        }


        if (Type.equals("X"))
        {
            holder.visibility=View.GONE
            var v=Frag_All_All()
            var b=Bundle()
            b.putSerializable("data_2",intent.getSerializableExtra("data"))
            b.putInt("t",intent.getIntExtra("t",-1))
            b.putInt("cateid",intent.getIntExtra("cateid",-1))
            v.arguments=b
            supportFragmentManager.beginTransaction().add(R.id.Conta, v).commit()
        }


        linearLayout5.setOnClickListener {
            finish()
        }
    }
}