package com.example.profilebookkotlin.viewmodels

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profilebookkotlin.models.profile.ProfileModel
import com.example.profilebookkotlin.services.authorization.AuthorizationService
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

    fun onSaveProfile(){
       val profile = ProfileModel(
                image = image.value,
                nickname = "cba",
                name = "cba",
                description = "cba",
                dateTime = SimpleDateFormat("dd/MM/yyyy HH:mm aa").format(Date()).toString(),
                userId = 1
            )

        viewModelScope.launch {
            ProfileService.saveProfile(profile)
        }
    }

//    fun onPickImage(view: View) {
//        val builder = AlertDialog.Builder(view.context)
//        builder.setTitle("Title")
//        builder.setItems(arrayOf(
//            "Gallery",
//            "Camera" ),
//            DialogInterface.OnClickListener { dialog, which ->
//                chooseAction(dialog, which, view)
//            })
//
//        builder.create().show()
//    }
//
//    private fun chooseAction(dialog: DialogInterface?, which: Int, view: View) {
//        val gallery: Intent
//
//        when (which){
//            0 -> gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
//            else -> gallery = Intent(MediaStore.ACTION_IMAGE_CAPTURE, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
//        }
//
//        startActivityForResult(view.context as Activity, gallery, 1)
//    }
}