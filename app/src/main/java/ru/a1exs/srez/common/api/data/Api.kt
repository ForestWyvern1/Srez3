package ru.a1exs.srez.common.api.data

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import ru.a1exs.srez.common.api.models.RegAuthBody
import ru.a1exs.srez.common.api.models.RegAuthResp
import ru.a1exs.srez.common.api.models.TopicsModel

interface Api {

    @POST("v1/auth/register")
    fun register(@Body requestAuthBody : RegAuthBody) : Observable<Response<RegAuthResp>>

    @POST("v1/auth/login")
    fun auth(@Body requestAuthBody: RegAuthBody): Observable<Response<RegAuthResp>>

    @GET("v1/topic")
    fun getTopic(@Header("Authorization") token: String): Observable<Response<List<TopicsModel>>>

    companion object {
        fun createApi(): Api {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val retrofit = Retrofit.Builder()
                .baseUrl("http://45.144.179.101/scare-me/api/mobile/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(Api::class.java)
        }
    }
}