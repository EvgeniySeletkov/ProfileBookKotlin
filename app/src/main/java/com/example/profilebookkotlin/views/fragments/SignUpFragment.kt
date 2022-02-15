package com.example.profilebookkotlin.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.databinding.FragmentSignUpBinding
import com.example.profilebookkotlin.viewmodels.SignUpViewModel

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sign_up,
            container,
            false)
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.signUpButton.isEnabled = false

        viewModel.login.observe(viewLifecycleOwner){
            binding.signUpButton.isEnabled = !hasEmptyFields()
        }

        viewModel.password.observe(viewLifecycleOwner){
            binding.signUpButton.isEnabled = !hasEmptyFields()
        }

        viewModel.confirmPassword.observe(viewLifecycleOwner){
            binding.signUpButton.isEnabled = !hasEmptyFields()
        }

        return binding.root
    }

    private fun hasEmptyFields() : Boolean{
        return viewModel.login.value.isNullOrBlank()
                || viewModel.password.value.isNullOrBlank()
                || viewModel.confirmPassword.value.isNullOrBlank()
    }

    companion object{
        const val LOGIN = "LOGIN"
    }
}