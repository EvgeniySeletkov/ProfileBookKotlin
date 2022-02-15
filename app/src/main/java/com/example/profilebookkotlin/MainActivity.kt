package com.example.profilebookkotlin

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    private var _navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        instance = this

        val navController = findNavController(R.id.navHostFragment)
        onNavControllerActivated(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return _navController?.navigateUp() ?: false || super.onSupportNavigateUp()
    }

    private fun onNavControllerActivated(navController: NavController) {
        if (_navController != navController){
            _navController?.removeOnDestinationChangedListener(destinationListener)
            navController.addOnDestinationChangedListener(destinationListener)
            _navController = navController
        }
    }

    private val destinationListener = NavController.OnDestinationChangedListener { _, destination, _ ->
        //supportActionBar?.title = prepareTitle(destination.label, arguments)
        supportActionBar?.setDisplayHomeAsUpEnabled(!isStartDestination(destination))
    }

    private fun isStartDestination(destination: NavDestination?): Boolean {
        var result = false

        if (destination != null) {
            val graph = destination.parent
            if (graph != null){
                val startDestinations = setOf(R.id.mainListFragment, R.id.signInFragment2) + graph.startDestination
                result = startDestinations.contains(destination.id)
            }
        }

        return result
    }

    companion object {
        private var _instance: Activity? = null
        var instance: Activity? = _instance
    }
}