package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils

import com.google.gson.GsonBuilder
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.RetrofitService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class XELRetrofitUtils (
    var baseUrl : String
)
{
    var retrofitInstance : Retrofit? = null
//    var retrofitInstance : Retrofit = Retrofit.Builder()
//        .baseUrl(baseUrl)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    fun getInstance () : Retrofit
//    {
//        return retrofitInstance
//    }

    private val CONNECT_TIMEOUT_SEC = 20000L

    fun create(): RetrofitService
    {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
            .build()

        val gson = GsonBuilder().setLenient().create()

        retrofitInstance = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofitInstance!!.create(RetrofitService::class.java)
    }


}