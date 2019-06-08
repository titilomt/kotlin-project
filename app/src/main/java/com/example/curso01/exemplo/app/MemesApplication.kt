package com.example.curso01.exemplo.app

import android.app.Application

class MemesApplication : Application {

    constructor() : super()

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        val PROD = true
        val URL_API_DEV = "https://api.fluo.site"
        val URL_API_PROD = "https://api.fluo.site"
        val URL_API = if(PROD) URL_API_PROD else URL_API_DEV
        val URL_MEMES_API = "https://api.imgflip.com/"

        private var instance: MemesApplication? = null

        fun getIntance(): MemesApplication?{
            return instance
        }

    }

}
