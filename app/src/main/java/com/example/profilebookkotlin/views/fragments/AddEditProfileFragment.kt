package com.example.profilebookkotlin.views.fragments

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Context
import android.content.ContextWrapper
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.*
import androidx.core.net.toFile
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.profilebookkotlin.Constants
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.databinding.FragmentAddEditProfileBinding
import com.example.profilebookkotlin.services.image.ImageService
import com.example.profilebookkotlin.viewmodels.AddEditViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.net.URI
import java.sql.Wrapper
import java.util.*

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
        when (which){
            0 -> {
                val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                galleryIntent.type = "image/"
                startActivityForResult(galleryIntent, Constants.GALLERY_REQUEST)
            }
            else -> {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, Constants.CAMERA_REQUEST)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode){
            Constants.GALLERY_REQUEST -> {
                if (requestCode == RESULT_OK) {
                    binding.profileImage.setImageURI(data?.data)
                    val imagePath = ImageService.saveImageFromUri(this.context, data?.data!!)
                    viewModel.image.value = imagePath
                }
            }
            Constants.CAMERA_REQUEST -> {
                if (resultCode == RESULT_OK){
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    binding.profileImage.setImageBitmap(imageBitmap)
                    val imagePath = ImageService.saveImageFromBitmap(this.context, imageBitmap)
                    viewModel.image.value = imagePath
                }
            }
        }
    }
}