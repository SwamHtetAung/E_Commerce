package com.shaung.txt.e_commerce.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceBuilder {
//  (real android device)
    private const val BASE_URL = "http://192.168.1.100/72Api-Ecommerce-master/public/api/"

//  (emulator)
//    private const val BASE_URL = "http://10.0.2.2:8000/api/"

    private val Okhttp : OkHttpClient.Builder = OkHttpClient.Builder()

    private val builder : Retrofit.Builder = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(Okhttp.build())

    private val retrofit : Retrofit = builder.build()

    fun <T> buildService(serviceType : Class<T>) : T{
        return retrofit.create(serviceType)
    }

}