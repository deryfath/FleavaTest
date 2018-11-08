package com.example.dai_01.fleavatest.activity.main.login

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dai_01.fleavatest.App
import com.example.dai_01.fleavatest.R
import kotlinx.android.synthetic.main.landing_layout.*
import javax.inject.Inject

class LandingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.landing_layout, container, false)
        (activity as LoginActivity).getSupportActionBar()?.setTitle("landingFragment")
        (activity as LoginActivity).getSupportActionBar()?.setDisplayHomeAsUpEnabled(false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        login_text.setOnClickListener {

            val loginFragment = LoginFragment()
            val ft : FragmentTransaction = activity.getSupportFragmentManager().beginTransaction()
            ft.setCustomAnimations(R.anim.right_enter, R.anim.left_out)
            ft.add(R.id.frameContainer, loginFragment)
            ft.addToBackStack(null)
            ft.hide(this)
            ft.commit()

        }

    }
}