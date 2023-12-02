package com.jvrni.features.details

import androidx.navigation.NavGraphBuilder
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jvrni.core.domain.models.User
import com.jvrni.core.navigation.Destination
import com.jvrni.core.navigation.Router
import com.jvrni.core.navigation.extensions.composable

fun NavGraphBuilder.detailsGraph(router: Router) {
    composable(Destination.Details) { navBackStackEntry ->
        // Creating gson object
        val gson: Gson = GsonBuilder().create()
        /* Extracting the user object json from the route */
        val userJson = navBackStackEntry.arguments?.getString(Destination.Details.key)
        // Convert json string to the User data class object
        val user = gson.fromJson(userJson, User::class.java)

        DetailsScreen(user) {
            router.back()
        }
    }
}