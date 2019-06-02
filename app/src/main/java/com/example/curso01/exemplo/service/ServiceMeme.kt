package com.example.curso01.exemplo.service

import com.example.curso01.exemplo.model.CreateMemes
import com.example.curso01.exemplo.model.MainMemes
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceMeme {

    @GET("get_memes")
    fun getMemes(): Call<MainMemes>

    @POST("caption_image")
    fun createMemes(@Field("template_id") id: String,
                    @Field("username") username: String,
                    @Field("password") password: String,
                    @Field("text0") txt1: String,
                    @Field("text1") txt2: String): Call<CreateMemes>

}