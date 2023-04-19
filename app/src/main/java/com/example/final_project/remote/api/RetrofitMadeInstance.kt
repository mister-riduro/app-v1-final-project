package com.example.final_project.remote.api

import com.example.final_project.util.Constants
import com.example.final_project.util.Constants.Companion.LOCAL_API_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitMadeInstance {

    companion object {
        private val retrofitMade by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl("$LOCAL_API_URL/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val apiMade by lazy {
            retrofitMade.create(MadeApiService::class.java)
        }
    }
}