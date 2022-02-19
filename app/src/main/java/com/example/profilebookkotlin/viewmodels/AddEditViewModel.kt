package com.example.profilebookkotlin.viewmodels

import android.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profilebookkotlin.App
import com.example.profilebookkotlin.Constants
import com.example.profilebookkotlin.MainActivity
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.models.profile.ProfileModel
import com.example.profilebookkotlin.services.profile.ProfileService
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddEditViewModel : ViewModel() {
    lateinit var profile: ProfileModel

    private val _image = MutableLiveData<String>()
    val image: MutableLiveData<String> = _image

    private val _nickname = MutableLiveData<String>()
    val nickname: MutableLiveData<String> = _nickname

    private val _name = MutableLiveData<String>()
    val name: MutableLiveData<String> = _name

    private val _description = MutableLiveData<String>()
    val description: MutableLiveData<String> = _description

    fun initializeProfileById(id: Int){
        viewModelScope.launch {
            profile = ProfileService.getProfileById(id)
            image.value = profile.image
            nickname.value = profile.nickname
            name.value = profile.name
            description.value = profile.description
        }
    }

    fun onProfileSaved() : Boolean{
        var result = false

        if (!hasEmptyFields()){
            if (!this::profile.isInitialized){
                profile = createProfile()
            }
            else{
                editProfile()
            }

            viewModelScope.launch {
                ProfileService.saveProfile(profile)
            }

            result = true
        }
        else{
            showErrorAlert(App.getContext().getString(R.string.NickNameOrNameFieldIsEmpty))
        }

        return result
    }

    private fun hasEmptyFields() : Boolean{
        return nickname.value.isNullOrBlank() || name.value.isNullOrBlank()
    }

    private fun createProfile() : ProfileModel{
        return ProfileModel(
            image = image.value,
            nickname = nickname.value.toString(),
            name = name.value.toString(),
            description = description.value,
            dateTime = SimpleDateFormat(Constants.DATE_OUTPUT_PATTERN).format(Date()).toString(),
            userId = 0
        )
    }

    private fun editProfile() {
        profile.image = image.value
        profile.nickname = nickname.value.toString()
        profile.name = name.value.toString()
        profile.description = description.value
    }

    private fun showErrorAlert(message: String){
        val builder = AlertDialog.Builder(MainActivity.instance)
        builder.setTitle(App.getContext().getString(R.string.Alert))
            .setMessage(message)
            .setPositiveButton(App.getContext().getString(R.string.OK)) { dialog, which ->
                dialog.cancel()
            }.create()

        builder.show()
    }
}