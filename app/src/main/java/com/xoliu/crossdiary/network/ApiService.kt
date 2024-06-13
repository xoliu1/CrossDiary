package com.xoliu.crossdiary.network

import com.xoliu.crossdiary.model.entities.Dialogue
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("index")
    suspend fun getDialogue(@Query("key")key:String): Dialogue
}

object RetrofitInstance {
    val apiTaiCi: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://apis.tianapi.com/dialogue/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
