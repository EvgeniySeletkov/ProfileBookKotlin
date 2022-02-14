package com.example.profilebookkotlin.viewmodels

import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.profilebookkotlin.App
import com.example.profilebookkotlin.models.user.UserModel
import com.example.profilebookkotlin.services.authorization.AuthorizationService
import com.example.profilebookkotlin.views.fragments.SignInFragment
import com.example.profilebookkotlin.views.fragments.SignUpFragment
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.math.log

class SignUpViewModel : ViewModel() {
    private var _login = MutableLiveData<String>()
    var login: MutableLiveData<String> = _login

    private val _password = MutableLiveData<String>()
    val password: MutableLiveData<String> = _password

    private val _confirmPassword = MutableLiveData<String>()
    val confirmPassword: MutableLiveData<String> = _confirmPassword

    fun onSignUpClick(view: View){
        if (hasValidLogin()
            && hasValidPassword()
            && _password.value == _confirmPassword.value){
            viewModelScope.launch {
                if (!AuthorizationService.hasLogin(_login.value.toString())){
                    val user = createUser()

                    if (user != null){
                        AuthorizationService.signUp(user)

                        view.findNavController().previousBackStackEntry?.savedStateHandle?.set(SignUpFragment.LOGIN, login.value)
                        view.findNavController().popBackStack()
                    }
                }
            }
        }
    }

    private fun hasValidLogin() : Boolean{
        var result = false
        val loginRegex = Regex("^[A-Za-z][A-Za-z\\d]{3,15}\$")
        if (loginRegex.containsMatchIn(login.value.toString())){
            result = true
        }
        else{
            result = false
            showErrorAlert("Hello")
        }

        return result
    }

    private fun hasValidPassword() : Boolean{
        val passRegex = Regex("^[A-Z](?=.*[a-z])(?=.*\\d)[a-zA-Z\\d]{5,15}\$")
        return passRegex.matches(password.value.toString())
    }

    private fun showErrorAlert(message: String){
        val builder = AlertDialog.Builder(App.applicationContext())
        builder.setTitle("Alert")
            .setMessage(message)
            .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            }).create()

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