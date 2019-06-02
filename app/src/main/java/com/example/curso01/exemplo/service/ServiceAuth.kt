package com.example.curso01.exemplo.service

import com.example.curso01.exemplo.model.Account
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

interface ServiceAuth {
    @POST("/v1/account/auth")
    fun auth(@Body account: Account): Call<Account>

    @POST("/v1/account")
    fun register(@Body account: Account): Call<Void>

}