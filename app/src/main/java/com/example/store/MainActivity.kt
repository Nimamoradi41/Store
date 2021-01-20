package com.example.store
import android.app.Activity
import android.app.UiModeManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.example.store.Dialog.Dial_App
import com.example.store.Main_Fragments.*
import com.example.store.Models.data_accses
import com.example.store.Models.orderItems
import com.example.store.VIEWMODEL.Data_card
import com.example.store.VIEWMODEL.MainActivityViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_detail_order.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainActivity : BaseActiity() {
    var F_Cate: CateFrag? = null
    var F_Home: Mainfrag? = null
    var F_Serach: FragSerch? = null
    var F_Order: Frag_list_orders? = null
    var M:Menu ?=null
    var doubleBackToExitPressedOnce=false
    companion object {
        var transaction: FragmentManager? = null
        var Count = 0;
        var mainActivityViewModel :MainActivityViewModel ?=null
    }




    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var d=getSystemService(UI_MODE_SERVICE) as UiModeManager
        d.nightMode=UiModeManager.MODE_NIGHT_NO
        setContentView(R.layout.activity_main)
        transaction = supportFragmentManager
        mainActivityViewModel = ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        var v=intent.getSerializableExtra("DATA") as data_2?
        mainActivityViewModel?.data?.value= v
//        mainActivityViewModel?.count?.value=v?.cartCount
        textView24.setText(mainActivityViewModel?.data?.value?.cartCount.toString())
        mainActivityViewModel?.count?.observe(this, Observer {
            textView24.setText(it.toString())
        })
        F_Home = Mainfrag()
        F_Cate = CateFrag()
        F_Serach = FragSerch()
        F_Order = Frag_list_orders()
        Frag_Home()
        M=bottomNavigationView.menu
        SetNavigation()
        Log.i("kvnsndvsdvnfnv",token)
        linearLayout.setOnClickListener {
            if (!isNetConnected())
            {

                    var I=2;
                    var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object : Dial_App.Interface_new{
                        override fun News() {

                        }
                    }, this)
                    p.show()

                return@setOnClickListener
            }

            startActivityForResult(Intent(this, Activity_card_Bascket::class.java),21)
        }
        linearLayout2.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }


    private fun SetNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                if (item.itemId == R.id.id_cate) {
                    if (Count != 0) {
//                            transaction?.popBackStack()
                        Frag_Cate()
                    } else {
                        Frag_Cate()
                    }
//                    var s = supportFragmentManager.findFragmentById(R.id.Cont)?.javaClass?.simpleName.toString()
//                    if (s.equals("Mainfrag") || s.equals("CateFrag")) {
//                        Log.i("fbllfblbl", "A")
//                        Log.i("fbllfblbl", s)
//                        if (Count != 0) {
////                            transaction?.popBackStack()
//                            Frag_Cate()
//                        } else {
//                            Frag_Cate()
//                        }
//                    } else {
//                        Log.i("fbllfblbl", "B")
//                        Log.i("fbllfblbl", s)
//                    }


                } else if (item.itemId == R.id.id_home) {
                    if (Count != 0) {
//                        transaction?.popBackStack()
                        Frag_Home()
                    } else {
                        Frag_Home()
                    }

                } else if (item.itemId == R.id.id_search) {
                    if (Count != 0) {
                        transaction?.popBackStack()
                        Frag_Serch()
                    } else {
                        Frag_Serch()
                    }

                } else if (item.itemId == R.id.id_card_payment) {
                    if (Count != 0) {
                        transaction?.popBackStack()
                        Frag_Card()
                    } else {
                        Frag_Card()
                    }

                }
                return true
            }

        })
    }



    private fun Frag_Card() {
        transaction = supportFragmentManager
        if (F_Order?.isAdded!!) {
            Log.i("vsknvsd", "A")
            transaction?.beginTransaction()?.show(F_Order!!)?.addToBackStack("Frag_Card")?.commit()
        } else {
            Log.i("vsknvsd", "b")
            transaction?.beginTransaction()?.add(R.id.Cont, F_Order!!)?.addToBackStack("Frag_Card")?.commit()
        }

        if (F_Cate?.isAdded!!) {
            transaction?.beginTransaction()?.hide(F_Cate!!)?.addToBackStack(null)?.commit()
        }
        if (F_Home?.isAdded!!) {
            transaction?.beginTransaction()?.hide(F_Home!!)?.addToBackStack(null)?.commit()
        }
        if (F_Serach?.isAdded!!) {
//            transaction?.beginTransaction()?.hide(F_Serach!!)?.addToBackStack(null)?.commit()
            transaction?.beginTransaction()?.hide(F_Serach!!)?.commit()
        }
    }

    private fun Frag_Serch() {
        transaction = supportFragmentManager
        if (F_Serach?.isAdded!!) {

            Log.i("vsknvsd", "A")
            transaction?.beginTransaction()?.show(F_Serach!!)?.addToBackStack("Frag_Serch")?.commit()
        } else {
            Log.i("vsknvsd", "b")
            transaction?.beginTransaction()?.add(R.id.Cont, F_Serach!!)?.addToBackStack("Frag_Serch")?.commit()
        }


        if (F_Cate?.isAdded!!) {
//            transaction?.beginTransaction()?.hide(F_Cate!!)?.addToBackStack(null)?.commit()
            transaction?.beginTransaction()?.hide(F_Cate!!)?.commit()
        }
        if (F_Home?.isAdded!!) {
            Log.i("kdvkmdvmkdvk_2", F_Home?.isVisible!!.toString())
            transaction?.beginTransaction()?.hide(F_Home!!)?.commit()
//            transaction?.beginTransaction()?.hide(F_Home!!)?.commit()
        }
        if (F_Order?.isAdded!!) {
            transaction?.beginTransaction()?.hide(F_Order!!)?.commit()
        }
    }



    public fun Frag_Home() {
        transaction = supportFragmentManager
//        var v=M?.get(0)
//        Log.i("dvlmdmldmldvl", v?.title.toString())
//        v?.setIcon(R.drawable.ic_baseline_home_24)
//        v?.setEnabled(true)
        bottomNavigationView.getMenu()
                .findItem(R.id.id_home)
                .setEnabled(true)
                .setIcon(R.drawable.ic_baseline_home_24)

        if (F_Home?.isAdded!!) {
            Log.i("ccccccssss", "B")
            transaction?.beginTransaction()?.show(F_Home!!)?.addToBackStack("Frag_Home")?.commit()
//            transaction?.beginTransaction()?.show(F_Home!!)?.addToBackStack(null)?.commit()
        } else {
            Log.i("ccccccssss", "b")
            transaction?.beginTransaction()?.add(R.id.Cont, F_Home!!)?.commit()
        }

        if (F_Cate?.isAdded!!) {
            transaction?.beginTransaction()?.hide(F_Cate!!)?.commit()
        }
        if (F_Serach?.isAdded!!) {
            Log.i("kdvkmdvmkdvk", F_Home?.isVisible!!.toString())
            transaction?.beginTransaction()?.hide(F_Serach!!)?.commit()
        }

        if (F_Order?.isAdded!!) {
            transaction?.beginTransaction()?.hide(F_Order!!)?.commit()
        }


    }

    public fun Frag_Cate() {
        transaction = supportFragmentManager
        if (F_Cate?.isAdded!!) {
            Log.i("ccccccssss", "B")
            transaction?.beginTransaction()?.show(F_Cate!!)?.addToBackStack("Frag_Cate")?.commit()
        } else {
            Log.i("ccccccssss", "A")
            transaction?.beginTransaction()?.add(R.id.Cont, F_Cate!!)?.addToBackStack("Frag_Cate")?.commit()
        }

        if (F_Home?.isAdded!!) {
            transaction?.beginTransaction()?.hide(F_Home!!)?.commit()
//            transaction?.beginTransaction()?.hide(F_Home!!)?.addToBackStack("Frag_Home")?.commit()
        }
        if (F_Serach?.isAdded!!) {
//            transaction?.beginTransaction()?.hide(F_Serach!!)?.addToBackStack("Frag_Serch")?.commit()
            transaction?.beginTransaction()?.hide(F_Serach!!)?.commit()
        }

        if (F_Order?.isAdded!!) {
//            transaction?.beginTransaction()?.hide(F_Order!!)?.addToBackStack(null)?.commit()
            transaction?.beginTransaction()?.hide(F_Order!!)?.commit()
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==20)
        {
           if (resultCode== RESULT_OK)
           {
               GetHome()
               Log.i("dsvsbnsnfbdkkdvk","K")
//               if(data!=null)
//               {
//                   if (data?.getSerializableExtra("data")!=null)
//                   {
//                       Log.i("dsvsbnsnfbdkkdvk","I")
//                       var data=data?.getSerializableExtra("data") as ArrayList<orderItems>
//                       mainActivityViewModel?.orderItems?.value=data
//                   }
//               }
           }
        }

        if (requestCode==22)
        {
            if (resultCode== RESULT_OK)
            {
                GetHome()
                Log.i("dvsdvsdb","A")
                    if (data!=null)
                    {
                        Log.i("dvsdvsdb","B")
                        var ii=data.getIntExtra("count",-1)
                        Log.i("dvsdvsdb", ii.toString())
                        textView24.setText(ii.toString())
                    }
//                Log.i("dsvsbnsnfbdkkdvk","U")
//                if(data!=null)
//                {
//                    if (data?.getSerializableExtra("data")!=null)
//                    {
//                        Log.i("dsvsbnsnfbdkkdvk","T")
//                        var data=data?.getSerializableExtra("data") as ArrayList<orderItems>
//                        mainActivityViewModel?.orderItems?.value=data
//                    }
//                }
            }
        }
        if (requestCode==85)
        {
            if (resultCode== RESULT_OK)
            {
                var s= data_accses()
                Log.i("svsbknsbs", data?.getIntExtra("pos",77).toString())
                s.Pos=data?.getIntExtra("pos",77)
                s.v=data?.getSerializableExtra("data") as products?
                mainActivityViewModel?.change_Data?.value= s
            }
        }

        if (requestCode==21)
        {
            if (resultCode== RESULT_OK)
            {
                GetHome()
                Log.i("dsvsbnsnfbdkkdvk","Q")

                    if (data?.getSerializableExtra("data")!=null)
                    {
                        Log.i("dsvsbnsnfbdkkdvk","G")
                        var data=data?.getSerializableExtra("data") as ArrayList<orderItems>
                        mainActivityViewModel?.orderItems?.value=data
                    }
                 else{
                    Log.i("dsvsbnsnfbdkkdvk","NIMA")
                    var data=  ArrayList<orderItems>()
                    mainActivityViewModel?.orderItems?.value=data
                }

            }
        }


        if (requestCode==23)
        {
            if (resultCode== RESULT_OK)
            {
                GetHome()
                Log.i("dsvsbnsnfbdkkdvk","E")
//                if(data!=null)
//                {
//                    if (data?.getSerializableExtra("data")!=null)
//                    {
//                        Log.i("dsvsbnsnfbdkkdvk","F")
//                        var data=data?.getSerializableExtra("data") as ArrayList<orderItems>
//                        mainActivityViewModel?.orderItems?.value=data
//                    }
//                }
            }
        }



        if (requestCode==25)
        {
            if (resultCode== RESULT_OK)
            {
                GetHome()
//                Log.i("dsvsbnsnfbdkkdvk","D")
//                if (data?.getSerializableExtra("data")!=null)
//                {
//                    Log.i("dsvsbnsnfbdkkdvk","C")
//                    var data=data?.getSerializableExtra("data") as ArrayList<orderItems>
//                    mainActivityViewModel?.orderItems?.value=data
//                }
            }
        }

        if (requestCode==26)
        {
            if (resultCode== RESULT_OK)
            {
                GetHome()
                Log.i("dsvsbnsnfbdkkdvk","B")
//                if (data?.getSerializableExtra("data")!=null)
//                {
//                    Log.i("dsvsbnsnfbdkkdvk","A")
//                    var data=data?.getSerializableExtra("data") as ArrayList<orderItems>
//                    mainActivityViewModel?.orderItems?.value=data
//                }
            }
        }
    }
    override fun onBackPressed() {
        var v_4 = supportFragmentManager.backStackEntryCount.toString()
        Log.i("vmkdmkvd", "COunt  befor $v_4")
        var v = supportFragmentManager.findFragmentById(R.id.Cont)
        val list: List<Fragment> = supportFragmentManager.fragments
        Log.i("vmkdmkvd", v?.javaClass?.simpleName.toString())
        Log.i("vmkdmkvd", list.size.toString())
        var v_2 = supportFragmentManager.backStackEntryCount.toString()
        if (FragSerch.ma!=null)
        {
            if (!FragSerch.ma?.backStackEntryCount.toString().equals("0"))
            {
                FragSerch.ma?.popBackStack()
                return
            }
        }

        if (Mainfrag.child!=null)
        {
            if (!Mainfrag.child?.backStackEntryCount.toString().equals("0"))
            {
                Mainfrag.child?.popBackStack()
                return
            }
        }
        if (supportFragmentManager.backStackEntryCount==0)
        {


            if (doubleBackToExitPressedOnce) {
                finish()
                return
            }

            this.doubleBackToExitPressedOnce = true
            Snackbar.make(root, "برای خروج دوباره دکمه برگشت را بزنید", Snackbar.LENGTH_SHORT).show()
            Handler().postDelayed(Runnable {
                doubleBackToExitPressedOnce = false
            }, 2000)
            return

        }
        var vd=supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1)
         Log.i("vlldlvldvscv", vd.name.toString())
         Log.i("vlldlvldvscv", v_2)

        if (vd.name.toString().equals("Frag_Home"))
        {
            finish()
        }else if (vd.name.toString().equals("Frag_Cate")){
            if (CateFrag.manger?.backStackEntryCount == 0) {
                bottomNavigationView.selectedItemId=R.id.id_home
            } else {
                CateFrag.manger?.popBackStack()
            }
        }else{
            bottomNavigationView.selectedItemId=R.id.id_home
//            Frag_Home()
        }

        var gt = supportFragmentManager.backStackEntryCount.toString()
        var vsd=supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1)
        Log.i("vlldlvldvscv", vsd.name.toString())
        Log.i("vlldlvldvscv", gt)
//        if (!dvfb.name.toString().equals("Frag_Home"))
//        {
//            super.onBackPressed()
//            finish()
//        }
        Log.i("vmkdmkvd", "COunt $v_2")
//        transaction?.popBackStack()

//        if (v is Mainfrag)
//        {
//            Log.i("ddvdlkkdmkv","SS")
//          finish()
////        finish()
//
////        }else if (v is CateFrag) {
////            Log.i("vmkdmkvd", "e")
////            if (CateFrag.manger?.backStackEntryCount == 0) {
////                transaction?.popBackStack()
////            } else {
////                CateFrag.manger?.popBackStack()
////            }
//        }else{
////            Frag_Home()
//        }
//        }else  {
//            transaction?.popBackStack()
//            transaction?.popBackStack()
//            Log.i("vmkdmkvd", "D")
//            transaction?.popBackStack()
//    }

    }
    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }
    fun  GetHome()
    {
        var req=api?.GETHOME("Bearer " +token)
        req?.enqueue(object : Callback<RESPOSNHOME> {
            override fun onResponse(call: Call<RESPOSNHOME>, response: Response<RESPOSNHOME>) {
                Log.i("zcvmzkmvzkmvmzv",response.code().toString())
                if (response.isSuccessful)
                {
                    mainActivityViewModel?.data?.value=response.body()?.data
                    mainActivityViewModel?.count?.value=response.body()?.data?.cartCount
                }
                if (response.code()==401)
                {
                    Login(securityKey,object : Login {
                        override fun onLoginCompleted(success: Boolean) {
                            if (success)
                            {
                                GetHome()
                            }
                        }

                    })
                }
            }
            override fun onFailure(call: Call<RESPOSNHOME>, t: Throwable) {
                var I=3
                var p=   Dialapp(I,"لطفا دوباره تلاش کنید",object :Dial_App.Interface_new{
                    override fun News() {
                        GetHome()
                    }
                },this@MainActivity)
                p.show()

            }

        })
    }



}