package com.example.profilebookkotlin.services

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.*

object ImageService {
    fun saveImageFromUri(context: Context?, uri: Uri) : String?{
        var result: String? = null

        try {
            val stream = context?.contentResolver?.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(stream)

            result = saveImageFromBitmap(context, bitmap)
        }
        catch (ex: Exception){
            Log.i(this::saveImageFromUri.name, ex.message.toString())
        }

        return result
    }

    fun saveImageFromBitmap(context: Context?, bitmap: Bitmap) : String?{
        var result: String? = null

        try {
            val wrapper = ContextWrapper(context)
            val dir = wrapper.getDir("images", Context.MODE_PRIVATE)
            val file = File(dir, "${UUID.randomUUID()}.jpg")

            val fileStream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileStream)
            fileStream.flush()
            fileStream.close()

            result = file.path
        }
        catch (ex: Exception){
            Log.i(this::saveImageFromBitmap.name, ex.message.toString())
        }

        return result
    }
}