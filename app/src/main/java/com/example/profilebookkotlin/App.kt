package com.example.profilebookkotlin

import android.app.Application
import android.content.Context

class App : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: App? = null

        fun getContext() : Context {
            return instance!!.applicationContext
        }
    }
}