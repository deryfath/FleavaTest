package com.example.dai_01.fleavatest.dagger.component

import com.example.dai_01.fleavatest.activity.main.AccountFragment
import com.example.dai_01.fleavatest.activity.main.MainActivity
import com.example.dai_01.fleavatest.activity.main.login.LoginActivity
import com.example.dai_01.fleavatest.activity.main.login.LoginFragment
import com.example.dai_01.fleavatest.dagger.module.ApiModule
import com.example.dai_01.fleavatest.dagger.module.AppModule
import com.example.dai_01.fleavatest.dagger.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NetworkModule::class,
        ApiModule::class
))

interface AppComponent {

    fun inject(mainActivity:MainActivity)
    fun inject(loginActivity: LoginActivity)
    fun inject(loginFragment: LoginFragment)
    fun inject(accountFragment: AccountFragment)
}