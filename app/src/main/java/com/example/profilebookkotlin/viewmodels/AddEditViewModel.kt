package com.example.profilebookkotlin.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profilebookkotlin.models.profile.ProfileModel
import com.example.profilebookkotlin.services.profile.ProfileService
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddEditViewModel : ViewModel() {
    private val _image = MutableLiveData<String>()
    val image: MutableLiveData<String> = _image

    private val _nickname = MutableLiveData<String>()
    val nickname: MutableLiveData<String> = _nickname

    private val _name = MutableLiveData<String>()
    val name: MutableLiveData<String> = _name

    private val _description = MutableLiveData<String>()
    val description: MutableLiveData<String> = _description

    fun onProfileSaved() : Boolean{
        var result = false

        if (!haveEmptyFields()){
            val profile = createProfile()

            viewModelScope.launch {
                ProfileService.saveProfile(profile)
            }

            result = true
        }

        return result
    }

    private fun haveEmptyFields() : Boolean{
        var result = false

        if (nickname.value.isNullOrBlank()
            || name.value.isNullOrBlank()){
            result = true
        }

        return result
    }

    private fun createProfile() : ProfileModel{
        return ProfileModel(
            image = image.value,
            nickname = nickname.value.toString(),
            name = name.value.toString(),
            description = description.value,
            dateTime = SimpleDateFormat("dd/MM/yyyy HH:mm aa").format(Date()).toString(),
            userId = 0
        )
    }
}