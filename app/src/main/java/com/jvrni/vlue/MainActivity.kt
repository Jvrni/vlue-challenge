package com.jvrni.vlue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jvrni.core.designsystem.theme.VlueChallengeTheme
import com.jvrni.core.navigation.Destination
import com.jvrni.core.navigation.Router
import com.jvrni.features.home.homeGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VlueChallengeTheme {
                val navController = rememberNavController()
                val router = Router(navController)

                NavHost(
                    navController,
                    startDestination = Destination.Home.route
                ) {
                    homeGraph(router)
                }
            }
        }
    }
}
