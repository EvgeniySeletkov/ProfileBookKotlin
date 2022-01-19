package com.example.profilebookkotlin.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.profilebookkotlin.views.fragments.MainListFragmentDirections

class MainListViewModel : ViewModel() {
    fun onAddProfile(view: View){
        view.findNavController().navigate(MainListFragmentDirections.actionMainListFragmentToAddEditProfileFragment())
    }
}