package com.example.store.Main_Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.store.*
import com.example.store.Adapters.adapter_main_cate
import com.example.store.Dialog.Dial_App
import com.example.store.Models.modeli_category
import com.example.store.VIEWMODEL.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_cate.*
import kotlinx.android.synthetic.main.fragment_cate.view.*

class CateFrag : BaseFragment() {
    var ad_cate:adapter_main_cate ? =null
    var modelmain: MainActivityViewModel ?=null
    companion object{
        var manger:FragmentManager ? =null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      manger=childFragmentManager
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
     var V=inflater.inflate(R.layout.fragment_cate, container, false)
        modelmain = ViewModelProviders.of(activity!!)[MainActivityViewModel::class.java]
        modelmain?.data?.observe(activity!!,object : Observer<data_2> {
            override fun onChanged(t: data_2?) {
                SetCate(V, t?.categories!!)
            }

        })

        return V
    }

    private fun SetCate(v_k:View,a:ArrayList<categories>) {
        v_k.rootView.recy_Cate.layoutManager=GridLayoutManager(context,2)
//        var f=ArrayList<modeli_category>()
//        var v= modeli_category()
//        v.name="نوشیدنی"
//        v.img=R.drawable.cate_2
//        f.add(v)
//        var v_2= modeli_category()
//        v_2.name="خواربار"
//        v_2.img=R.drawable.cate_3
//        f.add(v_2)
//        var v_3= modeli_category()
//        v_3.name="لوازم تحریر"
//        v_3.img=R.drawable.cate_5
//        f.add(v_3)
//        var v_4= modeli_category()
//        v_4.name="مواد غذایی"
//        v_4.img=R.drawable.cate_1
//        f.add(v_4)
//        var v_5= modeli_category()
//        v_5.name="میوه و سبزیجات"
//        v_5.img=R.drawable.cate_4
//        f.add(v_5)
//        v_k.Cont_2.setOnClickListener {
//
//            Toast.makeText(context,"A",Toast.LENGTH_SHORT).show()
//        }
         ad_cate=adapter_main_cate(context!!,a)
        ad_cate?.D_1(object :adapter_main_cate.Data{
            override fun Clicl(CategoryId:String,subCategory: ArrayList<subCategory>) {
                if (!isNetConnected()){
                    var I=2;
                    var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object : Dial_App.Interface_new{
                        override fun News() {
                        }
                    }, context!!)
                    p.show()
                    return
                }
                v_k.Cont_2.visibility=View.VISIBLE
                var f=Frag_Under_Cate()
                var b=Bundle()
                b.putString("Type","Cate")
                b.putString("Id",CategoryId)
                b.putSerializable("subcate",subCategory)
                f.arguments=b
                manger?.beginTransaction()?.add(R.id.Cont_2,f)?.addToBackStack(null)?.commit()

//                 MainActivity.Count=1;
//                 MainActivity.transaction?.beginTransaction()?.add(R.id.Cont,f)?.addToBackStack(null)?.commit()
            }

        })
        v_k.recy_Cate.adapter=ad_cate
    }




}