package com.example.profilebookkotlin.views.adapters

import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.profilebookkotlin.App
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.databinding.ItemProfileBinding
import com.example.profilebookkotlin.models.profile.ProfileModel
import com.example.profilebookkotlin.services.profile.ProfileService
import java.util.*

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
        holder.bind(profiles[position])
    }

    override fun getItemCount(): Int {
        return profiles.size
    }

    override fun onClick(view: View) {
        val profile = view.tag as ProfileModel
        showPopupMenu(view)
    }

//    override fun onLongClick(view: View): Boolean {
//        showPopupMenu(view)
//        return true
//    }

    private fun showPopupMenu(view: View) {
        val profile = view.tag as ProfileModel
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
                Toast.makeText(App.applicationContext(), "Edit", Toast.LENGTH_SHORT).show()
            }
            1 -> {
                //actionListener.onDeleteProfile(profile)
                //profiles.remove(profile)
                Toast.makeText(App.applicationContext(), "Delete", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    class ProfilesViewHolder(
        val binding: ItemProfileBinding)
        : RecyclerView.ViewHolder(binding.root){

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