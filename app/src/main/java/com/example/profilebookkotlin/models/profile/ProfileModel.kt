package com.example.profilebookkotlin.models.profile

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "profile")
data class ProfileModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val image: String?,
    val nickname: String,
    val name: String,
    val description: String,
    val dateTime: Date
    )