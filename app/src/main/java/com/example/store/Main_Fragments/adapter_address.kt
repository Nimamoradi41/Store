package com.example.store.Main_Fragments

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.store.ItemDetail
import com.example.store.MapActivity
import com.example.store.ModelAddress
import com.example.store.Models.model_Item
import com.example.store.R
import kotlinx.android.synthetic.main.custome_modal.view.*
import kotlinx.android.synthetic.main.custome_special.view.*
import kotlinx.android.synthetic.main.customeaddress.view.*
import okhttp3.MediaType
import okhttp3.RequestBody

class adapter_address(var c: Activity) : RecyclerView.Adapter<adapter_address.view>() {



    var DA:data_Type?=null

   var Selected=-1;


    fun  DATA(DA:data_Type)
    {
        this.DA=DA
    }

    var list:ArrayList<ModelAddress> ?=null
    public  class view(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
        var V= LayoutInflater.from(parent.context).inflate(R.layout.customeaddress,parent,false)
        return  view(V)
    }

    override fun onBindViewHolder(holder: view, position: Int) {

        var item=list?.get(position)








        if (item?.fullLocation!=null)
        {
            holder.itemView.textView36.setText(item.fullLocation?.trim().toString())
        }else{
            holder.itemView.textView36.setText("نامشخص")
        }


        if (item?.home!=null)
        {
            holder.itemView.textView37.setText(item.home)
        }else{
            holder.itemView.textView37.setText("نامشخص")
        }


        if (item?.floor!=null)
        {
            holder.itemView.textView38.setText(item.floor)
        }else{
            holder.itemView.textView38.setText("نامشخص")
        }


        if (item?.unit!=null)
        {
            holder.itemView.textView39.setText(item.unit)
        }else{
            holder.itemView.textView39.setText("نامشخص")
        }

        if (item?.plaque!=null)
        {
            holder.itemView.textView40.setText(item.plaque)
        }else{
            holder.itemView.textView40.setText("نامشخص")
        }



        if (item?.fullLocation!=null)
        {
            holder.itemView.textView36.setText(item.fullLocation?.trim().toString())
        }else{
            holder.itemView.textView36.setText("نامشخص")
        }




        holder.itemView.imageView32.setOnClickListener {
            Toast.makeText(c,"برای حذف آدرس بر روی دکمه نگه دارید",Toast.LENGTH_SHORT).show()
        }



        holder.itemView.imageView32.setOnLongClickListener {
            DA?.Del(item?.id.toString(),position)
            return@setOnLongClickListener true
        }





        holder.itemView.setOnLongClickListener {
            DA?.Edit(item!!,position)
            return@setOnLongClickListener true
        }








    }

    override fun getItemCount(): Int {
        if (list!=null)
        {
           return list?.size!!
        }
        return   0
    }


    public interface data_Type{
        public fun Del(S:String,Pos:Int)
        public fun Edit(S:ModelAddress,Pos:Int)
    }






}
