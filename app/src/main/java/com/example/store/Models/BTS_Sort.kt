package com.example.store.Models

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.store.Main_Fragments.Frag_Under_Cate
import com.example.store.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_tamrin__android_2.view.*
import kotlinx.android.synthetic.main.custome_sort.view.*

class BTS_Sort(var I: Int) : BottomSheetDialogFragment() {

    var d:data_BTS?=null


    fun Click(d:data_BTS)
    {
        this.d=d
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = View.inflate(context, R.layout.custome_sort, null)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.imageView15.setOnClickListener {
            dismiss()
        }

        if (I==0)
        {
            view.rag.check(R.id.Cheapest)
        }

        if (I==1)
        {
            view.rag.check(R.id.Expensive)
        }

        if (I==2)
        {
            view.rag.check(R.id.MostDiscounted)
        }

        if (I==3)
        {
            view.rag.check(R.id.Newest)
        }




        view.button9.setOnClickListener {


            val radioButtonID: Int = view.rag.getCheckedRadioButtonId()
            val radioButton: View = view.rag.findViewById(radioButtonID)
            val idx: Int = view.rag.indexOfChild(radioButton)
            if (Frag_Under_Cate.Order!=idx)
            {
                d?.Data(idx)
                dismiss()
            }else{
                dismiss()
            }


        }

    }


    interface data_BTS{
        fun Data(i:Int)
    }

}