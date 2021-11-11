package ru.a1exs.srez.controller

import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.a1exs.srez.common.api.app.App
import ru.a1exs.srez.common.api.models.RegAuthBody
import ru.a1exs.srez.common.base.BaseActivity
import ru.a1exs.srez.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignUp.setOnClickListener {

            if (checkEmail(binding.email) && checkPassword(binding.password) && checkconfirmPassword(
                    binding.password,
                    binding.confirmPassword
                )
            ) {
                val disp = App.dm.api
                    .register(
                        RegAuthBody(
                            binding.email.text.toString(),
                            binding.password.text.toString()
                        )
                    )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        println(it.code())
                        if (it.code() == 400) {
                            binding.password.error = "Password is too short"
                        } else if (it.code() == 200) {
                            App.dm.setToken(it.body()!!.accessToken)
                            App.dm.setRefToken(it.body()!!.refreshToken)
                            App.dm.setEmail(binding.email.text.toString())
                            App.dm.setPassword(binding.password.text.toString())
                            startActivityWithFinish(this, RegInfoActivity::class.java)
                        } else if (it.code() == 409) {
                            binding.email.error = "This email address is already register"
                        }
                    }, {
                        it.message
                    })
            }

        }
    }
}