package com.example.profilebookkotlin.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.profilebookkotlin.models.profile.ProfileModel
import com.example.profilebookkotlin.views.fragments.MainListFragmentDirections
import java.util.*
import kotlin.collections.ArrayList

class MainListViewModel : ViewModel() {
    private var _profiles = arrayListOf<ProfileModel>()
    var profiles = MutableLiveData<ArrayList<ProfileModel>>()

    init {
        _profiles = arrayListOf(
            ProfileModel(
                id = 1,
                image = null,
                nickname = "abc",
                name = "abc",
                description = "abc",
                dateTime = Date()
            ),
            ProfileModel(
                id = 2,
                image = null,
                nickname = "abc",
                name = "abc",
                description = "abc",
                dateTime = Date()
            ),
            ProfileModel(
                id = 3,
                image = null,
                nickname = "abc",
                name = "abc",
                description = "abc",
                dateTime = Date()
            ),
            ProfileModel(
                id = 4,
                image = null,
                nickname = "abc",
                name = "abc",
                description = "abc",
                dateTime = Date()
            ),
            ProfileModel(
                id = 5,
                image = null,
                nickname = "abc",
                name = "abc",
                description = "abc",
                dateTime = Date()
            ),
            ProfileModel(
                id = 6,
                image = null,
                nickname = "abc",
                name = "abc",
                description = "abc",
                dateTime = Date()
            )
        )
        profiles.value = _profiles
    }

    fun onAddProfile(view: View){
        _profiles.add(
            ProfileModel(
                id = 6,
                image = null,
                nickname = "cba",
                name = "cba",
                description = "cba",
                dateTime = Date()
            )
        )
        profiles.value = _profiles
        //view.findNavController().navigate(MainListFragmentDirections.actionMainListFragmentToAddEditProfileFragment())
    }
}