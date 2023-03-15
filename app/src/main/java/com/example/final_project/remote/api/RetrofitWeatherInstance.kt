package com.example.final_project.remote.api

import com.example.final_project.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitWeatherInstance {
    companion object {
        private val retrofitWeather by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_WEATHER)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val apiWeather by lazy {
            retrofitWeather.create(WeatherApiService::class.java)
        }
    }
}