package com.example.profilebookkotlin.viewmodels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.views.fragments.SignInFragmentDirections

public class SignInViewModel : ViewModel() {
    private val _login = MutableLiveData<String>()
    public val login: LiveData<String> = _login

    private val _password = MutableLiveData<String>()
    public val password: LiveData<String> = _password

    public fun onOpenSignUpFragment(view: View){
        view.findNavController().navigate(R.id.action_signInFragment2_to_signUpFragment)
    }

    public fun onSignInClick(view: View) {
        //view.findNavController().graph.startDestination = R.id.mainListFragment
        view.findNavController().navigate(SignInFragmentDirections.actionSignInFragment2ToMainListFragment())
    }
}