package com.example.profilebookkotlin.views.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import androidx.core.net.toFile
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.databinding.FragmentAddEditProfileBinding
import com.example.profilebookkotlin.viewmodels.AddEditViewModel
import java.io.File
import java.net.URI

class AddEditProfileFragment : Fragment() {
    private lateinit var binding: FragmentAddEditProfileBinding
    private lateinit var viewModel: AddEditViewModel

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

        viewModel = ViewModelProvider(this)[AddEditViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.profileImage.setOnClickListener { onAddProfileImage() }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_edit_navbar_menu, menu)
    }

    private fun onAddProfileImage() {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Title")
        builder.setItems(arrayOf(
            "Gallery",
            "Camera" ),
            DialogInterface.OnClickListener { dialog, which ->
                chooseAction(dialog, which)
            })

        builder.create().show()
    }

    private fun chooseAction(dialog: DialogInterface?, which: Int) {
        val gallery = when (which){
            0 -> Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            else -> Intent(MediaStore.ACTION_IMAGE_CAPTURE, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        }

        startActivityForResult(gallery, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1){
            binding.profileImage.setImageURI(data?.data)
            viewModel.image.value = data?.data?.lastPathSegment
        }
    }
}