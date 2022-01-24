package com.example.profilebookkotlin.views.adapters

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

    class ProfilesViewHolder(item: View) : RecyclerView.ViewHolder(item){
        val binding = ItemProfileBinding.bind(item)

        fun bind(profile: ProfileModel) = with(binding){
            if (profile.image != null){
                profileImage.load(File(profile.image))
            }
            profileNickname.text = profile.nickname
            profileName.text = profile.name
            profileDate.text = profile.dateTime
        }
    }
}