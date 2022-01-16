package com.example.profilebookkotlin.views.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.databinding.FragmentAddEditProfileBinding

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

        binding.profileImage.setOnClickListener { onAddProfileImage() }

        return binding.root
    }

    private fun onAddProfileImage() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1){
            binding.profileImage.setImageURI(data?.data)
        }
    }
}