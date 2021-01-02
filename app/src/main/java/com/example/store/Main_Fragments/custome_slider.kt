package com.example.store.Main_Fragments

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
import kotlinx.android.synthetic.main.fragment_custome_slider.view.*

class custome_slider : Fragment() {
   var i: sliders ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            i= arguments?.getSerializable("data") as sliders?
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var V= inflater.inflate(R.layout.fragment_custome_slider, container, false)


        if(i?.fileName!=null)
        {
            Log.i("sfkmsmlbab",""+Constants.BASE_URL+"/Sliders/"+i?.fileName)
            Glide.with(this).load(Constants.BASE_URL+"/Sliders/"+i?.fileName).into(V.img_);
        }
        // Inflate the layout for this fragment
//        V.img_.setImageResource(i)
        return V;
    }


}