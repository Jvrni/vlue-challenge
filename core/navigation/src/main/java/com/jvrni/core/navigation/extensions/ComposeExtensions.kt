package com.jvrni.core.navigation.extensions

import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.compose.animation.*
import androidx.compose.animation.core.TweenSpec
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jvrni.core.navigation.Destination


@Composable
fun SystemsBarsVisible(isVisible: Boolean) {
    val activity = LocalContext.current as ComponentActivity

    LaunchedEffect(key1 = Unit) {
        if (isVisible) {
            WindowCompat.setDecorFitsSystemWindows(activity.window, true)
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        } else {
            WindowCompat.setDecorFitsSystemWindows(activity.window, false)

            activity.window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }
}

fun NavGraphBuilder.composable(
    destination: Destination,
    systemsBarsVisible: Boolean = false,
    animation: MutableState<Boolean> = mutableStateOf(true),
    content: @Composable (NavBackStackEntry) -> Unit
) = this.apply {
    composable(destination.route) {
        AnimatedVisibility(
            visible = animation.value,
            enter = fadeIn(animationSpec = TweenSpec(800)),
            exit = fadeOut(animationSpec = TweenSpec(800))
        ) {
            SystemsBarsVisible(systemsBarsVisible)
            content(it)
        }
    }
}
