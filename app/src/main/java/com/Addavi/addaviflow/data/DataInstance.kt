package com.Addavi.addaviflow.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

private val BASE_URL = "https://call1.tgju.org/"

object DataInstance {
    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS) // تاخیر اتصال
            .readTimeout(10, TimeUnit.SECONDS)    // تاخیر خواندن پاسخ
            .writeTimeout(10, TimeUnit.SECONDS)   // تاخیر ارسال
            .addInterceptor { chain ->
                try {
                    chain.proceed(chain.request())
                } catch (e: UnknownHostException) {
                    // وقتی اینترنت قطع است
                    throw IOException("اتصال اینترنت برقرار نیست", e)
                } catch (e: SocketTimeoutException) {
                    throw IOException("اتصال به سرور با تاخیر مواجه شد", e)
                }
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
            }
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiInterface: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
    }