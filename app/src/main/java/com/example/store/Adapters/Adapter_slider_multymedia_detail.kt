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
import com.example.store.sliders
import com.github.islamkhsh.CardSliderAdapter
import kotlinx.android.synthetic.main.fragment_custome_slider.view.*

class Adapter_slider_multymedia_detail(var Img: ArrayList<sliders>, var C: Context) :
    CardSliderAdapter<Adapter_slider_multymedia_detail.view>() {
    var das:data ?=null
    fun clicl(dssa:data)
    {
        this.das
    }
    override fun bindVH(holder: Adapter_slider_multymedia_detail.view, position: Int) {
        var i=Img.get(position)
        if(i?.fileName!=null)
        {
            Log.i("sfkmsmlbab",""+ Constants.BASE_URL+"/Sliders/"+i?.fileName)
            Glide.with(C).load(Constants.BASE_URL+"/Sliders/"+i?.fileName).into(holder.itemView.img_);
        }

        das?.pos(position)
    }

    override fun getItemCount(): Int {
    return  Img.size
    }


    class  view(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter_slider_multymedia_detail.view {
        val view: View = LayoutInflater.from(C).inflate(R.layout.fragment_custome_slider, parent, false)

        return  view(view)

    }

    interface  data{
        public fun pos(I:Int)
    }}