package com.example.curso01.exemplo.service

import com.example.curso01.exemplo.model.CreateMemes
import com.example.curso01.exemplo.model.MainMemes
import com.example.curso01.exemplo.model.ResponseCreatedMemes
import retrofit2.Call
import retrofit2.http.*

interface ServiceMeme {

    @GET("get_memes")
    fun getMemes(): Call<MainMemes>

    @POST("caption_image")
    fun createMemes(
        @Query("template_id") id: String,
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("text0") txt0: String,
        @Query("text1") txt1: String
    ): Call<ResponseCreatedMemes>

}