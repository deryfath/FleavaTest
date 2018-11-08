package com.example.dai_01.fleavatest.activity.main.login

import com.example.dai_01.fleavatest.dagger.qualifier.Authorized
import com.example.dai_01.fleavatest.extension.errorConverter
import com.example.dai_01.fleavatest.mvp.Presenter
import com.example.dai_01.fleavatest.service.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import java.util.LinkedHashMap
import javax.inject.Inject

class LoginPresenter @Inject constructor(
        @Authorized val api: ApiService,
        val retrofit: Retrofit
):Presenter<LoginView>{

    private var view : LoginView? = null

    var loginDisposables = Disposables.empty()

    override fun onAttach(view: LoginView) {
        this.view = view
    }

    override fun onDetach() {
        view = null
    }

    fun login(email : String, password: String){


        loginDisposables.dispose()
        val map = LinkedHashMap<String,String>()
        map.put("email",email)
        map.put("password",password)
        loginDisposables = api.login(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    res ->

                    view?.onLoginSuccess(res)

                }, {
                    err ->
                    if (err is HttpException) {
                        val body = retrofit.errorConverter<Response<Throwable>>(err)
                        view?.onLoginError("Error: ${body.body().toString()}")
                    } else {
                        view?.onLoginError(err.localizedMessage.toString())
                    }
                })
    }
}