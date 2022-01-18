package com.example.profilebookkotlin.viewmodels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.services.authorization.AuthorizationService
import com.example.profilebookkotlin.views.fragments.SignInFragmentDirections

public class SignInViewModel : ViewModel() {
    private val _login = MutableLiveData<String>()
    public val login: MutableLiveData<String> = _login

    private val _password = MutableLiveData<String>()
    public val password: MutableLiveData<String> = _password

    init {
        login.value = "Hello"
    }

    public fun onOpenSignUpFragment(view: View){
        view.findNavController().navigate(R.id.action_signInFragment2_to_signUpFragment)
    }

    public fun onSignInClick(view: View) {
        //view.findNavController().graph.startDestination = R.id.mainListFragment
        var isAuthorize = AuthorizationService.signIn(_login.value.toString(), _password.value.toString())

        if (isAuthorize){
            view.findNavController().navigate(SignInFragmentDirections.actionSignInFragment2ToMainListFragment())
        }
    }
}