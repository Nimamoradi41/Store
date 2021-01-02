package com.example.store.Main_Fragments

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.store.ItemDetail
import com.example.store.Models.model_Item
import com.example.store.R
import kotlinx.android.synthetic.main.custome_modal.view.*
import kotlinx.android.synthetic.main.custome_special.view.*
import kotlinx.android.synthetic.main.fragment_frag__offers.view.*

class Adapter_Offer(var c: Context) : RecyclerView.Adapter<Adapter_Offer.view>() {

    public  class view(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {

        var V= LayoutInflater.from(parent.context).inflate(R.layout.custome_offer,parent,false)

        return  view(V)
    }

    override fun onBindViewHolder(holder: view, position: Int) {



    }

    override fun getItemCount(): Int {
        return  10
    }

}