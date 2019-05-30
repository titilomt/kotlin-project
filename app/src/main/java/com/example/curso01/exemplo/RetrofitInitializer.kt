package com.example.curso01.exemplo

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInitializer {

    companion object {
        private val okHttpClient: OkHttpClient by lazy {
            OkHttpClient.Builder()
                // habilitando impressão das requisições no logcat
                .addInterceptor(HttpLoggingInterceptor().also { it -> it.level = HttpLoggingInterceptor.Level.BODY })
                // configurando timeout das requisições
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()
        }

    }


    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("http://apiteste.fourtime.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun serviceTeam(): ServiceTeam {
        return retrofit.create(ServiceTeam::class.java)
    }
}