package com.example.profilebookkotlin.services.authorization

import android.content.Context
import android.text.BoringLayout
import com.example.profilebookkotlin.App
import com.example.profilebookkotlin.AppDatabase
import com.example.profilebookkotlin.Constants
import com.example.profilebookkotlin.models.user.UserModel

object AuthorizationService{
    private val _database: AppDatabase = AppDatabase.getInstance(App.getContext())
    private val _preferences = App.getContext().getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
    val isAuthorized = _preferences.getInt(Constants.USER_ID, 0) != 0

    suspend fun signIn(login: String, password: String): Boolean {
        var result = false

        val user = _database.userDao.get(login)
        if (user != null && user.password == password){
            _preferences.edit()
                .putInt(Constants.USER_ID, user.userId)
                .apply()
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
}