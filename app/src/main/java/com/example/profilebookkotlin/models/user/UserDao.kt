package com.example.profilebookkotlin.models.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insert(user: UserModel)

    @Query("SELECT * from user WHERE login = :login")
    fun get(login: String): UserModel?
}