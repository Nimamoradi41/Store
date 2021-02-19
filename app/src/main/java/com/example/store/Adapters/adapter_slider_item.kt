package com.example.store.Adapters

import com.example.store.R
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.store.Constants
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.custome_special.view.*
import kotlinx.android.synthetic.main.itemslider.view.*


class adapter_slider_item(var s: ArrayList<String>, var context: Context): SliderViewAdapter<adapter_slider_item.view>() {

    public class view(itemView: View?) : SliderViewAdapter.ViewHolder(itemView) {

    }
    override fun getCount(): Int {
    return  s.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): view {
       var v=LayoutInflater.from(parent?.context).inflate(com.example.store.R.layout.itemslider,parent,false)
        return view(v)
    }

    override fun onBindViewHolder(viewHolder: adapter_slider_item.view?, position: Int) {
        if(s.get(position)!=null)
        {
//            Log.i("sfkmsmlbab",""+ Constants.BASE_URL+"/Sliders/"+Item?.getFirstImage())
            Glide.with(context).load(Constants.BASE_URL+"/Images/"+s.get(position)).into(viewHolder?.itemView?.rootView?.img!!);
        }


    }
}