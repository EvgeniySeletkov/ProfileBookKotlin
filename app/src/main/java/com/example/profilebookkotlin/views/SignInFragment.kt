package com.example.profilebookkotlin.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSignInBinding>(
            inflater,
            R.layout.fragment_sign_in,
            container,
            false)

        binding.signUpButton.setOnClickListener {
            signUpClick(it)
        }

        return binding.root
    }

    private fun signUpClick(view: View?) {
        view?.findNavController()?.navigate(R.id.action_signInFragment2_to_signUpFragment)
    }
}