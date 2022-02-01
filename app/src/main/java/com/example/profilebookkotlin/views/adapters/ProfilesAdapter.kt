package com.example.profilebookkotlin.views.adapters

import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.databinding.ItemProfileBinding
import com.example.profilebookkotlin.models.profile.ProfileModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ProfilesAdapter(private val profiles: ArrayList<ProfileModel>)
    : RecyclerView.Adapter<ProfilesAdapter.ProfilesViewHolder>() {
    var onItemLongClickListener: ((ProfileModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_profile, parent, false)
        return ProfilesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfilesViewHolder, position: Int) {
        holder.bind(profiles[position])
    }

    override fun getItemCount(): Int {
        return profiles.size
    }

    inner class ProfilesViewHolder(item: View) : RecyclerView.ViewHolder(item){
        val binding = ItemProfileBinding.bind(item)

        init {
            item.setOnLongClickListener {
                onItemLongClickListener?.invoke(profiles[adapterPosition])
                return@setOnLongClickListener true
            }
        }

        fun bind(profile: ProfileModel) = with(binding){
            if (profile.image != null){
                //profileImage.setImageURI(Uri.parse(profile.image))
                profileImage.load(profile.image)
            }
            profileNickname.text = profile.nickname
            profileName.text = profile.name
            profileDate.text = profile.dateTime
        }
    }
}