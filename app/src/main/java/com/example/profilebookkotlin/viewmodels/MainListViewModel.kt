package com.example.profilebookkotlin.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.profilebookkotlin.App
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.models.profile.ProfileModel
import com.example.profilebookkotlin.services.authorization.AuthorizationService
import com.example.profilebookkotlin.services.profile.ProfileService
import com.example.profilebookkotlin.views.fragments.MainListFragmentDirections
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

class MainListViewModel : ViewModel() {
    private var _profiles = arrayListOf<ProfileModel>()
    var profiles = MutableLiveData<ArrayList<ProfileModel>>()

    fun onGetProfiles(){
        viewModelScope.launch {
            _profiles = ArrayList(ProfileService.getAllProfiles())
            profiles.value = _profiles
        }
    }

    fun onAddProfile(view: View){
        view.findNavController().navigate(MainListFragmentDirections.actionMainListFragmentToAddEditProfileFragment(0, App.getContext().getString(R.string.AddProfile)))
    }

    fun onDeleteProfile(profile: ProfileModel){
        viewModelScope.launch {
            ProfileService.deleteProfile(profile)
        }
    }

    fun onLogout(){
        AuthorizationService.logout()
    }
}