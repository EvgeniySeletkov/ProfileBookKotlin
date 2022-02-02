package com.example.profilebookkotlin.views.adapters

import android.view.*
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.databinding.ItemProfileBinding
import com.example.profilebookkotlin.models.profile.ProfileModel
import java.util.*


class ProfilesAdapter(private val profiles: ArrayList<ProfileModel>)
    : RecyclerView.Adapter<ProfilesAdapter.ProfilesViewHolder>(), View.OnLongClickListener{
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

    override fun onLongClick(view: View): Boolean {
        showPopupMenu(view)
        return true
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.menu.add(Menu.NONE, 0, Menu.NONE, "Редактировать")
        popupMenu.menu.add(Menu.NONE, 1, Menu.NONE, "Удалить")
    }

    class ProfilesViewHolder(item: View) : RecyclerView.ViewHolder(item){
        val binding = ItemProfileBinding.bind(item)

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