package com.example.yo_apano.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL =
        "https://x8ki-letl-twmt.n7.xano.io/api:03B66VrO/"

    val instance: UsuarioApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsuarioApiService::class.java)
    }
}
