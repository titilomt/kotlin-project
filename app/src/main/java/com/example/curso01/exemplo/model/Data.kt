package com.example.curso01.exemplo.model

import com.google.gson.annotations.SerializedName

data class Data (
    @SerializedName("memes") var listMemes: List<Memes>
)