package com.example.profilebookkotlin

import android.app.Application
import com.example.profilebookkotlin.services.profile.ProfileService

public class App : Application() {
    val profileService = ProfileService()
}