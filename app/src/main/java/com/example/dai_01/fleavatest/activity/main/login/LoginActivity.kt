package com.example.dai_01.fleavatest.activity.main.login

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.dai_01.fleavatest.App
import com.example.dai_01.fleavatest.R
import com.example.dai_01.fleavatest.Tools
import com.example.dai_01.fleavatest.activity.main.MainActivity
import com.example.dai_01.fleavatest.extension.debug
import com.example.dai_01.fleavatest.extension.get
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    val fragmentManager = supportFragmentManager
    val landingFragment = LandingFragment()

    @Inject
    lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //hide status bar
        Tools.hideStatusBar(this)

        setContentView(R.layout.activity_login)

        App.component.inject(this)

        //FRAGMENT

        if (pref.get("token") == null) {
            initFragment()
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun initFragment(){

        //FRAGMENT

        val transaction = fragmentManager.beginTransaction()
        //membuat object fragmentPertama
        transaction.add(R.id.frameContainer, landingFragment)
        //menambahkan fragment
        transaction.addToBackStack("landingFragment")
        //dapat menyimpan fragment ke dalam state ,ketika tombol back diklik
        transaction.commit()
        //mengeksekusi fragment transaction


    }

    override fun onBackPressed() {

        val count = fragmentManager.backStackEntryCount
        debug("count : $count")
        if (count <= 1) {
            debug("exit")
            finish()
            //additional code
        } else {
            debug("back fragment")
            fragmentManager.popBackStack()
        }

    }
}
