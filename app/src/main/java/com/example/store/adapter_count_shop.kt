package com.example.store

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.store.MainActivity.Companion.mainActivityViewModel
import com.example.store.Models.modeli_category
import com.example.store.R
import com.example.store.VIEWMODEL.Data_card
import kotlinx.android.synthetic.main.custome_cate_3.view.*
import kotlinx.android.synthetic.main.custome_count_shop.view.*

class adapter_count_shop(var list:ArrayList<String>,var d:Dialog,var id:String) : RecyclerView.Adapter<adapter_count_shop.view>() {
    var pos=-1
    var d_2:data_i ?= null
    public  class view(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
//        var v=LayoutInflater.from(parent.context).inflate(R.layout.custome_cate_3,parent,false)
        var v=LayoutInflater.from(parent.context).inflate(R.layout.custome_count_shop,parent,false)
        return  view(v)
    }

    fun clicl(data: data_i)
    {
        this.d_2=data
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: view, position: Int) {
         holder.itemView.textView25.setText(list.get(position))



        holder.itemView.setOnClickListener {
         pos=position
         notifyDataSetChanged()
            var c=Data_card()
            c.count=list.get(position).toInt()
            c.id=id
            d_2?.Data_d(list.get(position).toInt(),id)
//          MainActivity.mainActivityViewModel?.count?.value=c
          d.dismiss()
        }


        if (pos!=-1)
        {
            if (position==pos)
            {
                holder.itemView.textView25.setBackgroundResource(R.drawable.shape_20)
                holder.itemView.textView25.setTextColor(Color.WHITE)
            }else{
                holder.itemView.textView25.setBackgroundColor(Color.WHITE)
                holder.itemView.textView25.setTextColor(R.color.Base_Color)
            }
        }

    }

    override fun getItemCount(): Int {
         return  list.size
    }

    public interface data_i
    {
        public fun Data_d(I:Int,Id:String)
    }

}