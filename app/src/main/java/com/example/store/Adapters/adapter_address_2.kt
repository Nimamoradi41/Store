package com.example.store.Adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.store.ModelAddress
import com.example.store.MultyActivity_2
import com.example.store.R
import kotlinx.android.synthetic.main.custome_cate_3.view.*
import kotlinx.android.synthetic.main.customeaddress.view.*
import kotlinx.android.synthetic.main.customeaddress.view.imageView32
import kotlinx.android.synthetic.main.customeaddress.view.textView37
import kotlinx.android.synthetic.main.customeaddress.view.textView38
import kotlinx.android.synthetic.main.customeaddress.view.textView39
import kotlinx.android.synthetic.main.customeaddress.view.textView40
import kotlinx.android.synthetic.main.customeaddress_2.view.*

class adapter_address_2 (var c: Activity) : RecyclerView.Adapter<adapter_address_2.view>() {
    var DA: data_Type? = null

    var Selected = -1;


    fun DATA(DA: data_Type) {
        this.DA = DA
    }

    var list: ArrayList<ModelAddress>? = null

    public class view(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
        var V = LayoutInflater.from(parent.context).inflate(R.layout.customeaddress_2, parent, false)
        return view(V)
    }

    override fun onBindViewHolder(holder: view, position: Int) {

        var item = list?.get(position)




        if (Selected!=-1)
        {
            if (position==Selected)
            {
                 holder.itemView.rad.isChecked=true;
            }else{
                holder.itemView.rad.isChecked=false;
            }
        }


        holder.itemView.setOnClickListener {
            MultyActivity_2.address=item
            MultyActivity_2.Pos=position
            Selected=position;
            notifyDataSetChanged()
        }
        holder.itemView.rad.setOnClickListener {
            MultyActivity_2.address=item
            MultyActivity_2.Pos=position
            Selected=position;
            notifyDataSetChanged()
        }


        if (item?.fullLocation != null) {
            holder.itemView.rad.setText(item.fullLocation?.trim().toString())
        } else {
            holder.itemView.rad.setText("نامشخص")
        }


        if (item?.home != null) {
            holder.itemView.textView37.setText(item.home)
        } else {
            holder.itemView.textView37.setText("نامشخص")
        }


        if (item?.floor != null) {
            holder.itemView.textView38.setText(item.floor)
        } else {
            holder.itemView.textView38.setText("نامشخص")
        }


        if (item?.unit != null) {
            holder.itemView.textView39.setText(item.unit)
        } else {
            holder.itemView.textView39.setText("نامشخص")
        }

        if (item?.plaque != null) {
            holder.itemView.textView40.setText(item.plaque)
        } else {
            holder.itemView.textView40.setText("نامشخص")
        }


        if (item?.peykInfo != null) {
            holder.itemView.textView119.setText(item.peykInfo)
        } else {
            holder.itemView.textView119.setText("نامشخص")
        }




        if (item?.fullLocation != null) {
            holder.itemView.rad.setText(item.fullLocation?.trim().toString())
        } else {
            holder.itemView.rad.setText("نامشخص")
        }




        holder.itemView.imageView32.setOnClickListener {
            Toast.makeText(c, "برای حذف آدرس بر روی دکمه نگه دارید", Toast.LENGTH_SHORT).show()
        }



        holder.itemView.imageView32.setOnLongClickListener {
            if (Selected==MultyActivity_2.Pos)
            {
                MultyActivity_2.Pos=-8
                Selected=-1
//                notifyDataSetChanged()
            }
            DA?.Del(item?.id.toString(), position)
            return@setOnLongClickListener true
        }





        holder.itemView.setOnLongClickListener {
            DA?.Edit(item!!, position)
            return@setOnLongClickListener true
        }


    }

    override fun getItemCount(): Int {
        if (list != null) {
            return list?.size!!
        }
        return 0
    }


    public interface data_Type {
        public fun Del(S: String, Pos: Int)
        public fun Edit(S: ModelAddress, Pos: Int)
    }
}