package com.example.store

import android.app.ActionBar
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Constraints
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.store.Models.model_Item
import kotlinx.android.synthetic.main.custome_modal.view.*
import kotlinx.android.synthetic.main.custome_productboxdtos.view.*
import kotlinx.android.synthetic.main.custome_productboxdtos.view.titile_discounts
import kotlinx.android.synthetic.main.custome_special.view.*
import kotlinx.android.synthetic.main.fragment_mainfrag.view.*

class Adapter_productBoxDtos(var C: Activity, var list: ArrayList<productBoxDtos>,var W:Int) : RecyclerView.Adapter<Adapter_productBoxDtos.view>() {
    var interface_1: Adapter_productBoxDtos_2.Data_BTO_2?=null
    public  class view(itemView: View) : RecyclerView.ViewHolder(itemView)
    fun click_2(da: Adapter_productBoxDtos_2.Data_BTO_2)
    {
        this.interface_1=da
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
//         var V=LayoutInflater.from(parent.context).inflate(R.layout.custome_special, parent, false)
         var V=LayoutInflater.from(parent.context).inflate(R.layout.custome_productboxdtos, parent, false)
//          var v=V.layoutParams as RecyclerView.LayoutParams
//          v.width=pxToDp(w.toFloat()).toInt()+20
//          v.height=pxToDp(w.toFloat()).toInt()*2
//           V.layoutParams= ViewGroup.LayoutParams(v)
//        V.requestLayout()
        return  view(V)
    }
    override fun onBindViewHolder(holder: view, position: Int) {
//        holder.itemView.imageView2.setImageResource(list.get(position).img!!)





        Log.i("dvmlkdnvksfbnvvxzcv",list.get(position).getCategory().getTitle())
        holder.itemView.titile_discounts.setText(list.get(position).getCategory().getTitle())
        var vv=Adapter_productBoxDtos_2(C,list.get(position),W)
        vv.click_2(object : Adapter_productBoxDtos_2.Data_BTO_2{
            override fun Data(I: Int, ID: String, Pos: Int, Ad: Adapter_productBoxDtos_2) {
                 interface_1?.Data(I,ID,Pos,Ad)
            }

        })

        holder.itemView.bto_more.setOnClickListener {

                var I=Intent(C,MultyActivity_2::class.java)
                I.putExtra("Type","X")
                I.putExtra("data",list.get(position).getProducts())
                I.putExtra("cateid",list.get(position).getCategory().id)
                I.putExtra("t",1)
                C.startActivityForResult(I,26)
        }
        holder.itemView.recy_itemsss.layoutManager=LinearLayoutManager(C, LinearLayoutManager.HORIZONTAL,true)
        holder.itemView.recy_itemsss.adapter=vv


    }

    override fun getItemCount(): Int {
       return  list.size
    }





}


