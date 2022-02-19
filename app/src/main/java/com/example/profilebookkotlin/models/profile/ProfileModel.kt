package com.example.profilebookkotlin.models.profile

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class ProfileModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var image: String?,
    var nickname: String,
    var name: String,
    var description: String?,
    val dateTime: String,
    var userId: Int
    )