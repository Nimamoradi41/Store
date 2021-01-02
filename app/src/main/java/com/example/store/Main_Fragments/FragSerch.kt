package com.example.store.Main_Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.store.Adapters.adapter_serachbar
import com.example.store.Dialog.Dial_App
import com.example.store.Models.Data_4
import com.example.store.Models.ResponseSEARCH
import com.example.store.R
import com.example.store.adapter_Spacial
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import kotlinx.android.synthetic.main.fragment_frag_serch.*
import kotlinx.android.synthetic.main.fragment_frag_serch.view.*
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class FragSerch : BaseFragment() {
    var L:ArrayList<String> ?=null
    companion object{
        var ma:FragmentManager?=null
    }
    var ad_adapter_searchbar:adapter_serachbar ?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      var V=inflater.inflate(R.layout.fragment_frag_serch, container, false)
        L=ArrayList<String> ()
        ma=childFragmentManager
        V.imageView12.setOnClickListener {
            editTextTextPersonName.text.clear()
            imageView12.animate().alpha(0f).setDuration(700).start()
            ad_adapter_searchbar?.list?.clear()
            ad_adapter_searchbar?.notifyDataSetChanged()
        }
        ad_adapter_searchbar=adapter_serachbar()
        V.recy_search.adapter=ad_adapter_searchbar
        ad_adapter_searchbar?.click(object : adapter_serachbar.Data_Ser{
            override fun Data(I: Data_4) {
                var f= Frag_Under_Cate()
              var b= Bundle()
              b.putString("Type","Search")
              b.putSerializable("DATA",I)
              f.arguments=b
                Log.i("dvksnvnka","AAASDD")
                ma?.beginTransaction()?.add(R.id.holder_44,f)?.addToBackStack(null)?.commit()
            }

        })
        V.editTextTextPersonName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().length > 0)
                {
                    if (imageView12.alpha==0f)
                    {
                        imageView12.animate().alpha(1f).setDuration(700).start()
                    }

                    Search(s.toString())

                }else{
                    ad_adapter_searchbar?.list?.clear()
                    ad_adapter_searchbar?.notifyDataSetChanged()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        return  V
    }


    fun  Search(S:String)
    {
        var body=RequestBody.create(MediaType.parse("text/plain"),S)
        var req=api?.Search("Bearer " +token,body)
        req?.enqueue(object  : retrofit2.Callback<ResponseSEARCH> {
            override fun onResponse(call: Call<ResponseSEARCH>, response: Response<ResponseSEARCH>) {
                Log.i("sdvksndjlabs",response.code().toString())
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
                                Search(S)
                            }
                        }

                    })
                }
                if (response.isSuccessful)
                {
                    ad_adapter_searchbar?.list=response.body()?.data
                    ad_adapter_searchbar?.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ResponseSEARCH>, t: Throwable) {
                var I=2;
                var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object : Dial_App.Interface_new{
                    override fun News() {

                    }
                }, context!!)
                p.show()
            }
        })
    }
}