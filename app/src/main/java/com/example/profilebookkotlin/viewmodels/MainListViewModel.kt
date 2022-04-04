package com.example.profilebookkotlin.viewmodels

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.profilebookkotlin.App
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.models.profile.ProfileModel
import com.example.profilebookkotlin.services.AuthorizationService
import com.example.profilebookkotlin.services.ProfileService
import com.example.profilebookkotlin.views.fragments.MainListFragmentDirections
import kotlinx.coroutines.launch

class MainListViewModel : ViewModel() {
    private var _profiles = arrayListOf<ProfileModel>()
    var profiles = MutableLiveData<ArrayList<ProfileModel>>()

    fun onGetProfiles(){
        viewModelScope.launch {
            _profiles = ArrayList(ProfileService.getAllProfiles())
            profiles.value = _profiles
        }
    }

    fun onAddProfileTapped(navController: NavController, context: Context){
        navController.navigate(MainListFragmentDirections.actionMainListFragmentToAddEditProfileFragment(0, context.getString(R.string.AddProfile)))
    }

    fun onDeleteProfile(profile: ProfileModel){
        viewModelScope.launch {
            profiles.value?.remove(profile)
            ProfileService.deleteProfile(profile)
        }
    }

    fun onLogout(navController: NavController, context: Context){
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.Alert))
            .setMessage(context.getString(R.string.LogOutAlertMessage))
            .setPositiveButton(context.getString(R.string.Yes)) { dialog, which ->
                dialog.dismiss()
                logOut(navController)
            }
            .setNegativeButton(context.getString(R.string.No)) { dialog, which ->
                dialog.cancel()
            }
            .create()

        builder.show()
    }

    private fun logOut(navController: NavController) {
        AuthorizationService.logout()
        navController.navigate(MainListFragmentDirections.actionMainListFragmentToSignInFragment2())
    }
}