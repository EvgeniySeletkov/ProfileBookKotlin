package com.example.profilebookkotlin.views.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.databinding.FragmentMainListBinding
import com.example.profilebookkotlin.models.profile.ProfileModel
import com.example.profilebookkotlin.viewmodels.MainListViewModel
import com.example.profilebookkotlin.views.adapters.ProfileActionListener
import com.example.profilebookkotlin.views.adapters.ProfilesAdapter

class MainListFragment : Fragment() {
    private lateinit var binding: FragmentMainListBinding
    private lateinit var viewModel: MainListViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main_list,
            container,
            false)
        viewModel = ViewModelProvider(this)[MainListViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        navController = findNavController()

        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addProfileButton.setOnClickListener { viewModel.onAddProfileTapped(navController, requireContext()) }

        val profileActionListener = object : ProfileActionListener{
            override fun onEditProfile(profile: ProfileModel) {
                navController.navigate(MainListFragmentDirections.actionMainListFragmentToAddEditProfileFragment(profile.id, getString(R.string.EditProfile)))
            }

            override fun onDeleteProfile(profile: ProfileModel) {
                viewModel.onDeleteProfile(profile)
                binding.noProfilesLabel.isVisible = viewModel.profiles.value!!.isEmpty()
            }
        }

        viewModel.profiles.observe(viewLifecycleOwner, Observer {
            val adapter = ProfilesAdapter(it, profileActionListener)
            binding.recyclerView.adapter = adapter
            binding.noProfilesLabel.isVisible = viewModel.profiles.value!!.isEmpty()
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.onGetProfiles()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_list_navbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.logout_action -> {
                viewModel.onLogout(navController, requireContext())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}