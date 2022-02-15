package com.example.profilebookkotlin.services.authorization

import com.example.profilebookkotlin.App
import com.example.profilebookkotlin.AppDatabase
import com.example.profilebookkotlin.models.user.UserModel

object AuthorizationService{
    private var _database: AppDatabase = AppDatabase.getInstance(App.getContext())

    suspend fun signIn(login: String, password: String): Boolean {
        val user = _database.userDao.get(login)
        return user != null && user.password == password
    }

    suspend fun signUp(user: UserModel) {
        _database.userDao.insert(user)
    }

    suspend fun hasLogin(login: String) : Boolean{
        val user = _database.userDao.get(login)

        return user != null
    }
}