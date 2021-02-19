package com.example.store.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.store.Constants
import com.example.store.R
import com.example.store.Models.modeli_category
import com.example.store.categories
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custome_category.view.*
import kotlinx.android.synthetic.main.fragment_custome_slider.view.*

class adapter_Category(var c:Context,var list:ArrayList<categories>) : RecyclerView.Adapter<adapter_Category.view>() {
    public class view(itemView: View) : RecyclerView.ViewHolder(itemView)
    var d: adapter_main_cate.Data?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
         var V=LayoutInflater.from(parent.context).inflate(R.layout.custome_category,parent,false)
        return  view(V)
    }
    public fun D_1(s: adapter_main_cate.Data)
    {
        this.d=s
    }
    override fun onBindViewHolder(holder: view, position: Int) {
//       holder.itemView.imageView4.setImageResource(list.get(position).img!!)

        var item=list.get(position)
        Log.i("sfkmsmlbab",""+ list.get(position).image1)

        if (list.get(position).image1.isNotEmpty()&&list.get(position).image1!=null)
        {
                Log.i("sfkmsmlbab",""+ Constants.BASE_URL+"/CategoryImg/"+list.get(position).image1)
//                Glide.with(c).load(Constants.BASE_URL+"/CategoryImg/"+list.get(position).image1).into(holder.itemView.imageView4);
            Picasso.get().load(Constants.BASE_URL+"/CategoryImg/"+list.get(position).image1).placeholder(R.drawable.holder).into(holder.itemView.imageView4)
        }
       holder.itemView.textView2.setText(list.get(position).title)
        holder.itemView.setOnClickListener {
            d?.Clicl(item.id,item.subCategory)
        }
    }

    override fun getItemCount(): Int {
        return  list.size
    }
}