package com.example.dai_01.fleavatest.activity.main

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.Button
import com.example.dai_01.fleavatest.App
import com.example.dai_01.fleavatest.R
import com.example.dai_01.fleavatest.extension.debug
import com.example.dai_01.fleavatest.model.ResponseData
import com.example.dai_01.fleavatest.model.WeatherList
import com.example.dai_01.fleavatest.model.WeatherListResponse
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.components.RxActivity
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.view.LayoutInflater
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.graphics.Color.parseColor
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.example.dai_01.fleavatest.tools.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.*


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {


    var fragmentManager = supportFragmentManager
    lateinit var fragment:Fragment

//    private lateinit var linearLayoutManager: LinearLayoutManager
//    private lateinit var weatherAdapter : RecyclerCardMainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        App.component.inject(this)

        initBottomNav()

        if (savedInstanceState == null) {
            fragment = MainFragment()
            callFragment(fragment)

        }
    }

    private fun initBottomNav(){

        navigation.setItemIconTintList(null);

        navigation.selectedItemId = R.id.navigation_spirit

        val bottomNavigationMenuView = navigation.getChildAt(0) as BottomNavigationMenuView
        val v = bottomNavigationMenuView.getChildAt(4)
        val itemView = v as BottomNavigationItemView

        val badge = LayoutInflater.from(this)
                .inflate(R.layout.badge_item_action, bottomNavigationMenuView, false)

        itemView.addView(badge)

        BottomNavigationViewHelper.removeShiftMode(navigation);

        navigation.setOnNavigationItemSelectedListener(this);



    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.navigation_body ->  gotoMain("Body")
            R.id.navigation_mind -> gotoMain("Mind")
            R.id.navigation_spirit -> gotoMain("Spirit")
            R.id.navigation_event -> gotoMain("Event")
            R.id.navigation_account ->
                gotoMain("Account")

            else -> { // Note the block
                title_main.text = "Spirit"
            }
        }

        return true
    }

    private fun gotoMain(title: String){
        title_main.text = title

        if(title!="Account"){
            layout_fab.visibility = View.VISIBLE
            fragment = MainFragment()
            callFragment(fragment)
        }else{
            layout_fab.visibility = View.INVISIBLE
            fragment = AccountFragment()
            callFragment(fragment)
        }
    }

    private fun callFragment(fragment: android.support.v4.app.Fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container_main, fragment)
                .commit()
    }

    override fun onBackPressed() {

        finish()
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
