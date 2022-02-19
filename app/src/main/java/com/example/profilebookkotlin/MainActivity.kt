package com.example.profilebookkotlin

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.example.profilebookkotlin.services.authorization.AuthorizationService
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private var _navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        instance = this

        val navController = findNavController(R.id.navHostFragment)
        onNavControllerActivated(navController)

        prepareRootNavController(navController)
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

    private fun prepareRootNavController(navController: NavController) {
        val graph = navController.navInflater.inflate(R.navigation.navigation)

        val fragmentId = if (AuthorizationService.isAuthorized) {
            R.id.mainListFragment
        } else {
            R.id.signInFragment2
        }

        graph.startDestination = fragmentId
        navController.graph = graph
    }

    private val destinationListener = NavController.OnDestinationChangedListener { _, destination, arguments ->
        supportActionBar?.title = prepareTitle(destination.label, arguments)
        supportActionBar?.setDisplayHomeAsUpEnabled(!isStartDestination(destination))
    }

    private fun prepareTitle(label: CharSequence?, arguments: Bundle?): String {
        if (label == null) return ""
        val title = StringBuffer()
        val fillInPattern = Pattern.compile("\\{(.+?)\\}")
        val matcher = fillInPattern.matcher(label)
        while (matcher.find()) {
            val argName = matcher.group(1)
            if (arguments != null && arguments.containsKey(argName)) {
                matcher.appendReplacement(title, "")
                title.append(arguments[argName].toString())
            } else {
                throw IllegalArgumentException(
                    "Could not find $argName in $arguments to fill label $label"
                )
            }
        }
        matcher.appendTail(title)
        return title.toString()
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