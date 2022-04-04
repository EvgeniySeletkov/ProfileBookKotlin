package com.example.profilebookkotlin.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.databinding.FragmentSignUpBinding
import com.example.profilebookkotlin.viewmodels.SignUpViewModel

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: SignUpViewModel
    private lateinit var navController: NavController

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
        navController = findNavController()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpButton.setOnClickListener { viewModel.onSignUpTapped(navController, requireContext()) }
        observe()
    }

    private fun observe() {
        viewModel.login.observe(viewLifecycleOwner){
            binding.signUpButton.isEnabled = !checkAreFieldsEmpty()
        }

        viewModel.password.observe(viewLifecycleOwner){
            binding.signUpButton.isEnabled = !checkAreFieldsEmpty()
        }

        viewModel.confirmPassword.observe(viewLifecycleOwner){
            binding.signUpButton.isEnabled = !checkAreFieldsEmpty()
        }
    }

    private fun checkAreFieldsEmpty() : Boolean{
        return viewModel.login.value.isNullOrBlank()
                || viewModel.password.value.isNullOrBlank()
                || viewModel.confirmPassword.value.isNullOrBlank()
    }
}