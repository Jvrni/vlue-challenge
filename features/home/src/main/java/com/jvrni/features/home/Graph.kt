package com.jvrni.features.home

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jvrni.core.navigation.Destination
import com.jvrni.core.navigation.Router

fun NavGraphBuilder.homeGraph(router: Router) {
    composable(Destination.Home.route) {
        val viewModel = hiltViewModel<HomeViewModel>()

        HomeScreen()
    }
}