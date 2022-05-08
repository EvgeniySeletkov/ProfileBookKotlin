package com.example.profilebookkotlin.viewmodels

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.profilebookkotlin.Constants
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.helpers.Validator
import com.example.profilebookkotlin.models.user.UserModel
import com.example.profilebookkotlin.services.AuthorizationService
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    val login: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val password: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val confirmPassword: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun onSignUpTapped(navController: NavController, context: Context){
        if (checkIsLoginValid(context)
            && checkIsPasswordValid(context)){
            if(password.value == confirmPassword.value){
                viewModelScope.launch {
                    if (!AuthorizationService.checkIsUserExist(login.value.toString())){
                        val user = createUser()

                        if (user != null){
                            AuthorizationService.signUp(user)

                            navController.previousBackStackEntry?.savedStateHandle?.set(Constants.LOGIN, login.value)
                            navController.popBackStack()
                        }
                    }
                    else {
                        showErrorAlert(context, context.getString(R.string.ThisLoginIsBusy))
                    }
                }
            }
            else{
                showErrorAlert(context, context.getString(R.string.PasswordsFieldsNotMatches))
            }
        }
    }

    private fun checkIsLoginValid(context: Context) : Boolean{
        val result: Boolean

        if (Validator.checkIsLoginValid(login.value.toString())){
            result = true
        }
        else{
            result = false
            showErrorAlert(context ,context.getString(R.string.LoginIsInvalid))
        }

        return result
    }

    private fun checkIsPasswordValid(context: Context) : Boolean{
        val result: Boolean

        if (Validator.checkIsPasswordValid(password.value.toString())){
            result = true
        }
        else{
            result = false
            showErrorAlert(context, context.getString(R.string.PasswordIsInvalid))
        }
        return result
    }

    private fun showErrorAlert(context: Context, message: String){
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.Alert))
            .setMessage(message)
            .setPositiveButton(context.getString(R.string.OK)) { dialog, which ->
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