package com.example.profilebookkotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.databinding.ItemProfileBinding
import com.example.profilebookkotlin.models.Profile
import java.util.*
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.InputStream
import java.net.URL


public class ProfilesAdapter : RecyclerView.Adapter<ProfilesAdapter.ProfilesViewHolder>() {
    public var profiles: List<Profile> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

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

    public fun initProfiles(){
        profiles = listOf(
            Profile(
                id = 1,
                image = null,
                nickname = "abc",
                name = "abc",
                description = "abc",
                date = Date(12, 1, 2022)
            ),
            Profile(
                id = 2,
                image = null,
                nickname = "abc",
                name = "abc",
                description = "abc",
                date = Date(12, 1, 2022)
            ),
            Profile(
                id = 3,
                image = null,
                nickname = "abc",
                name = "abc",
                description = "abc",
                date = Date(12, 1, 2022)
            ),
            Profile(
                id = 4,
                image = null,
                nickname = "abc",
                name = "abc",
                description = "abc",
                date = Date(12, 1, 2022)
            ),
            Profile(
                id = 5,
                image = null,
                nickname = "abc",
                name = "abc",
                description = "abc",
                date = Date(12, 1, 2022)
            ),
            Profile(
                id = 6,
                image = null,
                nickname = "abc",
                name = "abc",
                description = "abc",
                date = Date(12, 1, 2022)
            )
        ).toMutableList()
    }

//    public class ProfilesViewHolder(
//        val binding: ItemProfileBinding
//    ) : RecyclerView.ViewHolder(binding.root)


    public class ProfilesViewHolder(item: View) : RecyclerView.ViewHolder(item){
        val binding = ItemProfileBinding.bind(item)

        public fun bind(profile: Profile) = with(binding){
//            val stream: InputStream = URL(profile.image).openStream()
//            val bmp = BitmapFactory.decodeStream(stream)
//            profileImage.setImageBitmap(bmp)
            profileName.text = profile.name
        }
    }
}