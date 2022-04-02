package com.example.profilebookkotlin.services

import com.example.profilebookkotlin.App
import com.example.profilebookkotlin.AppDatabase
import com.example.profilebookkotlin.models.user.UserModel

object AuthorizationService {
    private val _database: AppDatabase = AppDatabase.getInstance(App.getContext())

    val isAuthorized = SettingsManager.userId != 0

    suspend fun signIn(login: String, password: String): Boolean {
        var result = false

        val user = _database.userDao.get(login)
        if (user != null && user.password == password){
            SettingsManager.userId = user.userId
            result = true;
        }

        return result
    }

    suspend fun signUp(user: UserModel) {
        _database.userDao.insert(user)
    }

    suspend fun hasLogin(login: String) : Boolean{
        val user = _database.userDao.get(login)

        return user != null
    }

    fun logout(){
        SettingsManager.userId = 0
    }
}