package com.example.profilebookkotlin.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        binding.signInButton.setOnClickListener {
            signIn(it)
        }

        binding.signUpButton.setOnClickListener {
            goToSignUp(it)
        }
    }

    private fun signIn(view: View?) {
        TODO("Add logic")
    }

    private fun goToSignUp(view: View?) {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}