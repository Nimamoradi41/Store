package com.example.store.Adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.store.Models.modeli_category
import com.example.store.R
import com.example.store.data_2
import com.example.store.subCategory
import kotlinx.android.synthetic.main.custome_cate_3.view.*

class adapter_cate_3(var list:ArrayList<subCategory>) : RecyclerView.Adapter<adapter_cate_3.view>() {
    var ad_cate:adapter_Category ?=null
    var data_2:Data_2 ?=null
    var Selected=-1


    public fun Click(d: Data_2)
    {
        this.data_2=d
    }
    public  class view(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
        var v=LayoutInflater.from(parent.context).inflate(R.layout.custome_cate_3,parent,false)

        return  view(v)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: view, position: Int) {

        var Item=list.get(position)
//        if (Selected!=-1)
//        {
            if (Item.selected)
            {
                holder.itemView.textView9.setBackgroundResource(R.drawable.shap_3)
                holder.itemView.textView9.setTextColor(Color.WHITE)
            }else{
                holder.itemView.textView9.setBackgroundColor(Color.WHITE)
                holder.itemView.textView9.setTextColor(R.color.Base_Color)
            }
//        }



        holder.itemView.textView9.setText(list.get(position).title)
        holder.itemView.setOnClickListener {


//            if (Selected!=-1)
//            {
//                list.get(Selected).selected=true
//                notifyItemChanged(Selected)
//                data_2?.data_a(list.get(position),position)
//            }else{
//                Selected=position
//                Item.selected=true
//                notifyItemChanged(Selected)
//                data_2?.data_a(list.get(position),position)
//            }

            if (Selected!=position)
            {
                list.get(Selected).selected=false
                notifyItemChanged(Selected)
                Selected=position
                Item.selected=true
                notifyItemChanged(Selected)
                data_2?.data_a(list.get(position),position)
            }

//            if (Selected!=position)
//            {
//                Selected=position
//                data_2?.data_a(list.get(position),position)
//            }

//            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
         return  list.size
    }


    public interface Data_2{
        public fun data_a(p:subCategory,Pos:Int)


    }
}