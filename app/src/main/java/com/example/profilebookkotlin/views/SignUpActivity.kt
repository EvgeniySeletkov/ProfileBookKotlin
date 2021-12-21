package com.example.profilebookkotlin.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        binding.signUpButton.setOnClickListener {
            signUp(it)
        }
    }

    private fun signUp(view: View?) {
        TODO("Add logic")
    }
}