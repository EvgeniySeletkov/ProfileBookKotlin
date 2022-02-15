package com.example.profilebookkotlin.viewmodels

import android.app.AlertDialog
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.profilebookkotlin.MainActivity
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.services.authorization.AuthorizationService
import com.example.profilebookkotlin.views.fragments.SignInFragmentDirections
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {
    private val _login = MutableLiveData<String>()
    val login: MutableLiveData<String> = _login

    private val _password = MutableLiveData<String>()
    val password: MutableLiveData<String> = _password

    fun onOpenSignUpFragment(view: View){
        view.findNavController().navigate(R.id.action_signInFragment2_to_signUpFragment)
    }

    fun onSignInClick(view: View) {
        viewModelScope.launch {
            val isAuthorize = AuthorizationService.signIn(_login.value.toString(), _password.value.toString())

            if (isAuthorize){
                view.findNavController().navigate(SignInFragmentDirections.actionSignInFragment2ToMainListFragment())
            }
            else {
                showErrorAlert("Invalid login or password!")
            }
        }
    }

    private fun showErrorAlert(message: String){
        val builder = AlertDialog.Builder(MainActivity.instance)
        builder.setTitle("Alert")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, which ->
                dialog.cancel()
            }.create()

        builder.show()
    }
}