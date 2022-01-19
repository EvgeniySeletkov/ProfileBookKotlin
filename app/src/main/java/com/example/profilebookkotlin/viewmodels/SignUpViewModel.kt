package com.example.profilebookkotlin.viewmodels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.profilebookkotlin.models.user.UserModel
import com.example.profilebookkotlin.services.authorization.AuthorizationService

class SignUpViewModel : ViewModel() {
    private var _login = MutableLiveData<String>()
    var login: MutableLiveData<String> = _login

    private val _password = MutableLiveData<String>()
    val password: MutableLiveData<String> = _password

    private val _confirmPassword = MutableLiveData<String>()
    val confirmPassword: MutableLiveData<String> = _confirmPassword

    fun onSignUpClick(view: View){
        if (_password.value == _confirmPassword.value){
            if (!AuthorizationService.hasLogin(_login.value.toString())){
                val user = createUser()

                if (user != null){
                    AuthorizationService.signUp(user)
                    view.findNavController().popBackStack()
                }
            }
        }
    }

    private fun createUser(): UserModel?{
        var user: UserModel? = null

        if (login.value != password.value){
            user = UserModel(
                login = login.value.toString(),
                password = password.value.toString()
            )
        }

        return user
    }
}