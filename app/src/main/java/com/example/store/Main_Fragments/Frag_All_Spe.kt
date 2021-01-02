package com.example.store.Main_Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.store.R
import kotlinx.android.synthetic.main.fragment_frag__a_l_l.view.*
import kotlinx.android.synthetic.main.fragment_frag__a_l_l.view.recy_itemsss
import kotlinx.android.synthetic.main.fragment_frag__all__spe.view.*

class Frag_All_Spe : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var V= inflater.inflate(R.layout.fragment_frag__all__spe, container, false)
        V.recy_itemsss_33.layoutManager= GridLayoutManager(activity,2)


        return  V
    }


}