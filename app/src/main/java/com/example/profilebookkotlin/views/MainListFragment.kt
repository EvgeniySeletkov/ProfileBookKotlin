package com.example.profilebookkotlin.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.databinding.FragmentMainListBinding
import com.example.profilebookkotlin.viewmodels.MainListViewModel

public class MainListFragment : Fragment() {
    private lateinit var binding: FragmentMainListBinding
    private lateinit var viewModel: MainListViewModel

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

        return inflater.inflate(R.layout.fragment_main_list, container, false)
    }
}