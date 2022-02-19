package com.example.profilebookkotlin.services.profile

import android.content.Context
import com.example.profilebookkotlin.App
import com.example.profilebookkotlin.AppDatabase
import com.example.profilebookkotlin.Constants
import com.example.profilebookkotlin.models.profile.ProfileModel
import com.example.profilebookkotlin.services.authorization.AuthorizationService

object ProfileService {
    private var _database: AppDatabase = AppDatabase.getInstance(App.getContext())
    private val _preferences = App.getContext().getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)

    suspend fun getAllProfiles() : List<ProfileModel>{
        val userId = _preferences.getInt(Constants.USER_ID, 0)
        return _database.profileDao.getAll(userId)
    }

    suspend fun saveProfile(profile: ProfileModel){
        if (profile.userId == 0){
            profile.userId = _preferences.getInt(Constants.USER_ID, 0)
        }

        if (profile.id == 0){
            _database.profileDao.insert(profile)
        }
        else {
            _database.profileDao.update(profile)
        }
    }

    suspend fun deleteProfile(profile: ProfileModel){
        _database.profileDao.delete(profile)
    }
}