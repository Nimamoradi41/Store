package com.example.store.Main_Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.store.R
import kotlinx.android.synthetic.main.fragment_frag__offers.view.*

class Frag_Offers : Fragment() {

    var ad:Adapter_Offer?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_frag__offers, container, false)
        ad= Adapter_Offer(context!!)
        v.recy_offers.adapter=ad

        return v
    }


}