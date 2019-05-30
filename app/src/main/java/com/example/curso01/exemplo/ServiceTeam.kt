package com.example.curso01.exemplo

import retrofit2.Call
import retrofit2.http.GET

interface ServiceTeam {
    @GET("time/lista")
    fun getTeam(): Call<ListTeam>
}