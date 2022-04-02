package com.example.profilebookkotlin.services

import com.example.profilebookkotlin.App
import com.example.profilebookkotlin.AppDatabase
import com.example.profilebookkotlin.models.profile.ProfileModel

object ProfileService {
    private var _database: AppDatabase = AppDatabase.getInstance(App.getContext())

    suspend fun getAllProfiles() : List<ProfileModel>{
        val userId = SettingsManager.userId
        return _database.profileDao.getAll(userId)
    }

    suspend fun getProfileById(id: Int) : ProfileModel{
        return _database.profileDao.getProfileById(id)
    }

    suspend fun addProfile(profile: ProfileModel) {
        profile.userId = SettingsManager.userId
        _database.profileDao.insert(profile)
    }

    suspend fun editProfile(profile: ProfileModel) {
        _database.profileDao.update(profile)
    }

    suspend fun deleteProfile(profile: ProfileModel){
        _database.profileDao.delete(profile)
    }
}