package com.example.store

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
import androidx.core.view.marginRight
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.store.Models.model_Item
import kotlinx.android.synthetic.main.custome_modal.view.*
import kotlinx.android.synthetic.main.custome_special.view.*

class adapter_Spacial(var c: Activity, var list: specials,var W:Int,var H:Int) : RecyclerView.Adapter<adapter_Spacial.view>() {
    var interface_1: Adapter_discounts.Data_dis?=null
    public  class view(itemView: View) : RecyclerView.ViewHolder(itemView)
    fun click(da: Adapter_discounts.Data_dis)
    {
        this.interface_1=da
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
         var V=LayoutInflater.from(parent.context).inflate(R.layout.custome_special_3, parent, false)


//          var v=V.layoutParams as RecyclerView.LayoutParams
//          v.width=pxToDp(w.toFloat()).toInt()+20
//          v.height=pxToDp(w.toFloat()).toInt()*2
//           V.layoutParams= ViewGroup.LayoutParams(v)
//        V.requestLayout()
        return  view(V)
    }

    fun pxToDp(px: Float): Float {
        return (px / Resources.getSystem().getDisplayMetrics().density)
    }
    override fun onBindViewHolder(holder: view, position: Int) {
//
        var Item=list.getProducts().get(position)
//        holder.itemView.imageView2.setImageResource(list.get(position).img!!)


        var v=holder.itemView.layoutParams as RecyclerView.LayoutParams
        v.width=W/2
        v.height=W*3/4
        holder.itemView.layoutParams= ViewGroup.LayoutParams(v)
        holder.itemView.requestLayout()


        // todo درصد تخفیف گرداشت
        if (Item.getDiscountPercent()!=null)
        {
            if (Item.getDiscountPercent()>0)
            {
                holder.itemView.textView3.setText("%" + list.getProducts().get(position).getDiscountPercent())
                holder.itemView.textView5.setText(list.getProducts().get(position).getPriceForShow())
                holder.itemView.textView7.setText(list.getProducts().get(position).getPriceForShow())
            }else{
                holder.itemView.textView3.visibility=View.GONE
                holder.itemView.imageView3.visibility=View.GONE
                holder.itemView.textView5.visibility=View.GONE
                holder.itemView.textView7.visibility=View.GONE

            }
        }else{
            holder.itemView.textView3.visibility=View.GONE
            holder.itemView.imageView3.visibility=View.GONE
            holder.itemView.textView5.visibility=View.GONE
            holder.itemView.textView7.visibility=View.GONE
        }



        if(Item?.getFirstImage()!=null)
        {
            Log.i("sfkmsmlbab",""+Constants.BASE_URL+"/Images/"+Item?.getFirstImage())
            Glide.with(c).load(Constants.BASE_URL+"/Images/"+Item?.getFirstImage()).into(holder.itemView.imageView2);
        }

        holder.itemView.textView4.setText(list.getProducts().get(position).getTitle())
//        holder.itemView.textView3.setText("%"+list.getProducts().get(position).getDiscountPercent())


//        holder.itemView.textView5.setText(list.getProducts().get(position).getPriceForShow())
//        holder.itemView.textView7.setText(list.getProducts().get(position).getPriceForShow())

        if (Item.getCurrentReserved()>0)
        {
            holder.itemView.button.setText(Item.getCurrentReserved().toString()+" عدد ")
            holder.itemView.button.animate().alpha(1f).setDuration(500).start()
        }else  {
            holder.itemView.button.setText("افزودن به سبد خرید")
            holder.itemView.button.animate().alpha(1f).setDuration(500).start()
        }



        holder.itemView.textView6.setText(list.getProducts().get(position).getPriceAfterDiscountForShow())
        holder.itemView.setOnClickListener {
            var I=Intent(c,ItemDetail::class.java)
            I.putExtra("data",Item)
            I.putExtra("pos",position)
            c.startActivityForResult(I,20)
        }
        holder.itemView.button.setOnClickListener {
            val d=Dialog(c, R.style.CustomDialog)
            d.setCancelable(true)
            var v=LayoutInflater.from(c).inflate(R.layout.custome_modal, null, false)
            d.setContentView(v)
            var i_Count=ArrayList<String> ()

            var c = list.getProducts().get(position).getMaxCountReserve()
            var i = 0
            while (i < c) {
                i++
                i_Count.add(i.toString())
            }
            i_Count.add(0,"0")
            var ad_=adapter_count_shop(i_Count,d,list.getProducts().get(position).id)
            ad_.clicl(object : adapter_count_shop.data_i {
                override fun Data_d(I: Int, Id: String) {
                    if (Item.getCurrentReserved()!=I)
                    {
                        interface_1?.Data(I,Id,position)
                    }
                }

            })
            v.recy_itemmmms.adapter=ad_
            d.window?.setLayout(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.MATCH_PARENT
            )
            d.window?.setBackgroundDrawable(ColorDrawable((Color.TRANSPARENT)))
            v.imageView15.setOnClickListener {
                d.dismiss()
            }

            v.cccv.setOnClickListener {

            }
            v.close.setOnClickListener {
                d.dismiss()
            }
            d.show()
        }
    }

    override fun getItemCount(): Int {
       return  list.getProducts().size
    }

}


