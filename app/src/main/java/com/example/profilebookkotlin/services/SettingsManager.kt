package com.example.profilebookkotlin.services

import android.content.Context
import com.example.profilebookkotlin.App

object SettingsManager {
    private val _preferences = App.getContext().getSharedPreferences("APP_PREFERENCES", Context.MODE_PRIVATE)

    var userId: Int
    get() = _preferences.getInt("USER_ID", 0)
    set(value) = _preferences.edit().putInt("USER_ID", value).apply()
}