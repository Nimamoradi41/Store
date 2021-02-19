package com.example.store.Adapters

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.store.Adapter_discounts
import com.example.store.Constants
import com.example.store.Models.model_Item
import com.example.store.Models.orderItems
import com.example.store.R
import com.example.store.adapter_count_shop
import kotlinx.android.synthetic.main.custome_card_bascket.view.*
import kotlinx.android.synthetic.main.custome_card_bascket.view.button
import kotlinx.android.synthetic.main.custome_card_bascket_3.view.*
import kotlinx.android.synthetic.main.custome_modal.view.*
import kotlinx.android.synthetic.main.custome_special.view.*

class adapter_items_card_bascket(var C:Context) : RecyclerView.Adapter<adapter_items_card_bascket.view>() {
    var interface_1: Adapter_discounts.Data_dis?=null
    var V :ArrayList<orderItems> ? =null
    public  class  view(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
//         var v=LayoutInflater.from(parent.context).inflate(R.layout.custome_card_bascket,parent,false)
         var v=LayoutInflater.from(parent.context).inflate(R.layout.custome_card_bascket_3,parent,false)
        return  view(v)
    }
    fun click(da: Adapter_discounts.Data_dis)
    {
        this.interface_1=da
    }
    override fun onBindViewHolder(holder: view, position: Int) {
        var Item=V?.get(position)



           holder.itemView.textView85.setText(V?.get(position)?.productTitle.toString())
           holder.itemView.textView87.setText(V?.get(position)?.pricePayForShow.toString())



        if (Item?.discountPercent!=null)
        {
            if (!Item?.discountPercent?.equals(0)!!)
            {
                holder.itemView.textView86.visibility=View.VISIBLE
                holder.itemView.textView86.setText("${Item.discountPercent}%")
            }else{
                holder.itemView.textView86.visibility=View.GONE
            }
        }else{
            holder.itemView.textView86.visibility=View.GONE
        }


        holder.itemView.imageView52.setOnClickListener {
            interface_1?.Data(0, V?.get(position)?.productId.toString(),position)
        }

        holder.itemView.textView91.setText(Item?.count.toString())

        holder.itemView.imageView54.setOnClickListener {
            if (V?.get(position)?.maxCountReserve==0)
            {
                Toast.makeText(C,"عدم موجودی", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (Item?.count!= Item?.count!! -1)
            {
                interface_1?.Data(Item?.count!! -1, Item.productId.toString(),position)
            }
        }

        holder.itemView.imageView51.setOnClickListener {
            if (V?.get(position)?.maxCountReserve==0)
                    {
                        Toast.makeText(C,"عدم موجودی", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    if (Item?.count!= Item?.count!! +1)
                    {
                        interface_1?.Data(Item?.count!! +1, Item.productId.toString(),position)
                    }
        }

        if(V?.get(position)?.priceForShow!=null)
        {
            Log.i("dknvnsnvnds", "A")
            holder.itemView.view6.visibility=View.VISIBLE
            holder.itemView.textView88.visibility=View.VISIBLE
//            holder.itemView.textView86.visibility=View.VISIBLE
            holder.itemView.textView88.setText(V?.get(position)?.priceForShow.toString())
            holder.itemView.view6.setText(V?.get(position)?.priceForShow.toString())
        }else{
            Log.i("dknvnsnvnds", "B")
            holder.itemView.view6.visibility=View.GONE
            holder.itemView.textView88.visibility=View.GONE
//            holder.itemView.textView86.visibility=View.GONE
        }


        if(Item?.productFirstImage!=null)
        {
            Log.i("sfkmsmlbab",""+ Constants.BASE_URL+"/Sliders/"+Item.productFirstImage)
            Glide.with(C).load(Constants.BASE_URL+"/Images/"+Item?.productFirstImage).into(holder.itemView.imageView53);
        }






        // TODO OLD
//   holder.itemView.imageView8.setImageResource(v.get(position).img!!)
//   holder.itemView.textView19.setText(V?.get(position)?.productTitle.toString())
//   holder.itemView.textView21.setText(V?.get(position)?.pricePayForShow.toString())



//        textView85


//        if (V?.get(position)?.count==0)
//        {
//            holder.itemView.button.visibility=View.GONE
//        }else
//        {
//            holder.itemView.button.visibility=View.VISIBLE
//            holder.itemView.button.setText(V?.get(position)?.count.toString()+" عدد ")
//        }
//
//

//
//
//        holder.itemView.button.setOnClickListener {
//            val d= Dialog(C, R.style.CustomDialog)
//            d.setCancelable(true)
//            var v=LayoutInflater.from(C).inflate(R.layout.custome_modal, null, false)
//            d.setContentView(v)
//            var i_Count=ArrayList<String> ()
//            var r = V?.get(position)?.maxCountReserve
//            var i = 0
//            while (i < r!!) {
//                i++
//                i_Count.add(i.toString())
//            }
//            i_Count.add(0,"0")
//            var ad_= adapter_count_shop(i_Count,d, V?.get(position)?.productId.toString())
//            v.recy_itemmmms.adapter=ad_
//            ad_.clicl(object : adapter_count_shop.data_i {
//                override fun Data_d(I: Int, Id: String) {
//                    if (V?.get(position)?.maxCountReserve==0)
//                    {
//                        Toast.makeText(C,"عدم موجودی", Toast.LENGTH_SHORT).show()
//                        return
//                    }
//                    if (Item?.count!=I)
//                    {
//                        interface_1?.Data(I, Id,position)
//                    }
//                }
//
//            })
//            d.window?.setLayout(
//                    ConstraintLayout.LayoutParams.MATCH_PARENT,
//                    ConstraintLayout.LayoutParams.MATCH_PARENT
//            )
//            d.window?.setBackgroundDrawable(ColorDrawable((Color.TRANSPARENT)))
//            v.imageView15.setOnClickListener {
//                d.dismiss()
//            }
//
//            v.cccv.setOnClickListener {
//
//            }
//            v.close.setOnClickListener {
//                d.dismiss()
//            }
//            d.show()
//        }
//
//
//        holder.itemView.imageView9.setOnClickListener {
//            Log.i("dvlmsdbl","dbfb")
//            interface_1?.Data(0, V?.get(position)?.productId.toString(),position)
//        }
    }
    override fun getItemCount(): Int {
        if (V!=null)
        {
            Log.i("dvkavnkadv", V?.size!!.toString())
            return V?.size!!
        }
        Log.i("dvkavnkadv","B")
        return 0



    }
}