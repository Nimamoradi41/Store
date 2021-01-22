package com.example.store

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
import kotlinx.android.synthetic.main.custome_modal.view.*
import kotlinx.android.synthetic.main.custome_special.view.*

class Adapter_productBoxDtos_2(var C: Activity, var list: productBoxDtos,var W:Int) : RecyclerView.Adapter<Adapter_productBoxDtos_2.view>() {
    var interface_1: Data_BTO_2?=null
    public  class view(itemView: View) : RecyclerView.ViewHolder(itemView)


    fun click_2(da: Data_BTO_2)
    {
        this.interface_1=da
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
         var V=LayoutInflater.from(parent.context).inflate(R.layout.custome_special_3, parent, false)
//         var V=LayoutInflater.from(parent.context).inflate(R.layout.custome_productboxdtos, parent, false)
//          var v=V.layoutParams as RecyclerView.LayoutParams
//          v.width=pxToDp(w.toFloat()).toInt()+20
//          v.height=pxToDp(w.toFloat()).toInt()*2
//           V.layoutParams= ViewGroup.LayoutParams(v)
//        V.requestLayout()
        return  view(V)
    }


    override fun onBindViewHolder(holder: view, position: Int) {
        var Item=list.getProducts().get(position)



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
            Glide.with(C).load(Constants.BASE_URL+"/Images/"+Item?.getFirstImage()).into(holder.itemView.imageView2);
        }

        holder.itemView.textView4.setText(list.getProducts().get(position).getTitle())
//        holder.itemView.textView3.setText("%"+list.getProducts().get(position).getDiscountPercent())


//        holder.itemView.textView5.setText(list.getProducts().get(position).getPriceForShow())
//        holder.itemView.textView7.setText(list.getProducts().get(position).getPriceForShow())


        holder.itemView.textView6.setText(list.getProducts().get(position).getPriceAfterDiscountForShow())

//        if (Item.getCurrentReserved()>0)
//        {
//            holder.itemView.button.setText(Item.getCurrentReserved().toString()+" عدد ")
//            holder.itemView.button.animate().alpha(1f).setDuration(500).start()
//        }else  {
//            holder.itemView.button.setText("افزودن به سبد خرید")
//            holder.itemView.button.animate().alpha(1f).setDuration(500).start()
//        }

        if (Item.getCurrentReserved()>0)
        {
            holder.itemView.button.setText(Item.getCurrentReserved().toString()+" عدد ")
//            holder.itemView.button.animate().alpha(1f).setDuration(500).start()
            holder.itemView.button.animate().scaleX(0f).scaleY(0f).setDuration(200).withEndAction {
                holder.itemView.button.animate().scaleX(1f).scaleY(1f).setDuration(200)
            }.start()
        }else  {
            holder.itemView.button.setText("افزودن به سبد خرید")
//            holder.itemView.button.animate().alpha(1f).setDuration(500).start()
            holder.itemView.button.animate().scaleX(0f).scaleY(0f).setDuration(200).withEndAction {
                holder.itemView.button.animate().scaleX(1f).scaleY(1f).setDuration(200)
            }.start()
        }

        holder.itemView.setOnClickListener {
            var I=Intent(C,ItemDetail::class.java)
            I.putExtra("data",Item)
            I.putExtra("pos",position)
            C.startActivityForResult(I,20)
        }



        holder.itemView.button.setOnClickListener {
            val d=Dialog(C, R.style.CustomDialog)
            d.setCancelable(true)
            var v=LayoutInflater.from(C).inflate(R.layout.custome_modal, null, false)
            d.setContentView(v)
            var i_Count=ArrayList<String> ()

            var c = list.getProducts().get(position).getMaxCountReserve()
            var i = 0
            while (i < c) {
                i++
                i_Count.add(i.toString())
            }
            i_Count.add(0,"0")
            var ad_=adapter_count_shop(i_Count,d,list.getProducts().get(position).getId())
            v.recy_itemmmms.adapter=ad_
            ad_.clicl(object :adapter_count_shop.data_i{
                override fun Data_d(I: Int, Id: String) {
                    if (Item.getCurrentReserved()!=I)
                    {
                        interface_1?.Data(I,Id,position,this@Adapter_productBoxDtos_2)
                    }

                }

            })
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
//        holder.itemView.imageView2.setImageResource(list.get(position).img!!)



    }

    override fun getItemCount(): Int {
       return  list.getProducts().size
    }


    public interface  Data_BTO_2{
        public fun Data(I:Int,ID:String,Pos:Int,Ad:Adapter_productBoxDtos_2)
    }

}


