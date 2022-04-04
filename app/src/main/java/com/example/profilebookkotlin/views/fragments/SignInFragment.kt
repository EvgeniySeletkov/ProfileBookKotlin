package com.example.profilebookkotlin.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.profilebookkotlin.Constants
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.databinding.FragmentSignInBinding
import com.example.profilebookkotlin.viewmodels.SignInViewModel

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var viewModel: SignInViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sign_in,
            container,
            false)
        viewModel = ViewModelProvider(this)[SignInViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        navController = findNavController()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListeners()
        observe()
    }

    private fun setOnClickListeners() {
        binding.signInButton.setOnClickListener { viewModel.onSignInTapped(navController, requireContext()) }
        binding.signUpButton.setOnClickListener { viewModel.onSignUpTapped(navController) }
    }

    private fun observe() {
        val liveData = navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>(Constants.LOGIN)
        liveData?.observe(viewLifecycleOwner){ login ->
            if (login != null){
                viewModel.login.value = login
                liveData.value = null
            }
        }

        viewModel.login.observe(viewLifecycleOwner){
            binding.signInButton.isEnabled = !checkAreFieldsEmpty()
        }

        viewModel.password.observe(viewLifecycleOwner){
            binding.signInButton.isEnabled = !checkAreFieldsEmpty()
        }
    }

    private fun checkAreFieldsEmpty() : Boolean{
        return viewModel.login.value.isNullOrBlank() || viewModel.password.value.isNullOrBlank()
    }
}