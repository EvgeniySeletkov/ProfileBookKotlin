package com.example.profilebookkotlin.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.profilebookkotlin.App
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.adapters.ProfilesAdapter
import com.example.profilebookkotlin.databinding.FragmentMainListBinding
import com.example.profilebookkotlin.services.profile.ProfileService
import com.example.profilebookkotlin.services.profile.ProfilesListener
import com.example.profilebookkotlin.viewmodels.MainListViewModel

public class MainListFragment : Fragment() {
    private lateinit var binding: FragmentMainListBinding
    private lateinit var viewModel: MainListViewModel
    private lateinit var adapter: ProfilesAdapter

    private val profilesService: ProfileService
    get() = (activity?.applicationContext as App).profileService

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

        adapter = ProfilesAdapter()
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        profilesService.addListener(profilesListener)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        profilesService.removeListener(profilesListener)
    }

    private val profilesListener: ProfilesListener = {
        adapter.profiles = it
    }
}