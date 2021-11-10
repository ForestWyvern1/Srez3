package ru.a1exs.srez

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.a1exs.srez.api.app.App
import ru.a1exs.srez.api.models.RegAuthBody
import ru.a1exs.srez.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonSignIn.setOnClickListener {
            if (checkEmail() && checkPassword()){
//                val disp = App.dm.api
//                    .auth(RegAuthBody(binding.email.text.toString(), binding.password.text.toString()))
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe({
//                        App.dm.setToken(it.body()!!.accessToken)
//                        App.dm.setRefToken(it.body()!!.refreshToken)
//                        startActivity(Intent(this, MainActivity::class.java))
//                    }, {
//                        println(it.localizedMessage)
//                    })
                startActivity(Intent(this, MainActivity::class.java))
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
                }else {
                    binding.email.error = "Incorrect email address"
                }
            }
        } else {
            binding.email.error = "Incorrect email address"
        }

        return responce
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