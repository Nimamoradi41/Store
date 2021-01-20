package com.example.store.Main_Fragments
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.store.*
import com.example.store.Adapters.ExpandableListAdapter
import com.example.store.Adapters.adapter_cate_3
import com.example.store.Dialog.Dial_App
import com.example.store.Main_Fragments.CateFrag.Companion.manger
import com.example.store.Models.*
import com.example.store.VIEWMODEL.MainActivityViewModel
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.custome_filter.*
import kotlinx.android.synthetic.main.custome_filter.view.*
import kotlinx.android.synthetic.main.custome_modal.view.*
import kotlinx.android.synthetic.main.custome_modal.view.close
import kotlinx.android.synthetic.main.fragment_frag__a_l_l.view.*
import kotlinx.android.synthetic.main.fragment_frag__under__cate.view.*
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import javax.security.auth.callback.Callback
class Frag_Under_Cate : BaseFragment() {
   var ad_cate_3:adapter_cate_3 ?= null
   var ad_mains:adapter_Spacial_3 ?= null
    var listAdapter: ExpandableListAdapter? = null
    var listDataHeader: ArrayList<String>? = null
    var D_Cate: ArrayList<String>? = null
    var D_Brand: ArrayList<String>? = null
    var listDataChild: HashMap<String, ArrayList<DataFilter>>? = null
    var  products:ArrayList<products> ?=null
    var Model: GetProductModel?=null
    var s=""
    var modelmain: MainActivityViewModel?=null
    var Id=""
    var page=1
    var Flag=false
    var ANTI=true
  var  subCategory: ArrayList<subCategory> ? =null
  var  data_search: Data_4? =null
    companion object{
        var Order=0;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Model=GetProductModel()
        s= arguments?.getString("Type").toString()
        D_Brand=java.util.ArrayList<String>()
        D_Cate=java.util.ArrayList<String>()
        if (s.equals("Cate"))
        {
            Id=arguments?.getString("Id").toString()
            subCategory= arguments?.getSerializable("subcate") as ArrayList<subCategory>?
        }


        modelmain = ViewModelProviders.of(activity!!)[MainActivityViewModel::class.java]


        modelmain?.orderItems?.observe(activity!!,object :Observer<java.util.ArrayList<orderItems>>{
            override fun onChanged(t: java.util.ArrayList<orderItems>?) {
                if (t?.size==0)
                {
                    ad_mains?.list?.forEachIndexed { index, products ->
                        var c=products
                        c.currentReserved=0
                        ad_mains?.list?.set(index,c)
                        ad_mains?.notifyItemChanged(index)
                    }
                }else{




                    ad_mains?.list?.forEachIndexed outer@{ index2 ,  products1 ->
                        Log.i("bvbvbd", "Loop1")
                        var Finded=false
                        t?.forEachIndexed inner@{index1 , orderItems  ->
                            Log.i("bvbvbd", "Loop2")
                            if (products1.id==orderItems.productId)
                            {
                                Finded=true
                                Log.i("bvdvxstfcxasc","BB")
                                Log.i("bvbvbd", "==")
                                var vv = products1
                                Log.i("rrtewv","A")
                                Log.i("bbshcjsk", "NIMA")
                                Log.i("hdgwtwt", products1.title)
                                Log.i("hdgwtwt", index2.toString())
                                Log.i("hdgwtwt", orderItems.count!!.toString())
                                Log.i("ffggfhdw", "A")
                                vv.currentReserved = orderItems.count!!
                                ad_mains?.list?.set(index2, vv)
                                ad_mains?.notifyItemChanged(index2)
                                 return@outer
                            }
//                            else{
//                                Log.i("bvdvxstfcxasc","AA")
//                                Log.i("rrtewv","b")
//                                Log.i("bvbvbd", "!=")
//                                Log.i("hdgwtwt_2", products1.title)
//                                Log.i("hdgwtwt_2", index2.toString())
//                                Log.i("hdgwtwt_2", orderItems.count!!.toString())
//                                var vv = products1
//                                Log.i("bvhyeft", products1.title)
//                                vv.currentReserved = 0
//                                ad_mains?.list?.set(index2,vv)
//                                ad_mains?.notifyItemChanged(index2)
////                                if (products1.currentReserved!=0)
////                                {
////                                    Log.i("dvmsdmvsld", "A")
////                                    Log.i("ffggfhdw", products1.title)
////                                    var vv = products1
////                                    vv.currentReserved =0
////                                    ad_mains?.list?.set(index2, vv)
////                                    ad_mains?.notifyItemChanged(index2)
////                                }
//                            }
                        }

                        if (!Finded)
                        {
                            var vv = products1
                            Log.i("bvhyeft", products1.title)
                            vv.currentReserved = 0
                            ad_mains?.list?.set(index2,vv)
                            ad_mains?.notifyItemChanged(index2)
                        }
                    }
                }










//                t?.forEachIndexed { index1, orderItems ->
//                    Log.i("dsvnsb","Loop1")
//                    ad_mains?.list?.forEachIndexed { index2, products ->
//                        if (orderItems.productId==products.id)
//                        {
//                            Log.i("dsvnsb",orderItems.count.toString())
//                            Log.i("dsvnsb","Index+"+index2.toString())
//
//                            var vc = ad_mains?.list?.get(index2)
//                            vc?.currentReserved= orderItems.count!!
////                            Log.i("svmsnbsbnhn", vc?.count.toString())
//                            ad_mains?.list?.set(index2, vc!!)
//                            ad_mains?.notifyItemChanged(index2)
//                        }
//                    }
//                }
//                Log.i("vmsdsvsdvn","A")
//                if (t!=null)
//                {
//                    if (t.size==0)
//                    {
//                        ad_mains?.list?.forEachIndexed { index2, products ->
//                                var vc = ad_mains?.list?.get(index2)
//                                vc?.currentReserved= 0
////                            Log.i("svmsnbsbnhn", vc?.count.toString())
//                                ad_mains?.list?.set(index2, vc!!)
//                                ad_mains?.notifyItemChanged(index2)
//                        }
//                    }
//                }else{
//                    ad_mains?.list?.forEachIndexed { index2, products ->
//
//                        var vc = ad_mains?.list?.get(index2)
//                        vc?.currentReserved= 0
////                            Log.i("svmsnbsbnhn", vc?.count.toString())
//                        ad_mains?.list?.set(index2, vc!!)
//                        ad_mains?.notifyItemChanged(index2)
//
//                    }
//                }
            }


        })

        modelmain?.change_Data?.observe(activity!!, object : Observer<data_accses> {
            override fun onChanged(t: data_accses?) {
                Log.i("svmsnbsbnhn", t?.Pos.toString())
                Log.i("svmsnbsbnhn", t?.v?.count.toString())
                var vc = ad_mains?.list?.get(t?.Pos!!)
                var f=t?.v?.currentReserved
                vc?.currentReserved = f!!
                Log.i("svmsnbsbnhn", f.toString())
                ad_mains?.list?.set(t?.Pos!!, vc!!)
                ad_mains?.notifyItemChanged(t?.Pos!!)
            }
        })
        if (s.equals("Search"))
        {
            data_search= arguments?.getSerializable("DATA") as Data_4?
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       var V=inflater.inflate(R.layout.fragment_frag__under__cate, container, false)
        V.ddddd.setOnClickListener {
        }
        V.ddddd.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        products=ArrayList<products>()
        SetCate_2(V,products!!)
        if (!s.equals("Cate"))
        {
            Log.i("dvknsvnsdv","A")
            Log.i("dvknsvnsdv",data_search?.title.toString())
            Log.i("dvknsvnsdv",data_search?.id.toString())
            Log.i("dvknsvnsdv",data_search?.type.toString())
            Log.i("dvknsvnsdv","A")
            Log.i("dvknsvnsdv","A")
            V.recy_cate_3.visibility=View.GONE
            GetFirst_Search(data_search?.id.toString(), data_search?.type!!, data_search?.title.toString(),V,Order)
            V.recy_Cate_2.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView,dx,dy)
                    val linearLayoutManager = recyclerView.getLayoutManager() as LinearLayoutManager
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == ad_mains?.list?.size!! - 1) {
                        if (ad_mains?.moredata!!) {
                            Getdata(Order)
//                            Getdata()
                        }
                    }
                }
            })
        }else{
            Log.i("dvknsvnsdv","B")
            Log.i("dvknsvnsdv", subCategory?.size?.toString()!!)
            Setcate(V, subCategory!!,V)
            var S=ArrayList<String>()
            S.add( subCategory?.get(0)?.id.toString())
            GetFirst_Cate(S,1,V,Order)
            V.recy_Cate_2.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView,dx,dy)
                    val linearLayoutManager = recyclerView.getLayoutManager() as LinearLayoutManager
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == ad_mains?.list?.size!! - 1) {
                      Log.i("dvksvnas","S")
                        if (ad_mains?.moredata!!) {
                            Log.i("dvksvnas","V")
                              Getdata(Order)
//                            Getdata()
                        }
                    }
                }

            })
        }
        V.rootView.anti_1.setOnClickListener {
        }
        listDataHeader=ArrayList<String>()
        listDataChild=HashMap<String, ArrayList<DataFilter>>()
        Filter_2(V)
        V.btn_filter.setOnClickListener {
            if (!isNetConnected())
            {
                var I=2;
                var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object :Dial_App.Interface_new{
                    override fun News() {

                    }
                }, context!!)
                p.show()
                return@setOnClickListener
            }

            if (!ANTI)
            {
                return@setOnClickListener
            }
            if (s.equals("Cate"))
            {
                if (Flag)
                {
                    Log.i("fkmdsbkna","b")
                    V.ddddd.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                    V.ddddd.openDrawer(Gravity.RIGHT)
                }else{
                    Log.i("fkmdsbkna","A")
                    GetFilter(V,Model?.CategoryIds?.get(0).toString())
                }

            }else{
                GetFilter(V,"")
            }



//        V.drawer.openDrawer(Gravity.RIGHT)
//            V.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
//        Filter()
//            Filter()
//            var cc=BTS_Filter()
//            cc.show(childFragmentManager,"B")
        }

        V.btn_sort.setOnClickListener {
            if (!ANTI)
            {
                return@setOnClickListener
            }



            var cc=BTS_Sort(Order)
            cc.Click(object : BTS_Sort.data_BTS{
                override fun Data(i: Int) {
                    if (!isNetConnected())
                    {
                        var I=2;
                        var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object :Dial_App.Interface_new{
                            override fun News() {

                            }
                        }, context!!)
                        p.show()
                       return
                    }






                    Order=i
                    if (s.equals("Cate"))
                    {
                       GetFirst_Cate(Model?.CategoryIds!!,1,V,i)
                    }else{
//                        GetProduct(Order)
                        GetFirst_Search(data_search?.id.toString(), data_search?.type!!, data_search?.title.toString(),V,Order)
                    }
                }
            })
            cc.show(childFragmentManager,"A")
//             Sort()
        }


        return  V
    }

    public fun Filter()
    {
//        var sheet=But
        val d= Dialog(context!!, R.style.CustomDialog)
        d.setCancelable(true)
        var v=LayoutInflater.from(context).inflate(R.layout.custome_filter, null, false)
        d.setContentView(v)
//        listAdapter = ExpandableListAdapter(context, listDataHeader, listDataChild)
//        v.explist.setAdapter(listAdapter);
        d.window?.setLayout(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
        )
        d.window?.setBackgroundDrawable(ColorDrawable((Color.TRANSPARENT)))
//        v.imageView15.setOnClickListener {
//            d.dismiss()
//        }

//        v.explist.setOnClickListener {
//
//        }
//        v.close.setOnClickListener {
//            d.dismiss()
//        }
        d.show()
    }
    public fun Filter_2(vcv:View)
    {
//        var sheet=But
//        var v_2=LayoutInflater.from(context).inflate(R.layout.custome_filter, null, false)

//        vcv.navigation.addHeaderView(v_2)

    }

    public fun Sort()
    {
        val d= Dialog(context!!, R.style.CustomDialog)
        d.setCancelable(true)
        var v=LayoutInflater.from(context).inflate(R.layout.custome_sort, null, false)
        d.setContentView(v)

        d.window?.setLayout(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
        )
        d.window?.setBackgroundDrawable(ColorDrawable((Color.TRANSPARENT)))
        v.imageView15.setOnClickListener {
            d.dismiss()
        }





        v.cccv.setOnClickListener {

        }







        v.close.setOnClickListener {
            d.dismiss()
        }
        d.show()
    }
    private fun Setcate(vf: View,  subCategory: ArrayList<subCategory>,V:View) {
        if (subCategory==null)
        {
            vf.recy_cate_3.visibility=View.GONE
        }else{
            vf.recy_cate_3.visibility=View.VISIBLE
            ad_cate_3= adapter_cate_3(subCategory)
            vf.rootView.recy_cate_3.layoutManager=LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
            vf.rootView.recy_cate_3.adapter=ad_cate_3
            ad_cate_3?.Click(object : adapter_cate_3.Data_2{
                override fun data_a(p: subCategory, Pos: Int) {
                     if (!isNetConnected())
                     {
                         var I=2;
                         var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object :Dial_App.Interface_new{
                             override fun News() {

                             }
                         }, context!!)
                         p.show()
                         return
                     }
                    var v=ArrayList<String>()
                    v.add(p.id)
                    GetProduct(v,1,Pos,V,Order)

                }

            })

        }

    }
    private fun SetCate_2(v: View,list:ArrayList<products>) {
        val dm = DisplayMetrics()
        activity?.getWindowManager()?.getDefaultDisplay()?.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.widthPixels
        v.rootView.recy_Cate_2.layoutManager= GridLayoutManager(context, 2)
        ad_mains= adapter_Spacial_3(activity!!,list,width)
        v.rootView.recy_Cate_2.adapter=ad_mains
        ad_mains?.click(object : Adapter_discounts.Data_dis {
            override fun Data(I: Int, ID: String, Pos: Int) {
                if (!isNetConnected())
                {
                    var I=2;
                    var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object :Dial_App.Interface_new{
                        override fun News() {

                        }
                    }, context!!)
                    p.show()
                    return
                }
                Log.i("kvnsndv", I.toString())

                AddEditDeleteItem_Card(I,ID,object : Resuilt {
                    override fun Data(i: Int, S: String, B: Boolean) {
                        if (B)
                        {
                            var v=ad_mains?.list?.get(Pos)
                            v?.currentReserved=I
                            ad_mains?.list?.set(Pos, v!!)
                            ad_mains?.notifyItemChanged(Pos)
                            MainActivity.mainActivityViewModel?.count?.value=i
                        }
                    }

                })
            }

        })
    }
    public  fun GetFirst_Cate(cateid:ArrayList<String>,Type:Int,V:View,Order:Int)
    {
        page=1;
        var Order_Body=RequestBody.create(MediaType.parse("text//plain"),Order.toString())
        DialLoad()
//        Model?.CategoryId=cateid
//        var D=ArrayList<String>()
//        D.add(cateid)
        Model?.CategoryIds=cateid
        Model?.type=1
        Flag=false

        var req=api?.GetProduct("Bearer " +token,Model,Order_Body)
        req?.enqueue(object : retrofit2.Callback<ResponseGetProduct> {
            override fun onResponse(call: Call<ResponseGetProduct>, response: Response<ResponseGetProduct>) {
                Dial_Close()
                Log.i("vksvnksdv_2", response.code().toString())
                if (response.code() == 500) {
                    var code500: ErrorCode500? = null
                    val gson = Gson()
                    val adapter: TypeAdapter<ErrorCode500> =
                            gson.getAdapter(ErrorCode500::class.java)
                    try {
                        if (response.errorBody() != null) code500 = adapter.fromJson(
                                response.errorBody()!!.string()
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    Toast.makeText(
                            context,
                            "" + code500?.getMessage(),
                            Toast.LENGTH_LONG
                    ).show()
                    return
                }
                if (response.code()==401)
                {
                    Login(securityKey,object :Login{
                        override fun onLoginCompleted(success: Boolean) {
                            if (success)
                            {
                                GetFirst_Cate(cateid,Type,V,Order)
                            }
                        }

                    })
                }
                if (response.isSuccessful)
                {
                    ad_cate_3?.Selected=0
                    ad_cate_3?.notifyDataSetChanged()
                    ad_mains?.list= response.body()?.data?.products!!
                    Log.i("vksvnksdv", response.body()?.data?.products?.size.toString())
                    Log.i("vksvnksdv", response.body()?.data?.moreDate!!.toString())
                    ad_mains?.moredata= response.body()?.data?.moreDate!!
                    ad_mains?.notifyDataSetChanged()
                    if (response.body()?.data?.products!=null)
                    {
                        if(response.body()?.data?.products?.size==0)
                        {
                            V.no_item_Card.visibility=View.VISIBLE
                        }
                    }else{
                        V.no_item_Card.visibility=View.VISIBLE
                    }


                }
            }
            override fun onFailure(call: Call<ResponseGetProduct>, t: Throwable) {
                Dial_Close()
                var I=2;
                var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object :Dial_App.Interface_new{
                    override fun News() {
                        manger?.popBackStack()
                    }
                }, context!!)
                p.show()
            }

        })
    }
    public  fun GetFirst_Cate_2(cateid:ArrayList<String>,Type:Int,V:View,Order:Int)
    {
        page=1;
        var Order_Body=RequestBody.create(MediaType.parse("text//plain"),Order.toString())
        DialLoad()
//        Model?.CategoryId=cateid
//        var D=ArrayList<String>()
//        D.add(cateid)
        Model?.CategoryIds=cateid
        Model?.type=1
        Flag=false

        var req=api?.GetProduct("Bearer " +token,Model,Order_Body)
        req?.enqueue(object : retrofit2.Callback<ResponseGetProduct> {
            override fun onResponse(call: Call<ResponseGetProduct>, response: Response<ResponseGetProduct>) {
                Dial_Close()
                Log.i("vksvnksdv_2", response.code().toString())
                if (response.code() == 500) {
                    var code500: ErrorCode500? = null
                    val gson = Gson()
                    val adapter: TypeAdapter<ErrorCode500> =
                            gson.getAdapter(ErrorCode500::class.java)
                    try {
                        if (response.errorBody() != null) code500 = adapter.fromJson(
                                response.errorBody()!!.string()
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    Toast.makeText(
                            context,
                            "" + code500?.getMessage(),
                            Toast.LENGTH_LONG
                    ).show()
                    return
                }
                if (response.code()==401)
                {
                    Login(securityKey,object :Login{
                        override fun onLoginCompleted(success: Boolean) {
                            if (success)
                            {
                                GetFirst_Cate(cateid,Type,V,Order)
                            }
                        }

                    })
                }
                if (response.isSuccessful)
                {
                    ad_cate_3?.Selected=0
                    ad_cate_3?.notifyDataSetChanged()
                    ad_mains?.list= response.body()?.data?.products!!
                    Log.i("vksvnksdv", response.body()?.data?.products?.size.toString())
                    Log.i("vksvnksdv", response.body()?.data?.moreDate!!.toString())
                    ad_mains?.moredata= response.body()?.data?.moreDate!!
                    ad_mains?.notifyDataSetChanged()
                    if (response.body()?.data?.products!=null)
                    {
                        if(response.body()?.data?.products?.size==0)
                        {
                            V.no_item_Card.visibility=View.VISIBLE
                        }
                    }else{
                        V.no_item_Card.visibility=View.VISIBLE
                    }


                }
            }
            override fun onFailure(call: Call<ResponseGetProduct>, t: Throwable) {
                Dial_Close()
                var I=2;
                var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object :Dial_App.Interface_new{
                    override fun News() {
                        manger?.popBackStack()
                    }
                }, context!!)
                p.show()
            }

        })
    }
    public  fun GetFirst_Search(Id:String,Type:Int,Title:String,V:View,Order: Int)
    {
        var Order_Body=RequestBody.create(MediaType.parse("text//plain"),Order.toString())
        DialLoad()
        if (Type==1)
        {
          Model?.type=1
//            Model?.CategoryId=Id
            var D=ArrayList<String>()
            D.add(Id)
            Model?.CategoryIds=D
            Model?.productTitle=Title
        }


        if (Type==2)
        {
            Model?.type=0
            Model?.productTitle=Title
            var D=ArrayList<String>()
            D.add(Id)
            Model?.CategoryIds=D
//            Model?.brandId=Id
        }

        var req=api?.GetProduct("Bearer " +token,Model,Order_Body)
        req?.enqueue(object : retrofit2.Callback<ResponseGetProduct> {
            override fun onResponse(call: Call<ResponseGetProduct>, response: Response<ResponseGetProduct>) {
                Dial_Close()
                Log.i("vksvnksdv", response.code().toString())
                if (response.code() == 500) {
                    var code500: ErrorCode500? = null
                    val gson = Gson()
                    val adapter: TypeAdapter<ErrorCode500> =
                            gson.getAdapter(ErrorCode500::class.java)
                    try {
                        if (response.errorBody() != null) code500 = adapter.fromJson(
                                response.errorBody()!!.string()
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    Toast.makeText(
                            context,
                            "" + code500?.getMessage(),
                            Toast.LENGTH_LONG
                    ).show()
                    return
                }
                if (response.code()==401)
                {
                    Login(securityKey,object :Login{
                        override fun onLoginCompleted(success: Boolean) {
                            if (success)
                            {
                                GetFirst_Search(Id,Type,Title,V,Order)
                            }
                        }

                    })
                }
                if (response.isSuccessful)
                {
                    ad_cate_3?.Selected=0
                    ad_cate_3?.notifyDataSetChanged()
                    ad_mains?.list= response.body()?.data?.products!!
                    ad_mains?.notifyDataSetChanged()
                    Log.i("vnsfbvkandg",response.body()?.data?.products?.size.toString()!!)
                    ANTI=true
                    if (response.body()?.data?.products!=null)
                    {
                        if(response.body()?.data?.products?.size==0)
                        {
                            ANTI=false
                            V.no_item_Card.visibility=View.VISIBLE
                        }
                    }else{
                        ANTI=false
                        V.no_item_Card.visibility=View.VISIBLE
                    }

                }
            }
            override fun onFailure(call: Call<ResponseGetProduct>, t: Throwable) {
                Dial_Close()
                var I=2;
                var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object :Dial_App.Interface_new{
                    override fun News() {
                        manger?.popBackStack()
                    }
                }, context!!)
                p.show()
            }

        })
    }
    public  fun GetFirst_Search_2(Id:String,Type:Int,Title:String,V:View,Order: Int)
    {
        var Order_Body=RequestBody.create(MediaType.parse("text//plain"),Order.toString())
        DialLoad()
        if (Type==1)
        {
            Model?.type=1
//            Model?.CategoryId=Id
            var D=ArrayList<String>()
            D.add(Id)
            Model?.CategoryIds=D
            Model?.productTitle=Title
        }


        if (Type==2)
        {
            Model?.type=0
            Model?.productTitle=Title
            var D=ArrayList<String>()
            D.add(Id)
            Model?.CategoryIds=D
//            Model?.brandId=Id
        }

        var req=api?.GetProduct("Bearer " +token,Model,Order_Body)
        req?.enqueue(object : retrofit2.Callback<ResponseGetProduct> {
            override fun onResponse(call: Call<ResponseGetProduct>, response: Response<ResponseGetProduct>) {
                Dial_Close()
                Log.i("vksvnksdv", response.code().toString())
                if (response.code() == 500) {
                    var code500: ErrorCode500? = null
                    val gson = Gson()
                    val adapter: TypeAdapter<ErrorCode500> =
                            gson.getAdapter(ErrorCode500::class.java)
                    try {
                        if (response.errorBody() != null) code500 = adapter.fromJson(
                                response.errorBody()!!.string()
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    Toast.makeText(
                            context,
                            "" + code500?.getMessage(),
                            Toast.LENGTH_LONG
                    ).show()
                    return
                }
                if (response.code()==401)
                {
                    Login(securityKey,object :Login{
                        override fun onLoginCompleted(success: Boolean) {
                            if (success)
                            {
                                GetFirst_Search(Id,Type,Title,V,Order)
                            }
                        }

                    })
                }
                if (response.isSuccessful)
                {
                    ad_cate_3?.Selected=0
                    ad_cate_3?.notifyDataSetChanged()
                    ad_mains?.list= response.body()?.data?.products!!
                    ad_mains?.notifyDataSetChanged()
                    Log.i("vnsfbvkandg",response.body()?.data?.products?.size.toString()!!)
                    ANTI=true
                    if (response.body()?.data?.products!=null)
                    {
                        if(response.body()?.data?.products?.size==0)
                        {
                            ANTI=false
                            V.no_item_Card.visibility=View.VISIBLE
                        }
                    }else{
                        ANTI=false
                        V.no_item_Card.visibility=View.VISIBLE
                    }

                }
            }
            override fun onFailure(call: Call<ResponseGetProduct>, t: Throwable) {
                Dial_Close()
                var I=2;
                var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object :Dial_App.Interface_new{
                    override fun News() {
                        manger?.popBackStack()
                    }
                }, context!!)
                p.show()
            }

        })
    }
    public  fun GetProduct(cateid:ArrayList<String>,Type:Int,Pos:Int,V:View,Order:Int)
    {
        DialLoad()
        var Order_Body=RequestBody.create(MediaType.parse("text//plain"),Order.toString())
        V.no_item_Card.visibility=View.GONE
//        Model?.CategoryId=cateid
//        var D=ArrayList<String>()
//        D.add(cateid)
        if (s.equals("Cate"))
        {
            D_Brand=ArrayList<String>()
            D_Cate=ArrayList<String>()
        }





        Model?.CategoryIds=cateid
        Model?.type=1
        Flag=false;
        Model?.brandIds=D_Brand
        var req=api?.GetProduct("Bearer " +token,Model,Order_Body)
        req?.enqueue(object : retrofit2.Callback<ResponseGetProduct> {
            override fun onResponse(call: Call<ResponseGetProduct>, response: Response<ResponseGetProduct>) {
                Dial_Close()
                Log.i("vksvnksdv", response.code().toString())
                if (response.code() == 500) {
                    var code500: ErrorCode500? = null
                    val gson = Gson()
                    val adapter: TypeAdapter<ErrorCode500> =
                            gson.getAdapter(ErrorCode500::class.java)
                    try {
                        if (response.errorBody() != null) code500 = adapter.fromJson(
                                response.errorBody()!!.string()
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    Toast.makeText(
                            context,
                            "" + code500?.getMessage(),
                            Toast.LENGTH_LONG
                    ).show()
                    return
                }
                if (response.code()==401)
                {
                    Login(securityKey,object :Login{
                        override fun onLoginCompleted(success: Boolean) {
                            if (success)
                            {
                                GetProduct(cateid,Type,Pos,V,Order)
                            }
                        }

                    })
                }
                if (response.isSuccessful)
                {
                    V.ddddd.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    page=1
                    Log.i("vksvnksdv", response.body()?.data?.products?.size.toString())
                    ad_cate_3?.Selected=Pos
                    ad_cate_3?.notifyDataSetChanged()
                    ad_mains?.list?.clear()
                    ad_mains?.list= response.body()?.data?.products!!
                    ad_mains?.moredata= response.body()?.data?.moreDate!!
                    Log.i("vksvnksdv", response.body()?.data?.moreDate!!.toString())
                    ad_mains?.notifyDataSetChanged()
                    ANTI=true
                    if (response.body()?.data?.products!=null)
                    {
                        if(response.body()?.data?.products?.size==0)
                        {
                            V.no_item_Card.visibility=View.VISIBLE
                            ANTI=false
                        }else{
                            ANTI=true
                        }
                    }else{
                        ANTI=false;
                        V.no_item_Card.visibility=View.VISIBLE

                    }
                }
            }
            override fun onFailure(call: Call<ResponseGetProduct>, t: Throwable) {
                Dial_Close()
                var I=2;
                var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object :Dial_App.Interface_new{
                    override fun News() {

                    }
                }, context!!)
                p.show()
            }

        })
    }
    public fun Getdata(Order: Int)
    {
        var Order_Body=RequestBody.create(MediaType.parse("text//plain"),Order.toString())
        DialLoad()
        page=page+1
        var body_page= RequestBody.create(MediaType.parse("text/plain"),page.toString())
        var req=api?.GetMoreData("Bearer " +token,Model,body_page,Order_Body)
        req?.enqueue(object  : retrofit2.Callback<ResponseMoreData> {
            override fun onResponse(call: Call<ResponseMoreData>, response: Response<ResponseMoreData>) {
                Dial_Close()
                Log.i("dvmksvakvd", response.code().toString())
                if (response.code() == 500)
                {
                    var code500: ErrorCode500? = null
                    val gson = Gson()
                    val adapter: TypeAdapter<ErrorCode500> =
                        gson.getAdapter(ErrorCode500::class.java)
                    try {
                        if (response.errorBody() != null) code500 = adapter.fromJson(
                            response.errorBody()!!.string()
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    Toast.makeText(
                        activity,
                        "" + code500?.getMessage(),
                        Toast.LENGTH_LONG
                    ).show()
                    return
                }
                if (response.code()==401)
                {
                    Login(securityKey,object :Login{
                        override fun onLoginCompleted(success: Boolean) {
                            if (success)
                            {
                                Getdata(Order)
                            }
                        }

                    })
                }

                if (response.isSuccessful)
                {
                    ad_mains?.moredata= response.body()?.data?.moreDate!!
                    ad_mains?.list?.addAll(response.body()?.data?.products!!)
                    ad_mains?.notifyItemRangeChanged(0, ad_mains?.list?.size!!)
                }
            }
            override fun onFailure(call: Call<ResponseMoreData>, t: Throwable) {
                Dial_Close()
                page=page-1
                var I=2;
                var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object :Dial_App.Interface_new{
                    override fun News() {

                    }
                }, context!!)
                p.show()


            }

        })
    }



    fun GetFilter(V:View,S:String)
    {
        DialLoad()
      var req=api?.GetFilter("Bearer " +token,Model)
        req?.enqueue(object : retrofit2.Callback<ResponseSEARCH> {
            override fun onResponse(call: Call<ResponseSEARCH>, response: Response<ResponseSEARCH>) {
                Dial_Close()
                Log.i("sfvmsdvnsb", response.code().toString())
                if (response.code()==401)
                {
                    Login(securityKey,object :Login{
                        override fun onLoginCompleted(success: Boolean) {
                            if (success)
                            {
                                Getdata(Order)
                            }
                        }

                    })
                }

                if (response.code() == 500)
                {
                    var code500: ErrorCode500? = null
                    val gson = Gson()
                    val adapter: TypeAdapter<ErrorCode500> =
                            gson.getAdapter(ErrorCode500::class.java)
                    try {
                        if (response.errorBody() != null) code500 = adapter.fromJson(
                                response.errorBody()!!.string()
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    Toast.makeText(
                            activity,
                            "" + code500?.getMessage(),
                            Toast.LENGTH_LONG
                    ).show()
                    return
                }



                if (response.isSuccessful)
                {
                    Flag=true;
                 var Cate=   ArrayList<DataFilter> ()
                 var Brand=   ArrayList<DataFilter> ()
                    listDataHeader=ArrayList<String>()
                    listDataHeader?.add("دسته بندی ")
                    listDataHeader?.add("برند")
                    Log.i("dvmlsb", response.body()?.data?.size.toString())
                    response.body()?.data?.forEach {
                       if (it.type==1)
                       {
                           var d=DataFilter()
                           d.id=it.id
                           d.title=it.title
                           Cate.add(d)

                       }

                       if (it.type==2)
                       {
                           var d=DataFilter()
                           d.id=it.id
                           d.title=it.title
                           Brand.add(d)
                       }
                    }
                    listDataChild?.put(listDataHeader?.get(0).toString(), Cate)
                    listDataChild?.put(listDataHeader?.get(1).toString(), Brand)
                    listAdapter = ExpandableListAdapter(context, listDataHeader, listDataChild,S)
                    V.findViewById<ExpandableListView>(R.id.explist).setAdapter(listAdapter)
                    listAdapter?.notifyDataSetChanged()
                    listAdapter?.Click(object : ExpandableListAdapter.Data {
                        override fun Daaat(Cate: java.util.ArrayList<String>?, Brand: java.util.ArrayList<String>?) {
                            Log.i("sdvmksbkmsmb+ Brand",Brand?.size.toString())
                            Log.i("sdvmksbkmsmb+ Cate",Cate?.size.toString())
                             Brand?.forEach {
                                 Log.i("sdvmksbkmsmb+ Brand",it.toString())
                             }

                            Cate?.forEach {
                                Log.i("sdvmksbkmsmb+ Cate",it.toString())
                            }
                              D_Brand=Brand
                              D_Cate=Cate
                        }

                    })
                    V.ddddd.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                    V.ddddd.openDrawer(Gravity.RIGHT)



                    V.serch_filter.setOnClickListener {
                        GetItemFilter(V,Order)
                    }



                }

            }

            override fun onFailure(call: Call<ResponseSEARCH>, t: Throwable) {
                Dial_Close()
                Flag=false
                Log.i("sfvmsdvnsb", t.message.toString())
                var I=2;
                var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object :Dial_App.Interface_new{
                    override fun News() {

                    }
                }, context!!)
                p.show()
            }
        })
    }



    public  fun GetItemFilter(V:View,Order: Int)
    {

        DialLoad()



        var Order_Body=RequestBody.create(MediaType.parse("text//plain"),Order.toString())


            Model?.CategoryIds=D_Cate
            Model?.brandIds=D_Brand
//            Model?.brandId=Id
        var req=api?.GetProduct("Bearer " +token,Model,Order_Body)
        req?.enqueue(object : retrofit2.Callback<ResponseGetProduct> {
            override fun onResponse(call: Call<ResponseGetProduct>, response: Response<ResponseGetProduct>) {
                Dial_Close()
                Log.i("vksvnksdv", response.code().toString())
                if (response.code() == 500) {
                    var code500: ErrorCode500? = null
                    val gson = Gson()
                    val adapter: TypeAdapter<ErrorCode500> =
                            gson.getAdapter(ErrorCode500::class.java)
                    try {
                        if (response.errorBody() != null) code500 = adapter.fromJson(
                                response.errorBody()!!.string()
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    Toast.makeText(
                            context,
                            "" + code500?.getMessage(),
                            Toast.LENGTH_LONG
                    ).show()
                    return
                }
                if (response.code()==401)
                {
                    Login(securityKey,object :Login{
                        override fun onLoginCompleted(success: Boolean) {
                            if (success)
                            {
                                GetItemFilter(V,Order)
                            }
                        }

                    })
                }
                if (response.isSuccessful)
                {
                    page=1
                    ad_mains?.list?.clear()
                    ad_mains?.moredata= response.body()?.data?.moreDate!!
                    ad_mains?.list= response.body()?.data?.products!!
                    ad_mains?.notifyDataSetChanged()
                    if (response.body()?.data?.products!=null)
                    {
                        if(response.body()?.data?.products?.size==0)
                        {
                            V.no_item_Card.visibility=View.VISIBLE
                        }
                    }else{
                        V.no_item_Card.visibility=View.VISIBLE
                    }

                }
            }
            override fun onFailure(call: Call<ResponseGetProduct>, t: Throwable) {
                Dial_Close()
                var I=2;
                var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object :Dial_App.Interface_new{
                    override fun News() {

                    }
                }, context!!)
                p.show()
            }

        })
    }

}