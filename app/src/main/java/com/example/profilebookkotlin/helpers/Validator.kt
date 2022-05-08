package com.example.profilebookkotlin.helpers

object Validator {
    fun checkIsLoginValid(login: String) : Boolean {
        return Regex("^[A-Za-z][A-Za-z\\d]{3,15}\$").containsMatchIn(login)
    }

    fun checkIsPasswordValid(password: String) : Boolean {
        return Regex("^[A-Z](?=.*[a-z])(?=.*\\d)[a-zA-Z\\d]{7,15}\$").containsMatchIn(password)
    }
}