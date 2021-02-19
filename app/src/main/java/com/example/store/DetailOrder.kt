package com.example.store

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.store.Adapters.adapter_items_card_bascket
import com.example.store.Adapters.adapter_items_card_bascket_2
import com.example.store.Models.data_Order
import com.example.store.Models.model_Item
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_card__bascket.*
import kotlinx.android.synthetic.main.activity_detail_order.*
import kotlinx.android.synthetic.main.custome_list_order.view.*

class DetailOrder : BaseActiity() {
    var ad_card:Adapter_second_bascket_marsole ? =null
    var item: data_Order? =null

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_order)
        window.statusBarColor= Color.parseColor("#ec4646")
        item= intent.getSerializableExtra("data") as data_Order?
        btn_satau.setText(item?.orderStatusTitle)
        recy_itemss_2.isNestedScrollingEnabled=false
        if (item?.orderStatus==3||item?.orderStatus==4||item?.orderStatus==5||item?.orderStatus==6||item?.orderStatus==7||item?.orderStatus==9)
        {
            btn_satau.setBackgroundResource(R.drawable.shape_39)
            btn_satau.setText(item?.orderStatusTitle)
        }else if (item?.orderStatus==1||item?.orderStatus==2)
        {
            btn_satau.setBackgroundResource(R.drawable.shape_40)
            btn_satau.setText(item?.orderStatusTitle)
        }else{
            btn_satau.setBackgroundResource(R.drawable.shape_40)
            btn_satau.setText(item?.orderStatusTitle)
        }





        ad_card=Adapter_second_bascket_marsole(item?.orderItems!!,this)
        recy_itemss_2.adapter=ad_card




        if (item?.numberTracking!=null)
        {
            textView36.setText(item?.numberTracking)
        }

        if (item?.deliveryPriceForShow!=null)
        {
            textView96.setText(item?.deliveryPriceForShow)
        }




//        if (item?.deliveryCode!=null)
//        {
//            textView63.setText(item?.deliveryCode)
//        }else{
//            textView63.setText("بدون شناسه")
//        }



//        if (item?.addressFullName!=null)
//        {
//            textView37.setText(item?.addressFullName)
//        }else{
//            textView37.setText("بدون شناسه")
//        }













//        if (item?.fullLocation!=null)
//        {
//            textView39.setText(item?.fullLocation)
//        }
//        if (item?.pricePayForShow!=null)
//        {
//         textView40.setText(item?.pricePayForShow)
//        }




        if (item?.orderItems!=null)
        {
            textView9751_3.setText(item?.orderItems?.size.toString())
        }
//
//
//
        if (item?.priceForShow!=null)
        {
            textView14_2_3.setText(item?.priceForShow?.toString())
        }
//
//
//
//
        if (item?.discountPriceForShow!=null)
        {
            textView16_2_3.setText(item?.discountPriceForShow?.toString())
        }
//        if (item?.pricePayForShow!=null)
//        {
//            tx_mony.setText(item?.pricePayForShow?.toString())
//        }




        if (item?.datePaymentFa!=null)
        {
            textView38.setText(item?.datePaymentFa?.toString())
        }else{
            textView38.setText("بدون شناسه")
        }


//        textView9751_4.setText(item?.orderItems?.size.toString()!!)
//
//
//        if (!item?.priceForShow.isNullOrEmpty())
//        {
//            textView14_2_4.setText(item?.priceForShow.toString())
//        }
//
//
//        if (!item?.discountPriceForShow.isNullOrEmpty())
//        {
//            textView16_2_5.setText(item?.discountPriceForShow.toString())
//        }
//
//
//
        if (!item?.pricePayForShow.isNullOrEmpty())
        {
            dveifoh_2.setText(item?.pricePayForShow.toString())
        }
//
//
//        if (!item?.pricePayForShow.isNullOrEmpty())
//        {
//            dveifoh_4.setText(item?.pricePayForShow.toString())
//        }





        linearLayout555.setOnClickListener {
            finish()
        }


    }
}