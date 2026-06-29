package com.ibrohimapk3.employeelist.data.remote
import android.util.Log
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import kotlin.concurrent.thread
interface ApiService {
    @GET("api/?results=50&nat=us")
    suspend fun getEmployee(): ResponseUser
}
object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}