package com.example.profilebookkotlin.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.profilebookkotlin.R
import com.example.profilebookkotlin.views.adapters.ProfilesAdapter
import com.example.profilebookkotlin.databinding.FragmentMainListBinding
import com.example.profilebookkotlin.viewmodels.MainListViewModel

class MainListFragment : Fragment() {
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

        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager

        viewModel.profiles.observe(viewLifecycleOwner, Observer {
            val adapter = ProfilesAdapter(it)
            adapter.onItemLongClickListener = { profile ->
                registerForContextMenu(binding.recyclerView)
            }
            binding.recyclerView.adapter = adapter
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_list_navbar_menu, menu)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.add(Menu.NONE, 0, Menu.NONE, "Редактировать")
        menu.add(Menu.NONE, 1, Menu.NONE, "Удалить")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return super.onContextItemSelected(item)

        when (item.itemId){
            0 -> {}
            1 -> {}
        }
    }
}