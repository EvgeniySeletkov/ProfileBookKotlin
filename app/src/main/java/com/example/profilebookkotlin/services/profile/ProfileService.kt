package com.example.profilebookkotlin.services.profile

import com.example.profilebookkotlin.App
import com.example.profilebookkotlin.AppDatabase
import com.example.profilebookkotlin.models.profile.ProfileModel

//typealias ProfilesListener = (profiles: List<ProfileModel>) -> Unit

object ProfileService {
    private var _database: AppDatabase = AppDatabase.getInstance(App.applicationContext())

    suspend fun getAllProfiles() : List<ProfileModel>{
        val userId = 1
        return _database.profileDao.getAll(userId)
    }

    suspend fun saveProfile(profile: ProfileModel){
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