package com.example.profilebookkotlin.viewmodels

import android.app.AlertDialog
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.profilebookkotlin.Constants
import com.example.profilebookkotlin.MainActivity
import com.example.profilebookkotlin.models.user.UserModel
import com.example.profilebookkotlin.services.authorization.AuthorizationService
import com.example.profilebookkotlin.views.fragments.SignUpFragment
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    private var _login = MutableLiveData<String>()
    var login: MutableLiveData<String> = _login

    private val _password = MutableLiveData<String>()
    val password: MutableLiveData<String> = _password

    private val _confirmPassword = MutableLiveData<String>()
    val confirmPassword: MutableLiveData<String> = _confirmPassword

    fun onSignUpClick(view: View){
        if (hasValidLogin()
            && hasValidPassword()){
            if(password.value == confirmPassword.value){
                viewModelScope.launch {
                    if (!AuthorizationService.hasLogin(_login.value.toString())){
                        val user = createUser()

                        if (user != null){
                            AuthorizationService.signUp(user)

                            view.findNavController().previousBackStackEntry?.savedStateHandle?.set(Constants.LOGIN, login.value)
                            view.findNavController().popBackStack()
                        }
                    }
                    else {
                        showErrorAlert("This login is busy!")
                    }
                }
            }
            else{
                showErrorAlert("Password and confirm password fields are not matches!")
            }
        }
    }

    private fun hasValidLogin() : Boolean{
        val result: Boolean
        val loginRegex = Regex("^[A-Za-z][A-Za-z\\d]{3,15}\$")

        if (loginRegex.containsMatchIn(login.value.toString())){
            result = true
        }
        else{
            result = false
            showErrorAlert("Login be at least 4 and no more than 16 and first symbol must not be a digit!")
        }

        return result
    }

    private fun hasValidPassword() : Boolean{
        val result: Boolean
        val passRegex = Regex("^[A-Z](?=.*[a-z])(?=.*\\d)[a-zA-Z\\d]{7,15}\$")

        if (passRegex.containsMatchIn(password.value.toString())){
            result = true
        }
        else{
            result = false
            showErrorAlert("Password be at least 8 and no more than 16 and must contain at least one uppercase letter, one lowercase letter and one number!")
        }
        return result
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