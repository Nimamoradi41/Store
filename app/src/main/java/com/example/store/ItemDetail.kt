package com.example.store

import android.app.Dialog
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.store.Adapters.adapter_slider
import com.example.store.Adapters.adapter_slider_item
import com.example.store.Dialog.Dial_App
import com.example.store.Models.model_Item
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.custome_modal.view.*
import kotlinx.android.synthetic.main.custome_special.view.*
import kotlinx.android.synthetic.main.fragment_mainfrag.view.*


class ItemDetail : BaseActiity() {
    var ad_Spa : adapter_Spacial?= null
    var Pos:Int?=null
    var ad_slider:adapter_slider_item ?= null
    var Flag:Boolean=false
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }
    var v:products ? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        v= intent.getSerializableExtra("data") as products?
        Pos=intent.getIntExtra("pos",-48);
        if (v?.title!=null)
        {
            textView27.setText(v?.title)
        }




        if (v!=null)
        {

            if (v?.discountPercent==0)
            {
                textView5.visibility=View.GONE
                textView7.visibility=View.GONE
                textView3.visibility=View.GONE
                imageView3.visibility=View.GONE
            }else{
                if (v?.price!=null)
                {
                    textView5.setText(v?.priceForShow)
                    textView7.setText(v?.priceForShow)
                }
            }
        }else{
            textView5.visibility=View.GONE
            textView7.visibility=View.GONE
            textView3.visibility=View.GONE
            imageView3.visibility=View.GONE
        }




        if (v?.priceAfterDiscountForShow!=null)
        {
            textView6.setText(v?.priceAfterDiscountForShow)
        }



        if (v?.description!=null)
        {
            textView28.setText(v?.description)
        }




        if (v?.currentReserved!=null)
        {
            if (v?.currentReserved!! >0)
            {
                button.setText(v?.currentReserved!!.toString()+" عدد ")
            }
        }










        if (v?.discountPercent!=null)
        {
            textView3.setText("%"+v?.discountPercent)
        }







        ad_slider= adapter_slider_item(ArrayList<String> (),this)
        imageSlider.setSliderAdapter(ad_slider!!)
        imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
        imageSlider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        imageSlider.startAutoCycle()
        linearLayout.setOnClickListener {
            if (Flag)
            {
                var I=Intent()
                I.putExtra("data",v)
                I.putExtra("pos",Pos)
                setResult(RESULT_OK,I)
            }
            finish()
        }
        button.setOnClickListener {
            val d= Dialog(this, R.style.CustomDialog)
            d.setCancelable(true)
            var CF= LayoutInflater.from(this).inflate(R.layout.custome_modal, null, false)
            d.setContentView(CF)
            var i_Count=ArrayList<String> ()
            var r = v?.getMaxCountReserve()
            var i = 0
            while (i < r!!) {
                i++
                i_Count.add(i.toString())
            }
            i_Count.add(0,"0")
            var ad_=adapter_count_shop(i_Count,d, v?.getId().toString())
            CF.recy_itemmmms.adapter=ad_
            ad_.clicl(object : adapter_count_shop.data_i {
                override fun Data_d(I: Int, Id: String) {
                    if (v?.getCount()==0)
                    {
                        Toast.makeText(this@ItemDetail,"عدم موجودی", Toast.LENGTH_SHORT).show()
                        return
                    }
                    if (v?.getCurrentReserved()!=I)
                    {
                        if (!isNetConnected())
                        {
                            var I=2;
                            var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object : Dial_App.Interface_new{
                                override fun News() {

                                }
                            }, this@ItemDetail)
                            p.show()
                            return
                        }
                        Log.i("kvnsndv", I.toString())
                        AddEditDeleteItem_Card(I,Id,object :Resuilt{
                            override fun Data(i: Int, S: String, B: Boolean) {
                                if (B)
                                {
                                    v?.currentReserved=I
                                    Flag=true;
                                    if (I>0)
                                    {

                                      button.setText(I.toString()+" عدد ")
                                      button.animate().alpha(1f).setDuration(500).start()
                                    }else  {
                                       button.setText("افزودن به سبد خرید")
                                       button.animate().alpha(1f).setDuration(500).start()
                                    }

                                }
                            }

                        })
                    }
                }

            })
            d.window?.setLayout(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.MATCH_PARENT
            )
            d.window?.setBackgroundDrawable(ColorDrawable((Color.TRANSPARENT)))
            CF.imageView15.setOnClickListener {
                d.dismiss()
            }

            CF.cccv.setOnClickListener {

            }
            CF.close.setOnClickListener {
                d.dismiss()
            }
            d.show()
        }
//        var va=ArrayList<model_Item>()
//
//        var  nm= model_Item()
//        nm.name="همبرگر مممتاز 250 گرمی شرکت 202"
//        nm.img=R.drawable.item_1
//        va.add(nm)
//        var  nm_2= model_Item()
//        nm_2.name="روغن افتاب بدون کلسترول با ویتامین "
//        nm_2.img=R.drawable.item_2
//        va.add(nm_2)
//        var  nm_3= model_Item()
//        nm_3.name="برنج چارلی با کیفیت بالا و بهترین مزه "
//        nm_3.img=R.drawable.item_3
//        va.add(nm_3)
//        var  nm_4= model_Item()
//        nm_4.name="قارچ بسته بندی آماده وکیوم شده تازه  500 گرمی"
//        nm_4.img=R.drawable.item_4
//        va.add(nm_4)
//        var  nm_5= model_Item()
//        nm_5.name="چای شهرزاد خوش بو 500 گرمی"
//        nm_5.img=R.drawable.item_5
//        va.add(nm_5)
//        var  nm_6= model_Item()
//        nm_6.name="چای شهرزاد خوش بو با طعم لیو 500 گرمی"
//        nm_6.img=R.drawable.item_6
//        va.add(nm_6)
//        var dis=DisplayMetrics()
//        windowManager.defaultDisplay.getMetrics(dis)
//        var W=dis.widthPixels
//        Log.i("cmdvlad",W.toString())
//        ad_Spa= adapter_Spacial(this,va,W)
//        like?.layoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,true)
//        like.adapter=ad_Spa



    }

    override fun onBackPressed() {
        if (Flag)
        {
            var I=Intent()
            I.putExtra("data",v)
            I.putExtra("pos",Pos)
            setResult(RESULT_OK,I)
        }
        super.onBackPressed()

    }
}