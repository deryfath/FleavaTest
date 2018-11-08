package com.example.dai_01.fleavatest.activity.main.login

import com.example.dai_01.fleavatest.model.LoginResponse
import com.example.dai_01.fleavatest.mvp.View

interface LoginView :View {

    fun onLoginSuccess(data: LoginResponse)
    fun onLoginError(data: String)
}