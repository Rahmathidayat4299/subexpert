package com.dicoding.retrofit

import com.dicoding.core.data.source.remote.network.ApiUser
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroService {
    private const val BASE_URL = "https://api.github.com/"

    private var client = OkHttpClient.Builder().addInterceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Token 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")
            .build()
        chain.proceed(newRequest)
    }.build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiInstansiasi: ApiUser = retrofit.create(ApiUser::class.java)
}