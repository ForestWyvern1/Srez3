package ru.a1exs.srez

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.a1exs.srez.api.RegInfoActivity
import ru.a1exs.srez.api.app.App
import ru.a1exs.srez.api.models.RegAuthBody
import ru.a1exs.srez.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignUp.setOnClickListener {

            if (checkEmail() && checkPassword() && checkconfirmPassword()){
//                val disp = App.dm.api
//                    .register(RegAuthBody(binding.email.text.toString(), binding.password.text.toString()))
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe({
//                        if (it.code() == 400){
//                            binding.password.error = "Password is too short"
//                        }
//                        else {
//                            App.dm.setToken(it.body()!!.accessToken)
//                            App.dm.setRefToken(it.body()!!.refreshToken)
//                            startActivity(Intent(this, MainActivity::class.java))
//                        }
//                    }, {
//                        println("error " + it.localizedMessage)
//                    })
                startActivity(Intent(this, RegInfoActivity::class.java))
            }

        }
    }

    private fun checkEmail(): Boolean {

        var responce = false

        if (binding.email.text.isNullOrEmpty()) {
            binding.email.error = "Empty Email"
        } else if (binding.email.text.contains("@")) {
            if (binding.email.text.split("@")[0].isNullOrEmpty()) {
                binding.email.error = "Incorrect email address"
            } else if (binding.email.text.split("@")[1].isNullOrEmpty()) {
                binding.email.error = "Incorrect email address"
            } else {
                if (binding.email.text.split("@")[1].contains(".")) {
                    if (binding.email.text.split("@")[1].split(".")[0].isNullOrEmpty()) {
                        binding.email.error = "Incorrect email address"
                    } else if (binding.email.text.split(".")[1].isNullOrEmpty()) {
                        binding.email.error = "Incorrect email address"
                    } else {
                        responce = true
                    }
                }
                else {
                    binding.email.error = "Incorrect email address"
                }
            }
        } else {
            binding.email.error = "Incorrect email address"
        }

        return responce
    }

    private fun checkconfirmPassword() : Boolean{
        return if (binding.confirmPassword.text.isNullOrEmpty()){
            binding.confirmPassword.error = "Empty Confirm"
            false
        } else if (binding.confirmPassword.text.toString() == binding.password.text.toString()){
            true
        } else {
            binding.confirmPassword.error = "Password doesn't match"
            false
        }
    }

    private fun checkPassword() : Boolean{
        return if (binding.password.text.isNullOrEmpty()){
            binding.password.error = "Empty Password"
            false
        } else {
            true
        }
    }
}