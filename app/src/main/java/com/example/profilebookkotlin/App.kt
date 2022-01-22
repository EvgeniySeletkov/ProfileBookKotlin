package com.example.profilebookkotlin

import android.app.Application
import android.content.Context
import com.example.profilebookkotlin.services.profile.ProfileService

class App : Application() {
    val profileService = ProfileService()

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
}