package com.example.profilebookkotlin.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.profilebookkotlin.models.profile.ProfileModel
import com.example.profilebookkotlin.services.profile.ProfileService
import com.example.profilebookkotlin.views.fragments.MainListFragmentDirections
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainListViewModel : ViewModel() {
    private var _profiles = arrayListOf<ProfileModel>()
    var profiles = MutableLiveData<ArrayList<ProfileModel>>()

    init {
        viewModelScope.launch {
            _profiles = ArrayList(ProfileService.getAllProfiles())
            profiles.value = _profiles
        }
    }

    fun onAddProfile(view: View){
//        _profiles.add(
//            ProfileModel(
//                id = 6,
//                image = null,
//                nickname = "cba",
//                name = "cba",
//                description = "cba",
//                dateTime = SimpleDateFormat("dd/MM/yyyy HH:mm aa").format(Date()).toString(),
//                userId = 1
//            )
//        )
//        profiles.value = _profiles
//        viewModelScope.launch {
//            ProfileService.saveProfile(
//                ProfileModel(
//                    image = null,
//                    nickname = "cba",
//                    name = "cba",
//                    description = "cba",
//                    dateTime = SimpleDateFormat("dd/MM/yyyy HH:mm aa").format(Date()).toString(),
//                    userId = 1
//                )
//            )
//        }
        view.findNavController().navigate(MainListFragmentDirections.actionMainListFragmentToAddEditProfileFragment())
    }
}