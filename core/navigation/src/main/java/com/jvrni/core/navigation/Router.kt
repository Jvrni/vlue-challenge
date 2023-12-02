package com.jvrni.core.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

class Router(private val navController: NavHostController) {

    fun navigateTo(
        destination: Destination,
        args: String = ""
    ) {
        val route = if (args.isNotEmpty()) destination.route.replace(
            oldValue = "{${destination.key}}",
            newValue = args
        )
        else destination.route

        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }

            launchSingleTop = true
            restoreState = true
        }
    }

    fun back() { navController.popBackStack() }
}