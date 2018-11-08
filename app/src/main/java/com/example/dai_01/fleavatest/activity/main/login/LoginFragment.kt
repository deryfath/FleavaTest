package com.example.dai_01.fleavatest.activity.main.login

import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.dai_01.fleavatest.App
import com.example.dai_01.fleavatest.R
import com.example.dai_01.fleavatest.Tools
import com.example.dai_01.fleavatest.activity.main.MainActivity
import com.example.dai_01.fleavatest.extension.debug
import com.example.dai_01.fleavatest.extension.save
import com.example.dai_01.fleavatest.model.LoginResponse
import com.example.dai_01.fleavatest.tools.CustomToast
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.android.ActivityEvent
import io.reactivex.Observable
import kotlinx.android.synthetic.main.login_layout.*
import java.util.regex.Pattern
import javax.inject.Inject

class LoginFragment : Fragment(), LoginView {

    @Inject
    lateinit var presenter: LoginPresenter

    lateinit var progress : ProgressDialog

    private var shakeAnimation: Animation? = null

    @Inject
    lateinit var pref: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.login_layout, container, false)
        (activity as LoginActivity).getSupportActionBar()?.setTitle("loginFragment")
        (activity as LoginActivity).getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)


        App.component.inject(this)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        shakeAnimation = AnimationUtils.loadAnimation(activity,
                R.anim.shake)

        //PROGRESS BAR
        progress = Tools.progressBar(activity,"Loading...")

        presenter.onAttach(this)

        login_submit.setOnClickListener {

            val getEmail = email_login.getText().toString()
            val getPassword = password_login.getText().toString()

            // Check patter for email id
            val p = Pattern.compile(Tools.regEx)

            val m = p.matcher(getEmail)

            // Check for both field is empty or not
            if (getEmail == "" || getEmail.length == 0
                    || getPassword == "" || getPassword.length == 0) {
                login_layout.startAnimation(shakeAnimation)
                CustomToast().Show_Toast(activity, view,
                        "Email Dan Password Tidak Boleh Kosong","error")

            } else if (!m.find()) {
                login_layout.startAnimation(shakeAnimation)
                CustomToast().Show_Toast(activity, view,
                        "Email Tidak Valid","error")

            }else {
                progress.show()
                presenter.login(getEmail,getPassword)

            }

        }
    }

    override fun onAttach() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoginSuccess(data: LoginResponse) {
        debug("LOGIN SUCCESS : ${data} ")

        pref.save("token",data.token)
        pref.save("email",data.email)
        pref.save("name",data.name)

        progress.cancel()

        //go to Main activity
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
        activity.finish()
    }

    override fun onLoginError(data: String) {
        debug("LOGIN ERROR : ${data} ")
        CustomToast().Show_Toast(activity, view,
                data,"error")
    }

    override fun lifecycle(): Observable<ActivityEvent> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T> bindTolifeCycle(): LifecycleTransformer<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}