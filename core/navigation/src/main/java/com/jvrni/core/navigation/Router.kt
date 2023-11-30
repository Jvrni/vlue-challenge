package com.jvrni.core.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

class Router(private val navController: NavHostController) {

    fun navigateTo(destination: Destination, resetStack: Boolean, popUpStartDestination: Boolean) {
        navController.navigate(destination.route) {
            if (resetStack) popUpTo(0) { saveState = true } // reset stack
            else if (popUpStartDestination) popUpTo(navController.graph.findStartDestination().id) { saveState = true }

            launchSingleTop = true
            restoreState = true
        }
    }

    fun back() { navController.popBackStack() }
}