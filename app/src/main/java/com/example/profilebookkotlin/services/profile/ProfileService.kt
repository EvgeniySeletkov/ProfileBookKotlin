package com.example.profilebookkotlin.services.profile

import com.example.profilebookkotlin.models.Profile
import java.util.*

typealias ProfilesListener = (profiles: List<Profile>) -> Unit

public class ProfileService {
    private var _profiles = mutableListOf<Profile>()
    private val _listeners = mutableSetOf<ProfilesListener>()

    init {
        _profiles = listOf(
            Profile(
                id = 1,
                image = null,
                nickname = "abc",
                name = "abc",
                description = "abc",
                date = Date(12, 1, 2022)
            ),
            Profile(
                id = 2,
                image = null,
                nickname = "abc",
                name = "abc",
                description = "abc",
                date = Date(12, 1, 2022)
            ),
            Profile(
                id = 3,
                image = null,
                nickname = "abc",
                name = "abc",
                description = "abc",
                date = Date(12, 1, 2022)
            ),
            Profile(
                id = 4,
                image = null,
                nickname = "abc",
                name = "abc",
                description = "abc",
                date = Date(12, 1, 2022)
            ),
            Profile(
                id = 5,
                image = null,
                nickname = "abc",
                name = "abc",
                description = "abc",
                date = Date(12, 1, 2022)
            ),
            Profile(
                id = 6,
                image = null,
                nickname = "abc",
                name = "abc",
                description = "abc",
                date = Date(12, 1, 2022)
            )
        ).toMutableList()
    }

    public fun addListener(listener: ProfilesListener){
        _listeners.add(listener)
        listener.invoke(_profiles)
    }

    public fun removeListener(listener: ProfilesListener){
        _listeners.remove(listener)
    }

    private fun notifyChanges(){
        _listeners.forEach { it.invoke(_profiles) }
    }

    public fun getProfiles() : List<Profile> {
        return _profiles
    }
}