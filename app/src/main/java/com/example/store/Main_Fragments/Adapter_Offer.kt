package com.example.store.Main_Fragments

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.example.store.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.custome_modal.view.*
import kotlinx.android.synthetic.main.custome_offer.view.*
import kotlinx.android.synthetic.main.custome_special.view.*
import kotlinx.android.synthetic.main.fragment_frag__offers.view.*


class Adapter_Offer(var c: Context,var vv:View) : RecyclerView.Adapter<Adapter_Offer.view>() {
    var list :ArrayList<data_6> ?=null
    init {

        list=ArrayList<data_6>()
    }
    public  class view(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {

        var V= LayoutInflater.from(parent.context).inflate(R.layout.custome_offer, parent, false)

        return  view(V)
    }

    override fun onBindViewHolder(holder: view, position: Int) {

        var Item=list?.get(position)

        if (Item?.discountCode!=null)
        {
            holder.itemView.textView36.setText(Item.discountCode)
        }

        if (Item?.discountPercent!=null)
        {
            holder.itemView.textView37.setText(Item.discountPercent.toString() + " درصد ")
        }

        if (Item?.minPriceAccess!=null)
        {
            holder.itemView.textView39.setText(Item.minPriceAccess.toString() + " هزار تومان ")
        }



        holder.itemView.button4.setOnClickListener {
            val clipboard: ClipboardManager? = c.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
            val clip = ClipData.newPlainText(label.toString(), Item?.discountCode)
            clipboard?.setPrimaryClip(clip)
            Snackbar.make(vv,"کد تخفیف کپی شد",Snackbar.LENGTH_SHORT).show()
        }




    }

    override fun getItemCount(): Int {
      return list?.size!!
    }

}