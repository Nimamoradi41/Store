package com.example.store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.store.Adapters.adapter_items_card_bascket
import com.example.store.Adapters.adapter_items_card_bascket_2
import com.example.store.Models.data_Order
import com.example.store.Models.model_Item
import kotlinx.android.synthetic.main.activity_card__bascket.*
import kotlinx.android.synthetic.main.activity_detail_order.*
import kotlinx.android.synthetic.main.custome_list_order.view.*

class DetailOrder : AppCompatActivity() {
    var ad_card:Adapter_second_bascket_marsole ? =null
    var item: data_Order? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_order)
        item= intent.getSerializableExtra("data") as data_Order?
        btn_satau.setText(item?.orderStatusTitle)


        if (item?.orderStatus==3)
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

        recy_itemss_2.layoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,true)
        ad_card=Adapter_second_bascket_marsole(item?.orderItems!!,this)
        recy_itemss_2.adapter=ad_card




        if (item?.numberTracking!=null)
        {
            textView36.setText(item?.numberTracking)
        }

        if (item?.deliveryCode!=null)
        {
            textView63.setText(item?.deliveryCode)
        }else{
            textView63.setText("بدون شناسه")
        }



        if (item?.addressFullName!=null)
        {
            textView37.setText(item?.addressFullName)
        }else{
            textView37.setText("بدون شناسه")
        }













        if (item?.fullLocation!=null)
        {
            textView39.setText(item?.fullLocation)
        }
        if (item?.pricePayForShow!=null)
        {
         textView40.setText(item?.pricePayForShow)
        }




        if (item?.orderItems!=null)
        {
            tx_count.setText(item?.orderItems?.size.toString())
        }



        if (item?.priceForShow!=null)
        {
            tx_sum.setText(item?.priceForShow?.toString())
        }




        if (item?.discountPriceForShow!=null)
        {
            tx_offer.setText(item?.discountPriceForShow?.toString())
        }
        if (item?.pricePayForShow!=null)
        {
            tx_mony.setText(item?.pricePayForShow?.toString())
        }




        if (item?.datePaymentFa!=null)
        {
            textView38.setText(item?.datePaymentFa?.toString())
        }else{
            textView38.setText("بدون شناسه")
        }




        linearLayout555.setOnClickListener {
            finish()
        }


    }
}