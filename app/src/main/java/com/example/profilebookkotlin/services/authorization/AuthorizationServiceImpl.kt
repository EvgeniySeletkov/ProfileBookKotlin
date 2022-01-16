package com.example.profilebookkotlin.services.authorization

import com.example.profilebookkotlin.models.user.UserDao
import com.example.profilebookkotlin.models.user.UserModel

class AuthorizationServiceImpl(
    private val userDao: UserDao
) : AuthorizationService {
    override fun signIn(login: String, password: String): Boolean {
        val user = userDao.get(login)
        return user != null && user.password == password
    }

    override fun signUp(user: UserModel) {
        userDao.insert(user)
    }
}