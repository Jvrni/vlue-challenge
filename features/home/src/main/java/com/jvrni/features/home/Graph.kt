package com.jvrni.features.home

import android.net.Uri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jvrni.core.navigation.Destination
import com.jvrni.core.navigation.Router
import com.jvrni.core.navigation.extensions.composable

fun NavGraphBuilder.homeGraph(router: Router) {
    composable(Destination.Home) {
        val viewModel = hiltViewModel<HomeViewModel>()

        HomeScreen(viewModel) { user ->
            val gson: Gson = GsonBuilder().create()
            val userJson = Uri.encode(gson.toJson(user))

            router.navigateTo(
                destination = Destination.Details,
                args = userJson
            )
        }
    }
}