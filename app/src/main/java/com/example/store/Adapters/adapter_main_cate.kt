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
import com.example.store.categories
import com.example.store.subCategory
import kotlinx.android.synthetic.main.custome_category.view.*
import kotlinx.android.synthetic.main.custome_main_cate.view.*

class adapter_main_cate(var C:Context,var list: ArrayList<categories>, ) : RecyclerView.Adapter<adapter_main_cate.view>() {

    var d:Data ?=null
    public  class view(itemView: View) : RecyclerView.ViewHolder(itemView)

    public fun D_1(s: Data)
    {
        this.d=s
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
        var V=LayoutInflater.from(parent.context).inflate(R.layout.custome_main_cate, parent, false)
        return view(V)
    }

    override fun onBindViewHolder(holder: view, position: Int) {
//        holder.itemView.imageView5.setImageResource(list.get(position).img!!)
        var item=list.get(position)
        if (list.get(position).image1.isNotEmpty()&&list.get(position).image1!=null)
        {
            Log.i("sfkmsmlbab",""+ Constants.BASE_URL+"/CategoryImg/"+list.get(position).image1)
            Glide.with(C).load(Constants.BASE_URL+"/CategoryImg/"+list.get(position).image1).into(holder.itemView.imageView5);
        }
        holder.itemView.textView8.setText(list?.get(position)?.title)
     holder.itemView.setOnClickListener{
         d?.Clicl(item.id,item.subCategory)

     }
    }

    override fun getItemCount(): Int {
        return    list.size

    }


    interface  Data
    {
        public fun Clicl(CategoryId: String, subCategory: ArrayList<subCategory>)
    }
}