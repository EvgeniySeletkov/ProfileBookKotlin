package com.example.profilebookkotlin.services.authorization

import com.example.profilebookkotlin.models.user.UserModel

interface AuthorizationService {
    fun signIn(login: String, password: String) : Boolean

    fun signUp(user: UserModel)
}