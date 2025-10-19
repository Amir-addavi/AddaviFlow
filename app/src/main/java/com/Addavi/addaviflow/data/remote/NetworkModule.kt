package com.Addavi.addaviflow.data.remote

import android.icu.util.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.Timeout
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  RetrofitClient {
    private const val BASE_URL = "https://open-arz.milaadfarzian.workers.dev/"
    private fun createOkHttp(): OkHttpClient{
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .connectTimeout(15 , java.util.concurrent.TimeUnit.SECONDS)
            .readTimeout(20 , java.util.concurrent.TimeUnit.SECONDS)
            .build()
    }
    val arzService: ArzService by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createOkHttp())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ArzService::class.java)
    }
}