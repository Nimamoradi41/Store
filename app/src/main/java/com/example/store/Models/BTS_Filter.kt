package com.example.store.Models

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.store.Adapters.ExpandableListAdapter
import com.example.store.Main_Fragments.DataFilter
import com.example.store.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.custome_filter.view.*
import kotlinx.android.synthetic.main.custome_sort.view.*

class BTS_Filter : BottomSheetDialogFragment() {
    var listAdapter: ExpandableListAdapter? = null
    var listDataHeader: ArrayList<String>? = null
    var listDataChild: HashMap<String, ArrayList<DataFilter>>? = null
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = View.inflate(context, R.layout.custome_filter, null)

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        listDataHeader=ArrayList<String>()
//        listDataChild=HashMap<String, ArrayList<DataFilter>>()
//        listAdapter = ExpandableListAdapter(context, listDataHeader, listDataChild)
//        view.explist.setAdapter(listAdapter);
//        prepareListData()

    }
    private fun prepareListData() {
//        listDataHeader = ArrayList<String>()
//        listDataChild = HashMap<String, ArrayList<DataFilter>>()
//
//        // Adding child data
//        (listDataHeader as ArrayList<String>)?.add("Top 250")
//        (listDataHeader as ArrayList<String>)?.add("Now Showing")
//        (listDataHeader as ArrayList<String>)?.add("Coming Soon..")
//
//        // Adding child data
//        val top250: MutableList<String> = ArrayList()
//        top250.add("The Shawshank Redemption")
//        top250.add("The Godfather")
//        top250.add("The Godfather: Part II")
//        top250.add("Pulp Fiction")
//        top250.add("The Good, the Bad and the Ugly")
//        top250.add("The Dark Knight")
//        top250.add("12 Angry Men")
//        val nowShowing: MutableList<String> = ArrayList()
//        nowShowing.add("The Conjuring")
//        nowShowing.add("Despicable Me 2")
//        nowShowing.add("Turbo")
//        nowShowing.add("Grown Ups 2")
//        nowShowing.add("Red 2")
//        nowShowing.add("The Wolverine")
//        val comingSoon: MutableList<String> = ArrayList()
//        comingSoon.add("2 Guns")
//        comingSoon.add("The Smurfs 2")
//        comingSoon.add("The Spectacular Now")
//        comingSoon.add("The Canyons")
//        comingSoon.add("Europa Report")
//        listDataChild?.put(listDataHeader?.get(0).toString(), top250) // Header, Child data
//        listDataChild?.put(listDataHeader?.get(1).toString(), nowShowing)
//        listDataChild?.put(listDataHeader?.get(2).toString(), comingSoon)
//        listAdapter?.notifyDataSetChanged()
    }
}