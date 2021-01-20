package com.example.store

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.store.Main_Fragments.*
import com.example.store.Models.data_accses
import com.example.store.VIEWMODEL.MainActivityViewModel
import com.example.store.VIEWMODEL.MainActivityViewModel_2
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_multy.*

class MultyActivity_2 : BaseActiity() {
    companion object{
        var Count :Int =-4;
        var address :ModelAddress?= null
        var Pos=0
    }
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }
    var mainActivityViewModel : MainActivityViewModel_2?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multy)
        var Type=intent.getStringExtra("Type")
        mainActivityViewModel = ViewModelProviders.of(this)[MainActivityViewModel_2::class.java]

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


        if (Type.equals("Map2"))
        {
            textView1er.setText("آدرس های من")
            Pos=intent.getIntExtra("Pos",-4)
            address=intent.getSerializableExtra("address") as ModelAddress
            supportFragmentManager.beginTransaction().add(R.id.Conta, Frag_Address_2()).commit()
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
            if (address!=null)
            {
                var I=Intent()
                I.putExtra("data", address)
                I.putExtra("Pos", Pos)
                setResult(RESULT_OK,I)
                finish()
            }
            finish()
        }
    }


    override fun onBackPressed() {
//        if (Count!=-4)
//        {
//            var cc=Intent()
//            cc.putExtra("count", Count)
//           setResult(RESULT_OK,cc)
//            finish()
//        }

        Log.i("fbgnhscdv","A")

        if (address!=null)
        {

            var I=Intent()
            I.putExtra("data", address)
            I.putExtra("Pos", Pos)
            setResult(RESULT_OK,I)
            Log.i("fbgnhscdv",address?.fullLocation.toString())
            finish()
        }
        Log.i("fbgnhscdv","B")
        super.onBackPressed()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==85)
        {
            if (resultCode== RESULT_OK)
            {
                var s= data_accses()
                Log.i("svsbknsbs", data?.getIntExtra("pos",77).toString())
                s.Pos=data?.getIntExtra("pos",77)
                s.v=data?.getSerializableExtra("data") as products?
                MainActivity.mainActivityViewModel?.change_Data?.value= s
            }
        }
    }
}