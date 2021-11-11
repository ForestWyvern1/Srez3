package ru.a1exs.srez.controller

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.a1exs.srez.R
import ru.a1exs.srez.common.api.app.App
import ru.a1exs.srez.common.api.models.RegAuthBody
import ru.a1exs.srez.common.base.BaseActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.myLooper()!!).postDelayed({
            if (App.dm.getEmail().isNullOrEmpty()){
                startActivityWithFinish(this, WelcomeActivity::class.java)
            }
            else {
                val disp = App.dm.api
                    .auth(
                        RegAuthBody(
                            App.dm.getEmail().toString(),
                            App.dm.getPassword().toString()
                        )
                    )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if (it.code() == 200) {
                            App.dm.setToken(it.body()!!.accessToken)
                            App.dm.setRefToken(it.body()!!.refreshToken)
                            startActivityWithFinish(
                                this,
                                MainActivity::class.java
                            )
                        } else {
                            Toast.makeText(this, "Something goes wrong", Toast.LENGTH_SHORT).show()
                        }
                    }, {
                        println(it.localizedMessage)
                    })
            }
        }, 2000)

    }
}