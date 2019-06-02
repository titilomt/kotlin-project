package com.example.curso01.exemplo.model

data class CreateMemes (
    var template_id: String,
    var username: String,
    var password: String,
    var text0: String = "",
    var text1: String = ""
)