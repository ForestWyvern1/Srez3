package ru.a1exs.srez.common.base

import android.content.Context
import android.content.Intent
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    fun startActivity(from : Context, to : Class<*>){
        startActivity(Intent(from, to))
    }

    fun startActivityWithFinish(from : Context, to : Class<*>){
        startActivity(from, to)
        finish()
    }

    fun checkEmail(email: EditText): Boolean {
        when {
            email.text.isNullOrEmpty() -> {
                email.error = "Empty Email"
                return false
            }
            android.util.Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches() -> {
                return true
            }
            else -> {
                email.error = "Incorrect Email Address"
                return false
            }
        }
    }

    fun checkconfirmPassword(password: EditText, confirmPassword: EditText): Boolean {
        return if (confirmPassword.text.isNullOrEmpty()) {
            confirmPassword.error = "Empty Confirm"
            false
        } else if (confirmPassword.text.toString() == password.text.toString()) {
            true
        } else {
            confirmPassword.error = "Password doesn't match"
            false
        }
    }

    fun checkPassword(password: EditText): Boolean {
        return if (password.text.isNullOrEmpty()) {
            password.error = "Empty Password"
            false
        } else {
            true
        }
    }
}