package com.example.store.Adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.store.Adapter_discounts
import com.example.store.MainActivity
import com.example.store.Main_Fragments.Frag_Under_Cate
import com.example.store.Models.Data_4
import com.example.store.R
import kotlinx.android.synthetic.main.custome_item_searchbar.view.*

class adapter_serachbar : RecyclerView.Adapter<adapter_serachbar.view>() {
    var interface_1:Data_Ser?=null
    var list:ArrayList<Data_4>?=null
    init {
        list=ArrayList<Data_4> ()
    }
    public class view(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
         var V=LayoutInflater.from(parent.context).inflate(R.layout.custome_item_searchbar,parent,false)

        return  view(V)
    }

    fun click(da: Data_Ser)
    {
        this.interface_1=da
    }
    override fun onBindViewHolder(holder: view, position: Int) {
        var item=list?.get(position)
        if (item?.type==1)
        {
            holder.itemView.textView30.setText("کالا در دسته بندی")
            holder.itemView.textView23.setText(list?.get(position)?.title)
        }


        if (item?.type==2)
        {
            holder.itemView.textView30.setText(" کالا در برند")
            holder.itemView.textView23.setText(list?.get(position)?.title)
        }



        holder.itemView.setOnClickListener {
            interface_1?.Data(list?.get(position)!!)
//            var f= Frag_Under_Cate()
//            var b= Bundle()
//            b.putString("Type","Search")
//            f.arguments=b
//            MainActivity.Count=1;
//            MainActivity.transaction?.beginTransaction()?.add(R.id.Cont,f)?.addToBackStack(null)?.commit()
        }
    }
    override fun getItemCount(): Int {
        if (list?.size!=null)
        {
            return list?.size!!
        }
        return 0

    }

    public interface  Data_Ser{
        public fun Data(I:Data_4)
    }
}