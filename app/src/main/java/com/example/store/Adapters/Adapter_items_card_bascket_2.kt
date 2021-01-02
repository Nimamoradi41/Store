package com.example.store.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.store.Models.model_Item
import com.example.store.Models.orderItems
import com.example.store.R
import kotlinx.android.synthetic.main.custome_card_bascket.view.*
import kotlinx.android.synthetic.main.custome_card_bascket.view.textView19
import kotlinx.android.synthetic.main.custome_card_bascket.view.textView21
import kotlinx.android.synthetic.main.custome_card_bascket_2.view.*

class adapter_items_card_bascket_2(var v :ArrayList<orderItems>) : RecyclerView.Adapter<adapter_items_card_bascket_2.view>() {
    init {
        v=ArrayList<orderItems>()
    }
    public  class  view(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
//         var v=LayoutInflater.from(parent.context).inflate(R.layout.custome_card_bascket,parent,false)
         var v=LayoutInflater.from(parent.context).inflate(R.layout.custome_card_bascket_2,parent,false)
        return  view(v)
    }

    override fun onBindViewHolder(holder: view, position: Int) {

        var item=v.get(position)
        if (item.productTitle!=null)
        {
            holder.itemView.textView19.setText(item.productTitle)
        }

        if (item.pricePayForShow!=null)
        {
            holder.itemView.textView21.setText(item.pricePayForShow)
        }


        if (item.count!=null)
        {
            holder.itemView.textView62.setText(item.count.toString()!!)
        }



    }

    override fun getItemCount(): Int {
        if (v!=null)
        {
            return v.size
        }
        return 0
    }
}