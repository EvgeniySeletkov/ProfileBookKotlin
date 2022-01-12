package com.example.profilebookkotlin.services.profile

import com.example.profilebookkotlin.models.Profile
import java.util.*

typealias ProfilesListener = (profiles: List<Profile>) -> Unit

public class ProfileService {
    private var _profiles : MutableList<Profile> = mutableListOf<Profile>()

    private val listeners : MutableSet<ProfilesListener> = mutableSetOf<ProfilesListener>()

    init {
        _profiles.addAll(listOf(
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
        ))
    }

    public fun addListener(listener: ProfilesListener){
        listeners.add(listener)
        listener.invoke(_profiles)
    }

    public fun removeListener(listener: ProfilesListener){
        listeners.remove(listener)
    }

    private fun notifyChanges(){
        listeners.forEach { it.invoke(_profiles) }
    }

    public fun getProfiles() : List<Profile> {
        return _profiles
    }
}