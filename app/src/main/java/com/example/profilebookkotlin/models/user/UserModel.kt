package com.example.profilebookkotlin.models.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
public data class UserModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val login: String,
    val password: String
    )