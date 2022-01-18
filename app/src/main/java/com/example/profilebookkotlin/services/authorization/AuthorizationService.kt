package com.example.profilebookkotlin.services.authorization

import android.app.Application
import com.example.profilebookkotlin.App
import com.example.profilebookkotlin.AppDatabase
import com.example.profilebookkotlin.models.user.UserDao
import com.example.profilebookkotlin.models.user.UserModel

object AuthorizationService{
    private var _database: AppDatabase?

    init {
        _database = AppDatabase.getInstance(App.applicationContext())
    }

    fun signIn(login: String, password: String): Boolean {
        val user = _database?.userDao?.get(login)
        return user != null && user.password == password
    }

    fun signUp(user: UserModel) {
        _database?.userDao?.insert(user)
    }

    fun hasLogin(login: String) : Boolean{
        var user = _database?.userDao?.get(login)

        return user != null
    }
}