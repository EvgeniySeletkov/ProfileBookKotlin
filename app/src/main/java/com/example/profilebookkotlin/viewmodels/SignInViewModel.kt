package com.example.profilebookkotlin.viewmodels

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.services.AuthorizationService
import com.example.profilebookkotlin.views.fragments.SignInFragmentDirections
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {
    val login: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val password: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun onSignInTapped(navController: NavController, context: Context) {
        viewModelScope.launch {
            val isAuthorize = AuthorizationService.signIn(login.value.toString(), password.value.toString())

            if (isAuthorize){
                navController.navigate(SignInFragmentDirections.actionSignInFragment2ToMainListFragment())
            }
            else {
                showErrorAlert(context, context.getString(R.string.InvalidLoginOrPassword))
            }
        }
    }

    fun onSignUpTapped(navController: NavController) {
        navController.navigate(R.id.action_signInFragment2_to_signUpFragment)
    }

    private fun showErrorAlert(context: Context, message: String){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Alert")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, which ->
                dialog.cancel()
            }.create()

        builder.show()
    }
}