package com.example.curso01.exemplo.model

import com.google.gson.annotations.SerializedName

data class ResponseCreatedMemes (
    @SerializedName("data") var data: CreatedMemes
)