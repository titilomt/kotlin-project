package com.example.curso01.exemplo.service

import com.example.curso01.exemplo.model.Account
import retrofit2.Call
import retrofit2.http.POST

interface ServiceTeam {
    @POST("v1/account/auth")
    fun auth(): Call<Account>

    @POST("v1/account")
    fun cadastro(): Call<Void>
}