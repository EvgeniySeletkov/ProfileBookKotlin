package com.example.profilebookkotlin

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.profilebookkotlin.models.profile.ProfileModel
import com.example.profilebookkotlin.models.user.UserDao
import com.example.profilebookkotlin.models.user.UserModel

@Database(
    entities = [
        UserModel::class,
        ProfileModel::class],
    version = 1)
abstract class Database : RoomDatabase() {
    public abstract fun getUserDao() : UserDao
}