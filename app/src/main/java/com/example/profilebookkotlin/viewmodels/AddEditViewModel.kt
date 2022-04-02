package com.example.profilebookkotlin.viewmodels

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.profilebookkotlin.App
import com.example.profilebookkotlin.Constants
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.models.profile.ProfileModel
import com.example.profilebookkotlin.services.ProfileService
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddEditViewModel : ViewModel() {
    private var profile = ProfileModel()

    val image: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val nickname: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val name: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val description: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun initializeProfileById(id: Int){
        viewModelScope.launch {
            profile = ProfileService.getProfileById(id)
            image.value = profile.image
            nickname.value = profile.nickname
            name.value = profile.name
            description.value = profile.description
        }
    }

    fun onProfileSave(navController: NavController, context: Context){
        profile.image = if (image.value != null) image.value.toString() else ""
        profile.nickname = nickname.value.toString()
        profile.name = name.value.toString()
        profile.description = if (description.value != null) description.value.toString() else ""

        if (!hasEmptyFields()){
            viewModelScope.launch {
                if (profile.userId == 0){
                    profile.dateTime = SimpleDateFormat(Constants.DATE_OUTPUT_PATTERN).format(Date()).toString()
                    ProfileService.addProfile(profile)
                }
                else {
                    ProfileService.editProfile(profile)
                }
            }

            navController.popBackStack()
        }
        else{
            showErrorAlert(context, context.getString(R.string.NickNameOrNameFieldIsEmpty))
        }
    }

    private fun hasEmptyFields() : Boolean{
        return nickname.value.isNullOrBlank() || name.value.isNullOrBlank()
    }

    private fun showErrorAlert(context: Context, message: String){
        val builder = AlertDialog.Builder(context)
        builder.setTitle(App.getContext().getString(R.string.Alert))
            .setMessage(message)
            .setPositiveButton(App.getContext().getString(R.string.OK)) { dialog, which ->
                dialog.cancel()
            }.create()

        builder.show()
    }
}