package com.jvrni.core.designsystem.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.jvrni.core.designsystem.theme.custom.DarkThemeColors
import com.jvrni.core.designsystem.theme.custom.DefaultDimens
import com.jvrni.core.designsystem.theme.custom.DefaultRadius
import com.jvrni.core.designsystem.theme.custom.Dimens
import com.jvrni.core.designsystem.theme.custom.LightThemeColors
import com.jvrni.core.designsystem.theme.custom.Radius
import com.jvrni.core.designsystem.theme.custom.VlueColors
import com.jvrni.core.designsystem.theme.custom.black444444
import com.jvrni.core.designsystem.theme.custom.whiteFFFFFF

private val LocalColors = staticCompositionLocalOf<VlueColors> { error("No Colors provided") }
private val LocalDimens = staticCompositionLocalOf<Dimens> { error("No Dimens provided") }
private val LocalRadius = staticCompositionLocalOf<Radius> { error("No Radius provided") }

val Colors: VlueColors
    @Composable
    get() = LocalColors.current

val Dimens: Dimens
    @Composable
    get() = LocalDimens.current

val Radius: Radius
    @Composable
    get() = LocalRadius.current

@Composable
fun VlueChallengeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkThemeColors else LightThemeColors
    val rememberedColors = remember { colors.copy() }.apply { update(colors) }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = if (darkTheme) black444444.toArgb() else whiteFFFFFF.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalDimens provides DefaultDimens,
        LocalRadius provides DefaultRadius
    ) {
        content.invoke()
    }
}