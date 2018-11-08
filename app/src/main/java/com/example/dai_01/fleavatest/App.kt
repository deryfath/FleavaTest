package com.example.dai_01.fleavatest

import android.app.Application
import com.example.dai_01.fleavatest.dagger.component.AppComponent
import com.example.dai_01.fleavatest.dagger.component.DaggerAppComponent
import com.example.dai_01.fleavatest.dagger.module.ApiModule
import com.example.dai_01.fleavatest.dagger.module.AppModule
import com.example.dai_01.fleavatest.dagger.module.NetworkModule

class App : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
                    .appModule(AppModule(this))
                    .networkModule(NetworkModule("http://128.199.83.121:5021"))
                    .apiModule(ApiModule())
                    .build()
    }
}