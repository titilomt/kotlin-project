package com.example.curso01.exemplo.service

import com.example.curso01.exemplo.app.MemesApplication
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

    private val retrofitMemes = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(MemesApplication.URL_MEMES_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitAuth = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(MemesApplication.URL_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun serviceAuth(): ServiceAuth {
        return retrofitAuth.create(ServiceAuth::class.java)
    }

    fun serviceMemes(): ServiceMeme {
        return retrofitMemes.create(ServiceMeme::class.java)
    }
}