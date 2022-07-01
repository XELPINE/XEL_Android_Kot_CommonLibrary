package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class XELRetrofitUtils (
    var baseUrl : String
        )
{
    var retrofitInstance : Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getInstance () : Retrofit
    {
        return retrofitInstance
    }


}