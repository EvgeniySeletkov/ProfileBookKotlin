package com.example.profilebookkotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.profilebookkotlin.databinding.ItemProfileBinding
import com.example.profilebookkotlin.models.Profile

public class ProfilesAdapter : RecyclerView.Adapter<ProfilesAdapter.ProfilesViewHolder>() {
    public var _profiles: List<Profile> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProfileBinding.inflate(inflater, parent, false)
        return ProfilesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfilesViewHolder, position: Int) {
        val profile = _profiles[position]
        with(holder.binding){
            profileName.text = profile.name
        }
    }

    override fun getItemCount(): Int {
        return _profiles.size
    }

    public class ProfilesViewHolder(
        val binding: ItemProfileBinding
    ) : RecyclerView.ViewHolder(binding.root)
}