package com.example.final_project.remote.api

import com.example.final_project.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitLocInstance {
    companion object {
        private val retrofitLocation by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_BYNDERBYTE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val apiLocation by lazy {
            retrofitLocation.create(LocationApiService::class.java)
        }
    }
}