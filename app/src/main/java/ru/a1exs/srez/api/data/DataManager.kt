package ru.a1exs.srez.api.data

import android.content.Context

class DataManager(private val baseContext: Context) {
    val shared = baseContext.getSharedPreferences("WS", Context.MODE_PRIVATE)

    fun getToken() = shared.getString("token", "")
    fun setToken(value : String) = shared.edit().putString("token", value).apply()

    fun getRefToken() = shared.getString("refToken", "")
    fun setRefToken(value : String) = shared.edit().putString("refToken", value).apply()

    val api = Api.createApi()
}