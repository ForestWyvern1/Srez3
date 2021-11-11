package ru.a1exs.srez.controller

import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.a1exs.srez.common.api.app.App
import ru.a1exs.srez.common.api.models.RegAuthBody
import ru.a1exs.srez.common.base.BaseActivity
import ru.a1exs.srez.databinding.ActivitySignInBinding

class SignInActivity : BaseActivity() {
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignIn.setOnClickListener {
            if (checkEmail(binding.email) && checkPassword(binding.password)) {
                val disp = App.dm.api
                    .auth(
                        RegAuthBody(
                            binding.email.text.toString(),
                            binding.password.text.toString()
                        )
                    )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if (it.code() == 200) {
                            App.dm.setToken(it.body()!!.accessToken)
                            App.dm.setRefToken(it.body()!!.refreshToken)
                            App.dm.setEmail(binding.email.text.toString())
                            App.dm.setPassword(binding.password.text.toString())
                            startActivity(this, MainActivity::class.java)
                        } else if (it.code() == 404) {
                            binding.email.error =
                                "User with such a pair of email and password was not found"
                        } else if (it.code() == 400) {
                            binding.password.error = "Short Password"
                        }
                    }, {
                        println(it.localizedMessage)
                    })
            }
        }


    }
}