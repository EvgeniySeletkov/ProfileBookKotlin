package com.example.profilebookkotlin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.profilebookkotlin.models.profile.ProfileModel
import com.example.profilebookkotlin.models.user.UserDao
import com.example.profilebookkotlin.models.user.UserModel

@Database(
    entities = [
        UserModel::class,
        ProfileModel::class],
    version = 1,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao : UserDao

    companion object{
        @Volatile
        private var _instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this){
                var instance = _instance

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "my_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    _instance = instance
                }

                return instance
            }
        }
    }
}