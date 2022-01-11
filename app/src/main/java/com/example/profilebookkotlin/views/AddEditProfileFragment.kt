package com.example.profilebookkotlin.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.databinding.FragmentAddEditProfileBinding
import com.example.profilebookkotlin.databinding.FragmentMainListBinding

class AddEditProfileFragment : Fragment() {
    private lateinit var binding: FragmentAddEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_edit_profile,
            container,
            false)

        return binding.root
    }
}