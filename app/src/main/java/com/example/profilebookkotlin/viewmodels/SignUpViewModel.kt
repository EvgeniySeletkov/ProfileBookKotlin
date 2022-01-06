package com.example.profilebookkotlin.viewmodels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController

public class SignUpViewModel : ViewModel() {
    private val _login = MutableLiveData<String>()
    public val login: LiveData<String> = _login

    private val _password = MutableLiveData<String>()
    public val password: LiveData<String> = _password

    private val _confirmPassword = MutableLiveData<String>()
    public val confirmPassword: LiveData<String> = _confirmPassword

    public fun onSignUpClick(view: View){
        view.findNavController().popBackStack()
    }

}