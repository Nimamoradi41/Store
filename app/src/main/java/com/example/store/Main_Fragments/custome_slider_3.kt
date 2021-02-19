package com.example.store.Main_Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.store.Constants
import com.example.store.R
import com.example.store.sliders
import com.github.tntkhang.fullscreenimageview.library.FullScreenImageViewActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_custome_slider.view.*

class custome_slider_3 : Fragment() {
   var i: String ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            i= arguments?.getString("data",null)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        var V= inflater.inflate(R.layout.fragment_custome_slider, container, false)
        var V= inflater.inflate(R.layout.fragment_custome_slider_2, container, false)


        if(i!=null)
        {
            Log.i("kfnvslkfbzdfnbjzfb",""+Constants.BASE_URL+"/Sliders/"+i)
//            Glide.with(this).load(Constants.BASE_URL+"/Sliders/"+i).into(V.img_);
            Picasso.get().load(Constants.BASE_URL+"/Sliders/"+i).placeholder(R.drawable.holder).into(V.img_)
//            Glide.with(this).load(Constants.BASE_URL+"/Sliders/"+i).into();
        }


        V.img_.setOnClickListener {
            val fullImageIntent = Intent(activity, FullScreenImageViewActivity::class.java)
            var d=ArrayList<String>()
            d.add(Constants.BASE_URL + "/Sliders/" + i)
            fullImageIntent.putExtra(FullScreenImageViewActivity.URI_LIST_DATA, d)
            fullImageIntent.putExtra(FullScreenImageViewActivity.IMAGE_FULL_SCREEN_CURRENT_POS, 0)
            startActivity(fullImageIntent)
        }
        // Inflate the layout for this fragment
//        V.img_.setImageResource(i)
        return V;
    }


}