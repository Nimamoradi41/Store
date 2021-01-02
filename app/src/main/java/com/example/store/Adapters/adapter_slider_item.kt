package com.example.store.Adapters

import com.example.store.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.itemslider.view.*


class adapter_slider_item(var s: ArrayList<String>, var context: Context): SliderViewAdapter<adapter_slider_item.view>() {

    public class view(itemView: View?) : SliderViewAdapter.ViewHolder(itemView) {

    }
    override fun getCount(): Int {
    return   3
    }

    override fun onCreateViewHolder(parent: ViewGroup?): view {
       var v=LayoutInflater.from(parent?.context).inflate(com.example.store.R.layout.itemslider,parent,false)
        return view(v)
    }

    override fun onBindViewHolder(viewHolder: adapter_slider_item.view?, position: Int) {
        viewHolder?.itemView?.rootView?.img?.setBackgroundResource(com.example.store.R.drawable.item_1);
    }
}