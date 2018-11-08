package com.example.dai_01.fleavatest.activity.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dai_01.fleavatest.App
import com.example.dai_01.fleavatest.R
import com.example.dai_01.fleavatest.R.id.rv_main
import com.example.dai_01.fleavatest.activity.main.adapter.RecyclerCardMainAdapter
import com.example.dai_01.fleavatest.activity.main.gravitysnaphelper.GravitySnapHelper
import com.example.dai_01.fleavatest.extension.CustomScrollListner
import com.example.dai_01.fleavatest.extension.debug
import kotlinx.android.synthetic.main.main_layout.*
import java.util.*

class MainFragment: Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    val dummy1 = mapOf("name" to "Susan", "desc" to "erronous beliefs about life, people, about oneself", "title" to "Meditation")
    var listDataDummy: MutableList<Map<String,String>> = arrayListOf()
    private lateinit var mainAdapter: RecyclerCardMainAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.main_layout, container, false)
        (activity as MainActivity).getSupportActionBar()?.setDisplayHomeAsUpEnabled(false)

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        for (i in 1..7){
            listDataDummy.add(dummy1)
        }

        debug("LIST : $listDataDummy")

        initiateRecyclerTimelineView(listDataDummy)
    }

    private fun initiateRecyclerTimelineView(data:MutableList<Map<String,String>>) {
        rv_main.isNestedScrollingEnabled=false
        linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        rv_main.layoutManager=linearLayoutManager
        mainAdapter= RecyclerCardMainAdapter(data)
        rv_main.addOnScrollListener(CustomScrollListner())
        rv_main.adapter=mainAdapter
        val snapHelper= GravitySnapHelper(Gravity.START)
        snapHelper.attachToRecyclerView(rv_main)

    }
}