package com.example.store.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.store.DetailOrder
import com.example.store.Models.data_Order
import com.example.store.Models.orderItems
import com.example.store.R
import kotlinx.android.synthetic.main.custome_list_order.view.*

class adapter_list_order(var c:Context,var list:ArrayList<data_Order>) : RecyclerView.Adapter<adapter_list_order.view>() {
 init {
     list=ArrayList<data_Order>()
 }

    public class view(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
         var v=LayoutInflater.from(parent.context).inflate(R.layout.custome_list_order,parent,false)
        return  view(v)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: view, position: Int) {

        var item=list.get(position)

//        if(item.orderStatus==1)
//        {
//
//        }else if (item.orderStatus==2)
//        {
//
//        }else if (item.orderStatus==2)
//        {
//
//        }else if (item.orderStatus==2)
//        {
//
//        }else if (item.orderStatus==2)
//        {
//
//        }else if (item.orderStatus==2)
//        {
//
//        }



        if (item.orderStatus==3)
        {
            holder.itemView.button2.setBackgroundResource(R.drawable.shape_39)
            holder.itemView.button2.setText(item.orderStatusTitle)
//            holder.itemView.button2.setTextColor(Color.parseColor("#25C76C"))
        } else if (item.orderStatus==1||item.orderStatus==2)
        {
            holder.itemView.button2.setBackgroundResource(R.drawable.shape_40)
            holder.itemView.button2.setText(item.orderStatusTitle)
//            holder.itemView.button2.setTextColor(Color.parseColor("#F5254F"))
        }  else{
            holder.itemView.button2.setBackgroundResource(R.drawable.shape_40)
            holder.itemView.button2.setText(item.orderStatusTitle)
        }




        holder.itemView.button2.setText(item.orderStatusTitle)
        if (item.numberTracking!=null)
        {
            holder.itemView.textView36.setText(item.numberTracking)
        }
        if (item.addressFullName!=null)
        {
            holder.itemView.textView37.setText(item.addressFullName)
        }
        if (item.addressFullName!=null)
        {
            holder.itemView.textView38.setText(item.addressFullName)
        }
        if (item.fullLocation!=null)
        {
            holder.itemView.textView39.setText(item.fullLocation)
        }
        if (item.pricePayForShow!=null)
        {
            holder.itemView.textView40.setText(item.pricePayForShow)
        }







    holder.itemView.setOnClickListener {
        var I=Intent(c,DetailOrder::class.java)
        I.putExtra("data",item)
        c.startActivity(I)
    }
    }

    override fun getItemCount(): Int {
        return  list.size
    }
}