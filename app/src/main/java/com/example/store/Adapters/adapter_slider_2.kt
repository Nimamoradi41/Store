package com.example.store.Adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.store.Main_Fragments.custome_slider
import com.example.store.Main_Fragments.custome_slider_2
import com.example.store.sliders

class adapter_slider_2(fm: FragmentManager, var Img:ArrayList<String>) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
//    return   list.size
    return   Img.size
    }

    override fun getItem(position: Int): Fragment {
        var v=Bundle()
        v.putString("data",Img.get(position))
        var cv=custome_slider_2()
        cv.arguments=v
        return   cv
    }
}