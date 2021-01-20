package com.example.store.Main_Fragments

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.store.*
import com.example.store.Adapters.adapter_Category
import com.example.store.Adapters.adapter_main_cate
import com.example.store.Adapters.adapter_slider
import com.example.store.Dialog.Dial_App
import com.example.store.VIEWMODEL.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_cate.view.*
import kotlinx.android.synthetic.main.fragment_mainfrag.view.*

class Mainfrag : BaseFragment() {
    var  ad_slider : adapter_slider ? =null
    var ad_Cate :adapter_Category ?= null
    var ad_dis : Adapter_discounts?= null
    var ad_Spa_1 : adapter_Spacial?= null
    var ad_productBoxDtos_ : Adapter_productBoxDtos?= null
    var modelmain: MainActivityViewModel ?=null
    var V:View ?=null
    companion object{
        var child:FragmentManager ?=null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment.
         V=inflater.inflate(R.layout.fragment_mainfrag, container, false)
         modelmain = ViewModelProviders.of(activity!!)[MainActivityViewModel::class.java]
        child=childFragmentManager
         modelmain?.data?.observe(activity!!, object : Observer<data_2> {
             override fun onChanged(t: data_2?) {
                 SetSlider(t?.sliders!!)
                 SetCate(t.categories!!, V!!)
                 Setdiscounts(t.discounts)
                 SetSpa(t.specials!!)
                 SetproductBoxDtos(t.productBoxDtos)
             }
         })
         SetMore_Discount()
        SetMore_more()
        return V
    }


    fun   SetMore_Discount()
    {
     V?.discounts_more?.setOnClickListener {
         var I=Intent(activity, MultyActivity_2::class.java)
         I.putExtra("Type", "P")
         I.putExtra("data", ad_dis?.list)
         activity?.startActivityForResult(I, 22)
     }
    }

    fun   SetMore_more()
    {
        V?.spa_more?.setOnClickListener {
            var I=Intent(activity, MultyActivity_2::class.java)
            I.putExtra("Type", "G")
            I.putExtra("data", ad_Spa_1?.list?.products)
            I.putExtra("t", 3)
            activity?.startActivityForResult(I, 25)
        }
    }




    private fun SetSpa(data: specials) {
//        var va=ArrayList<model_Item>()
//        var  nm=model_Item()
//        nm.name="همبرگر مممتاز 250 گرمی شرکت 202"
//        nm.img=R.drawable.item_1
//        va.add(nm)
//        var  nm_2=model_Item()
//        nm_2.name="روغن افتاب بدون کلسترول با ویتامین "
//        nm_2.img=R.drawable.item_2
//        va.add(nm_2)
//        var  nm_3=model_Item()
//        nm_3.name="برنج چارلی با کیفیت بالا و بهترین مزه "
//        nm_3.img=R.drawable.item_3
//        va.add(nm_3)
//        var  nm_4=model_Item()
//        nm_4.name="قارچ بسته بندی آماده وکیوم شده تازه  500 گرمی"
//        nm_4.img=R.drawable.item_4
//        va.add(nm_4)
//        var  nm_5=model_Item()
//        nm_5.name="چای شهرزاد خوش بو 500 گرمی"
//        nm_5.img=R.drawable.item_5
//        va.add(nm_5)
//        var  nm_6=model_Item()
//        nm_6.name="چای شهرزاد خوش بو با طعم لیو 500 گرمی"
//        nm_6.img=R.drawable.item_6
//        va.add(nm_6)
//        var dis= DisplayMetrics()
//            activity?.windowManager?.defaultDisplay?.getMetrics(dis)
//        var W=dis.widthPixels


        if (data.products!=null)
        {
            val dm = DisplayMetrics()
            activity?.getWindowManager()?.getDefaultDisplay()?.getMetrics(dm)
            val width = dm.widthPixels
            val height = dm.widthPixels
            V?.rootView?.titil_spl?.setText(data.category.title)
            V?.rootView?.titil_spl?.visibility=View.VISIBLE
            V?.rootView?.recy_special_2?.visibility=View.VISIBLE
            ad_Spa_1= adapter_Spacial(activity!!, data,width,height)
            V?.rootView?.recy_special_2?.layoutManager=LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                true
            )
            V?.rootView?.recy_special_2?.adapter=ad_Spa_1
            ad_Spa_1?.click(object : Adapter_discounts.Data_dis {
                override fun Data(I: Int, ID: String, Pos: Int) {
                    if (!isNetConnected()) {
                        var I = 2;
                        var p = Dialapp(
                            2,
                            "اتصال خود را به اینترنت بررسی کنید",
                            object : Dial_App.Interface_new {
                                override fun News() {

                                }
                            },
                            context!!
                        )
                        p.show()
                        return
                    }
                    Log.i("kvnsndv", I.toString())
                    AddEditDeleteItem_Card(I, ID, object : Resuilt {
                        override fun Data(i: Int, S: String, B: Boolean) {
                            if (B) {
                                var v = ad_Spa_1?.list?.products?.get(Pos)
                                v?.currentReserved = I
                                ad_Spa_1?.list?.products?.set(Pos, v)
                                ad_Spa_1?.notifyItemChanged(Pos)
                                MainActivity.mainActivityViewModel?.count?.value = i
                            }
                        }

                    })
                }

            })
        }else{
            V?.rootView?.titil_spl?.visibility=View.GONE
            V?.rootView?.recy_special_2?.visibility=View.GONE
        }


    }

    private fun SetSlider(c: ArrayList<sliders>) {

        if (c!=null)
        {
            V?.rootView?.view_Slider?.visibility=View.VISIBLE
            ad_slider= adapter_slider(childFragmentManager, c)
            V?.rootView?.view_Slider?.adapter=ad_slider
        }else{
            V?.rootView?.view_Slider?.visibility=View.GONE
        }

    }
    private fun Setdiscounts(c: discounts) {

        val dm = DisplayMetrics()
        activity?.getWindowManager()?.getDefaultDisplay()?.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.widthPixels
        if (c.products!=null)
        {
            V?.rootView?.recy_discounts?.visibility=View.VISIBLE
            V?.rootView?.titile_discounts?.visibility=View.VISIBLE
            V?.rootView?.discounts_more?.visibility=View.VISIBLE
            V?.rootView?.titile_discounts?.setText(c.categories.title)
            ad_dis= Adapter_discounts(activity!!, c,width,height)
            V?.rootView?.recy_discounts?.layoutManager=LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                true
            )
            V?.rootView?.recy_discounts?.adapter=ad_dis
            ad_dis?.click(object : Adapter_discounts.Data_dis {
                override fun Data(I: Int, ID: String, Pos: Int) {
                    if (!isNetConnected()) {
                        var I = 2;
                        var p = Dialapp(
                            2,
                            "اتصال خود را به اینترنت بررسی کنید",
                            object : Dial_App.Interface_new {
                                override fun News() {

                                }
                            },
                            context!!
                        )
                        p.show()
                        return
                    }
                    Log.i("kvnsndv", I.toString())
                    AddEditDeleteItem_Card(I, ID, object : Resuilt {
                        override fun Data(i: Int, S: String, B: Boolean) {
                            if (B) {
                                var v = ad_dis?.list?.products?.get(Pos)
                                v?.currentReserved = I
                                ad_dis?.list?.products?.set(Pos, v)
                                ad_dis?.notifyItemChanged(Pos)
                                MainActivity.mainActivityViewModel?.count?.value = i
                            }
                        }

                    })
                }

            })

        }else{
            V?.rootView?.recy_discounts?.visibility=View.GONE
            V?.rootView?.discounts_more?.visibility=View.GONE
            V?.rootView?.titile_discounts?.visibility=View.GONE
        }

    }
    private fun SetproductBoxDtos(c: ArrayList<productBoxDtos>) {


        val dm = DisplayMetrics()
        activity?.getWindowManager()?.getDefaultDisplay()?.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.widthPixels
        if (c.size!=null)
        {
            V?.rootView?.recy_productBoxDtos?.visibility=View.VISIBLE
            ad_productBoxDtos_= Adapter_productBoxDtos(activity!!, c,width)
            V?.rootView?.recy_productBoxDtos?.layoutManager=LinearLayoutManager(context)
            V?.rootView?.recy_productBoxDtos?.adapter=ad_productBoxDtos_
            ad_productBoxDtos_?.click_2(object : Adapter_productBoxDtos_2.Data_BTO_2 {
                override fun Data(I: Int, ID: String, Pos: Int, Ad: Adapter_productBoxDtos_2) {
                    if (!isNetConnected()) {
                        var I = 2;
                        var p = Dialapp(
                            2,
                            "اتصال خود را به اینترنت بررسی کنید",
                            object : Dial_App.Interface_new {
                                override fun News() {

                                }
                            },
                            context!!
                        )
                        p.show()
                        return
                    }
                    AddEditDeleteItem_Card(I, ID, object : Resuilt {
                        override fun Data(i: Int, S: String, B: Boolean) {
                            if (B) {
                                var v = Ad.list.products?.get(Pos)
                                v?.currentReserved = I
                                Ad.list.products?.set(Pos, v)
                                Ad.notifyItemChanged(Pos)
                                MainActivity.mainActivityViewModel?.count?.value = i
                            }
                        }

                    })
                }

            })

        }else{
            V?.rootView?.recy_productBoxDtos?.visibility=View.GONE
        }

    }
    private fun SetCate(f: ArrayList<categories>, V: View) {
        if (f!=null)
        {
            V?.rootView?.recy_category?.visibility=View.VISIBLE
            ad_Cate= adapter_Category(context!!, f)
            ad_Cate?.D_1(object : adapter_main_cate.Data {
                override fun Clicl(CategoryId: String, subCategory: ArrayList<subCategory>) {
                    if (!isNetConnected()) {
                        var I = 2;
                        var p = Dialapp(
                            2,
                            "اتصال خود را به اینترنت بررسی کنید",
                            object : Dial_App.Interface_new {
                                override fun News() {
                                }
                            },
                            context!!
                        )
                        p.show()
                        return
                    }
                    V.Cont_3.visibility = View.VISIBLE
                    var f = Frag_Under_Cate()
                    var b = Bundle()
                    b.putString("Type", "Cate")
                    b.putString("Id", CategoryId)
                    b.putSerializable("subcate", subCategory)
                    f.arguments = b
                    child?.beginTransaction()?.add(R.id.Cont_3, f)?.addToBackStack(null)?.commit()

//                 MainActivity.Count=1;
//                 MainActivity.transaction?.beginTransaction()?.add(R.id.Cont,f)?.addToBackStack(null)?.commit()
                }

            })
            V?.rootView?.recy_category?.layoutManager=LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                true
            )
            V?.rootView?.recy_category?.adapter=ad_Cate
        }else{
            V?.rootView?.recy_category?.visibility=View.GONE
        }

    }




}