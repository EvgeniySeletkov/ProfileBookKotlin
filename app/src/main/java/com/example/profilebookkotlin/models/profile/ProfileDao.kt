package com.example.profilebookkotlin.models.profile

import androidx.room.*

@Dao
interface ProfileDao {
    @Insert
    suspend fun insert(profile: ProfileModel)

    @Update
    suspend fun update(profile: ProfileModel)

    @Delete
    suspend fun delete(profile: ProfileModel)

    @Query("SELECT * from profile WHERE userId = :userId")
    suspend fun getAll(userId: Int) : List<ProfileModel>
}