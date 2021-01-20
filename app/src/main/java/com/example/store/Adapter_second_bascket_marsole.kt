package com.example.store

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.store.Adapters.Adapter_second_bascket
import com.example.store.Models.orderItems
import kotlinx.android.synthetic.main.custome_card_bascket.view.*
import kotlinx.android.synthetic.main.custome_card_bascket.view.imageView8
import kotlinx.android.synthetic.main.custome_second_address_marsole.view.*
import java.util.*
import kotlin.collections.ArrayList

class Adapter_second_bascket_marsole(var items_2:ArrayList<orderItems>,var C:Context) : RecyclerView.Adapter<Adapter_second_bascket_marsole.view>() {


    fun Onclick(data: Data?) {

    }




    class view(itemView: View) : RecyclerView.ViewHolder(itemView) {



    }
    interface Data {
        fun Setdata(item: MODEL_ITEM?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {

//         View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cate,parent,false);
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custome_second_address_marsole, parent, false)
        return view(view)
    }

    override fun onBindViewHolder(holder: view, position: Int) {
        var Item=items_2.get(position)
        if(Item?.productFirstImage!=null)
        {
            Log.i("sfkmsmlbab",""+ Constants.BASE_URL+"/Sliders/"+Item.productFirstImage)
            Glide.with(C).load(Constants.BASE_URL+"/Images/"+Item?.productFirstImage).into(holder.itemView.imageView46);
        }
    }

    override fun getItemCount(): Int {
    return    items_2.size
    }
}
