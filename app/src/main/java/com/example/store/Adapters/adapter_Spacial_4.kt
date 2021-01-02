package com.example.store.Adapters


import android.app.ActionBar
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.store.*
import com.example.store.Models.model_Item
import kotlinx.android.synthetic.main.custome_modal.view.*
import kotlinx.android.synthetic.main.custome_special.view.*
import kotlinx.android.synthetic.main.custome_special.view.button
import kotlinx.android.synthetic.main.custome_special.view.textView3
import kotlinx.android.synthetic.main.custome_special.view.textView4
import kotlinx.android.synthetic.main.custome_special.view.textView5
import kotlinx.android.synthetic.main.custome_special.view.textView7
import kotlinx.android.synthetic.main.custome_special_2.view.*

class adapter_Spacial_4(var c:Activity, var list:ArrayList<products>) : RecyclerView.Adapter<adapter_Spacial_4.view>() {
    public  class view(itemView: View) : RecyclerView.ViewHolder(itemView)
    var interface_1: Adapter_discounts.Data_dis?=null
    var loadmore: Boolean=false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
        var V=LayoutInflater.from(parent.context).inflate(R.layout.custome_special_2,parent,false)
        return  view(V)
    }
    fun click(da: Adapter_discounts.Data_dis)
    {
        this.interface_1=da
    }
    override fun onBindViewHolder(holder: view, position: Int) {
//        holder.itemView.imageView2.setImageResource(list.get(position).img!!)
        holder.itemView.textView4.setText(list.get(position).title!!)
        var Item=list.get(position)


        if(Item?.getFirstImage()!=null)
        {
            Log.i("sfkmsmlbab",""+Constants.BASE_URL+"/Images/"+Item?.getFirstImage())
            Glide.with(c).load(Constants.BASE_URL+"/Images/"+Item?.getFirstImage()).into(holder.itemView.imageView85);
        }
//        holder.itemView.imageView2.setImageResource(list.get(position).img!!)
        holder.itemView.textView4.setText(list.get(position).getTitle())
        holder.itemView.textView89.setText("%"+list.get(position).getDiscountPercent())
        holder.itemView.textView7499.setText(list.get(position).getPriceForShow())
        holder.itemView.textView55959.setText(list.get(position).getPriceForShow())
        if (Item.getCurrentReserved()>0)
        {
            holder.itemView.button.setText(Item.getCurrentReserved().toString()+" عدد ")
            holder.itemView.button.animate().alpha(1f).setDuration(500).start()
        }else  {
            holder.itemView.button.setText("افزودن به سبد خرید")
            holder.itemView.button.animate().alpha(1f).setDuration(500).start()
        }


        holder.itemView.setOnClickListener {
            var I=Intent(c,ItemDetail::class.java)
            I.putExtra("data",Item)
            c.startActivityForResult(I,20)
        }

        holder.itemView.button.setOnClickListener {
            val d=Dialog(c, R.style.CustomDialog)
            d.setCancelable(true)
            var v=LayoutInflater.from(c).inflate(R.layout.custome_modal, null, false)
            d.setContentView(v)
            var i_Count=ArrayList<String> ()

            var c = list.get(position).getMaxCountReserve()
            var i = 0
            while (i < c) {
                i++
                i_Count.add(i.toString())
            }
            i_Count.add(0,"0")
            var ad_= adapter_count_shop(i_Count,d,list.get(position).id)
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
        return  list.size
    }

}



