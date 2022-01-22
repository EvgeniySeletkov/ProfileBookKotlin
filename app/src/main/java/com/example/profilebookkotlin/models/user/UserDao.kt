package com.example.profilebookkotlin.models.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: UserModel)

    @Query("SELECT * from user WHERE login = :login")
    suspend fun get(login: String): UserModel?
}