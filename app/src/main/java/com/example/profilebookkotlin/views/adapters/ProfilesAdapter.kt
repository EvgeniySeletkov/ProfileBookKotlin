package com.example.profilebookkotlin.views.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.profilebookkotlin.App
import com.example.profilebookkotlin.databinding.ItemProfileBinding
import com.example.profilebookkotlin.models.profile.ProfileModel

interface ProfileActionListener{
    fun onEditProfile(profile: ProfileModel)

    fun onDeleteProfile(profile: ProfileModel)
}

class ProfilesAdapter(private val profiles: ArrayList<ProfileModel>,
                      private val actionListener: ProfileActionListener)
    : RecyclerView.Adapter<ProfilesAdapter.ProfilesViewHolder>(), View.OnClickListener{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProfileBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return ProfilesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfilesViewHolder, position: Int) {
        val profile = profiles[position]
        holder.itemView.tag = profile
        holder.bind(profile)

    }

    override fun getItemCount(): Int {
        return profiles.size
    }

    override fun onClick(view: View) {
        showPopupMenu(view)
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.menu.add(Menu.NONE, 0, Menu.NONE, "Редактировать")
        popupMenu.menu.add(Menu.NONE, 1, Menu.NONE, "Удалить")
        popupMenu.setOnMenuItemClickListener { selectMenuItem(it.itemId, view) }
        popupMenu.show()
    }

    private fun selectMenuItem (itemId: Int, view: View) : Boolean{
        val profile = view.tag as ProfileModel

        when (itemId) {
            0 -> {
                //actionListener.onEditProfile(profile)
                Toast.makeText(App.getContext(), "Edit", Toast.LENGTH_SHORT).show()
            }
            1 -> {
                actionListener.onDeleteProfile(profile)
                profiles.remove(profile)
                notifyDataSetChanged()
            }
        }
        return true
    }

    class ProfilesViewHolder(
        val binding: ItemProfileBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(profile: ProfileModel) = with(binding){
            if (profile.image != null){
                val image = Uri.parse(profile.image)
                profileImage.setImageURI(image)
            }
            profileNickname.text = profile.nickname
            profileName.text = profile.name
            profileDate.text = profile.dateTime
        }
    }
}