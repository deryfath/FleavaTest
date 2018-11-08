package com.example.dai_01.fleavatest.activity.main

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dai_01.fleavatest.App
import com.example.dai_01.fleavatest.R
import com.example.dai_01.fleavatest.activity.main.login.LoginActivity
import com.example.dai_01.fleavatest.extension.get
import com.example.dai_01.fleavatest.extension.remove
import kotlinx.android.synthetic.main.account_layout.*
import javax.inject.Inject

class AccountFragment :Fragment() {

    @Inject
    lateinit var pref: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.account_layout, container, false)
        (activity as MainActivity).getSupportActionBar()?.setDisplayHomeAsUpEnabled(false)
        App.component.inject(this)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        email_profile.text = pref.get("email")
        bio_profile.text = pref.get("name")
        phone_profile.text = "02938982323"
        gender_profile.text = "Male"

        logout.setOnClickListener {
            pref.remove("token")
            val intentAct= Intent(activity, LoginActivity::class.java)
            startActivity(intentAct)
            activity.finish()
        }
    }
}