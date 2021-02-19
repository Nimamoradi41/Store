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
import com.bumptech.glide.Glide
import com.example.store.Adapters.adapter_slider
import com.example.store.Adapters.adapter_slider_2
import com.example.store.Adapters.adapter_slider_item
import com.example.store.Dialog.Dial_App
import com.example.store.MainActivity.Companion.mainActivityViewModel
import com.example.store.Main_Fragments.Login
import com.example.store.Models.ResGetDetail
import com.example.store.Models.model_Item
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.activity_item_detail.button
import kotlinx.android.synthetic.main.activity_item_detail.imageSlider_2
import kotlinx.android.synthetic.main.activity_item_detail.linearLayout
import kotlinx.android.synthetic.main.activity_item_detail.pageIndicatorView_2
import kotlinx.android.synthetic.main.activity_item_detail.recy_itemsssw
import kotlinx.android.synthetic.main.activity_item_detail.textView27
import kotlinx.android.synthetic.main.activity_item_detail.textView5
import kotlinx.android.synthetic.main.activity_item_detail.textView6
import kotlinx.android.synthetic.main.activity_item_detail.textView7
import kotlinx.android.synthetic.main.activity_item_detail.textView81
import kotlinx.android.synthetic.main.activity_item_detail.textView83
import kotlinx.android.synthetic.main.activity_item_detail_2.*
import kotlinx.android.synthetic.main.custome_modal.view.*
import kotlinx.android.synthetic.main.custome_special.view.*
import kotlinx.android.synthetic.main.fragment_custome_slider.view.*
import kotlinx.android.synthetic.main.fragment_mainfrag.view.*
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ItemDetail : BaseActiity() {
    var ad_Spa : Adapter_discounts_2?= null
    var Pos:Int?=null
    var  ad_slider : adapter_slider_2? =null
    var Flag:Boolean=false
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }
    var v:products ? =null
    private fun SetData() {
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



        // TODO Add
//        if (v?.description!=null)
//        {
//            textView81.setText(v?.description)
//        }


//        if (v?.!=null)
//        {
//            textView81.setText(v?.description)
//        }




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




    }
    fun GetDetail(S:String)
    {
        var Body=RequestBody.create(MediaType.parse("text/palne"),S)
        var req=api?.GetDetail("Bearer " +token,Body)
        req?.enqueue(object : Callback<ResGetDetail> {
            override fun onResponse(call: Call<ResGetDetail>, response: Response<ResGetDetail>) {
                Dial_Close()
                Log.i("zcvmzkmvzkmvmzv",response.code().toString())
                if (response.isSuccessful)
                {
                    var Data =response.body()?.data
                        if (Data?.images!=null)
                        {
                            if (Data.images?.size==0)
                            {
                                var vx=ArrayList<String>()
                                vx.add(Data.firstImage.toString())
                                ad_slider= adapter_slider_2(supportFragmentManager,vx)
                                imageSlider_2.adapter=ad_slider
                                pageIndicatorView_2?.setViewPager(imageSlider_2);
//                                ad_slider= adapter_slider_item(vx,this@ItemDetail)
//                                imageSlider.setSliderAdapter(ad_slider!!)
//                                imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
//                                imageSlider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
//                                imageSlider.startAutoCycle()
                            }else{
                                ad_slider= adapter_slider_2(supportFragmentManager, Data.images!!)
                                imageSlider_2.adapter=ad_slider
                                pageIndicatorView_2?.setViewPager(imageSlider_2);
//                                ad_slider= adapter_slider_item(Data.images!!,this@ItemDetail)
//                                imageSlider.setSliderAdapter(ad_slider!!)
//                                imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
//                                imageSlider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
//                                imageSlider.startAutoCycle()
                            }

                        }else{
                            var vx=ArrayList<String>()
                            vx.add(Data?.firstImage.toString())
                            ad_slider= adapter_slider_2(supportFragmentManager,vx)
                            imageSlider_2.adapter=ad_slider
                            pageIndicatorView_2?.setViewPager(imageSlider_2);
                        }







                        if (!Data?.categoryTitle?.isNullOrEmpty()!!)
                        {
                            textView116.setText(Data?.categoryTitle)
                        }


                    if (Data?.brand!=null)
                    {
                       if (!Data?.brand?.image.isNullOrEmpty())
                       {
                           Glide.with(this@ItemDetail).load(Constants.BASE_URL+"/BrandImg/"+Data?.brand?.image).into(imageView62);
                       }
                    }


                    if (Data?.title!=null)
                    {
                        if (!Data?.brand?.title.isNullOrEmpty())
                        {
                            textView114.setText(Data?.brand?.title)
                        }
                    }




//                    if (Data?.brand!=null)
//                    {
//                        if (!Data?.brand?.title.isNullOrEmpty())
//                        {
//                            textView116.setText(Data?.brand?.title.toString())
//                        }
//                    }




                    if (!Data?.description.isNullOrEmpty())
                    {
                        v?.description=Data?.description
                        textView81.setText(Data?.description)
                    }
                    if (Data?.title!=null)
                    {
                        v?.title=Data?.title
                        textView27.setText(Data?.title)
                    }
                    if (Data!=null)
                    {



                        if (Data?.discountPercent==0)
                        {
                            textView5.visibility=View.GONE
                            textView86.visibility=View.GONE
                            textView7.visibility=View.GONE
                        }else{
                            if (Data?.price!=null)
                            {
                                textView5.setText(Data?.priceForShow)
                                textView7.setText(Data.priceForShow)
                                textView86.setText("%"+Data.discountPercent.toString())
                            }
                        }
                    }else{
                        textView5.visibility=View.GONE
                        textView86.visibility=View.GONE
                        textView7.visibility=View.GONE
                    }




                    if (Data?.priceAfterDiscountForShow!=null)
                    {
                        textView6.setText(Data?.priceAfterDiscountForShow)
                    }


                    if (Data?.currentReserved!=null)
                    {
                        if (Data?.currentReserved!! >0)
                        {
                            textView112.setText(Data.currentReserved!!.toString())
                        }
                    }



                    if (v?.discountPercent!=null)
                    {
                        textView86.setText("%"+v?.discountPercent)
                    }
//                    var I=Intent(this@SplashScreen,MainActivity::class.java)
//                    I.putExtra("DATA",response.body()?.getData())
//                    startActivity(I)
//                    finish()


                    v?.discountPercent= Data?.discountPercent!!
                    v?.price= Data?.price!!
                    v?.priceForShow= Data?.priceForShow!!
                    v?.priceAfterDiscountForShow= Data?.priceAfterDiscountForShow!!
                    v?.discountPercent= Data?.discountPercent!!
                    v?.currentReserved= Data?.currentReserved!!
                    v?.count= Data?.count!!
                    v?.maxCountReserve= Data?.maxCountReserve!!

                    if(Data.similarProducts!=null)
                    {
                        if (Data.similarProducts?.size==0)
                        {
                            textView83.visibility=View.INVISIBLE
                        }else{
                            ad_Spa?.list= Data.similarProducts!!
                            ad_Spa?.notifyDataSetChanged()
                        }
                    }else{
                        textView83.visibility=View.INVISIBLE
                    }





                    Dial_Close()

                }
                if (response.code()==401)
                {
                    Login(securityKey,object : Login {
                        override fun onLoginCompleted(success: Boolean) {
                            if (success)
                            {
                                GetDetail(S)
                            }
                        }

                    })
                }
            }
            override fun onFailure(call: Call<ResGetDetail>, t: Throwable) {
                var I=3
                Log.i("zcvmzkmvzkmvmzv",t.message.toString())
                Dial_Close()
                var p=   Dialapp(I,"لطفا دوباره تلاش کنید",object :Dial_App.Interface_new{
                    override fun News() {
                       finish()
                    }
                },this@ItemDetail)
                p.show()
            }
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_item_detail)
        setContentView(R.layout.activity_item_detail_2)
        DialLoad()
        v= intent.getSerializableExtra("data") as products?
        Pos=intent.getIntExtra("pos",-48);
        window.statusBarColor=Color.parseColor("#ec4646")
        textView_98.setText(storeName)
//        SetData()



        imageView68.setOnClickListener {
            // Right
            Log.i("svknsnkvs", imageSlider_2.childCount.toString())
            Log.i("svknsnkvs_2", imageSlider_2.currentItem.toString())
            var Current=imageSlider_2.currentItem
            var Child=imageSlider_2.childCount
            if (Current<Child)
            {
                imageSlider_2.currentItem=Current+1
            }
        }


        imageView63.setOnClickListener {
            // Left
            Log.i("svknsnkvs", imageSlider_2.childCount.toString())
            Log.i("svknsnkvs_2", imageSlider_2.currentItem.toString())
            var Current=imageSlider_2.currentItem
            var Child=imageSlider_2.childCount
            if (Current>0)
            {
                imageSlider_2.currentItem=Current-1
            }
        }

        val dm = DisplayMetrics()
        getWindowManager()?.getDefaultDisplay()?.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.widthPixels











        ad_Spa= Adapter_discounts_2(this,ArrayList<products>(),height,width)
        recy_itemsssw.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true)
        recy_itemsssw.isNestedScrollingEnabled=false
        recy_itemsssw.adapter=ad_Spa
        ad_Spa?.click(object : Adapter_discounts_2.Data_dis {
            override fun Data(I: Int, ID: String, Pos: Int) {
                if (!isNetConnected()) {
                    var I = 2;
                    var p = Dialapp(
                            2,
                            "اتصال خود را به اینترنت بررسی کنید",
                            object : Dial_App.Interface_new {
                                override fun News() {

                                }
                            },
                            this@ItemDetail
                    )
                    p.show()
                    return
                }
                Log.i("kvnsndv", I.toString())
                AddEditDeleteItem_Card(I, ID, object : Resuilt {
                    override fun Data(i: Int, S: String, B: Boolean) {
                        if (B) {
                            var v = ad_Spa?.list?.get(Pos)
                            v?.currentReserved = I
                            ad_Spa?.list?.set(Pos, v!!)
                            ad_Spa?.notifyItemChanged(Pos)
                            MainActivity.mainActivityViewModel?.count?.value = i
                            GetHome()
                        }
                    }

                })
            }

        })
        GetDetail(v?.id.toString())









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
        constraintLayout8.setOnClickListener {
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

                                        textView112.setText(I.toString())
                                        textView112.animate().alpha(1f).setDuration(500).start()
                                    }else  {
                                        textView112.setText("0")
                                        textView112.animate().alpha(1f).setDuration(500).start()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==20)
        {
            if (resultCode== RESULT_OK)
            {
//                GetHome()
                Log.i("nngshfdp","K")
               if(data!=null)
               {
                   if (data.getSerializableExtra("data")!=null)
                   {
                       Log.i("dsvsbnsnfbdkkdvk","I")
                       var data_2=data?.getSerializableExtra("data") as products
                       var pos=data.getIntExtra("pos",-8)
                       var v = ad_Spa?.list?.get(pos)
                       v?.currentReserved = data_2.currentReserved
                       ad_Spa?.list?.set(pos, v!!)
                       ad_Spa?.notifyItemChanged(pos)
                       GetHome()
//                       MainActivity.mainActivityViewModel?.count?.value = i

                   }
               }
            }
        }
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