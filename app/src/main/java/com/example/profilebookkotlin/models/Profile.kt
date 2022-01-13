package com.example.profilebookkotlin.models

import java.util.*

data class Profile (
    val id: Int,
    val image: String?,
    val nickname: String,
    val name: String,
    val description: String,
    val dateTime: Calendar
)