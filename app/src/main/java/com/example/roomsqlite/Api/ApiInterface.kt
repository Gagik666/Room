package com.example.roomsqlite.Api

import com.example.roomsqlite.Db.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {
    @GET("marvel")
    fun getData(): Call<List<User>>

    object NewService {
        val retrofitBulder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.simplifiedcoding.net/demos/")
            .build()
            .create(ApiInterface::class.java)
    }
}