package com.example.store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.store.Adapters.adapter_items_card_bascket
import com.example.store.Adapters.adapter_items_card_bascket_2
import com.example.store.Models.data_Order
import com.example.store.Models.model_Item
import kotlinx.android.synthetic.main.activity_card__bascket.*
import kotlinx.android.synthetic.main.activity_detail_order.*
import kotlinx.android.synthetic.main.custome_list_order.view.*

class DetailOrder : AppCompatActivity() {
    var ad_card:adapter_items_card_bascket_2 ? =null
    var item: data_Order? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_order)
        item= intent.getSerializableExtra("data") as data_Order?
        btn_satau.setText(item?.orderStatusTitle)
        ad_card=adapter_items_card_bascket_2(item?.orderItems!!)
        recy_itemss_2.adapter=ad_card
        if (item?.numberTracking!=null)
        {
            textView1581.setText(item?.numberTracking)
            textView36.setText(item?.numberTracking)
        }

        if (item?.deliveryCode!=null)
        {
            textView63.setText(item?.deliveryCode)
        }



        if (item?.addressFullName!=null)
        {
            textView37.setText(item?.addressFullName)
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




        linearLayout555.setOnClickListener {
            finish()
        }


    }
}