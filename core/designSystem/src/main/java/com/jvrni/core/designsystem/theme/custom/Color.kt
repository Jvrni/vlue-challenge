package com.jvrni.core.designsystem.theme.custom

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

val primary = Color(0xFFBB86FC)

val black000000 = Color(0xFF000000)
val black444444 = Color(0xFF444444)
val whiteFFFFFF = Color(0xFFFFFFFF)


val LightThemeColors = VlueColors(
    primary = primary,
    background = whiteFFFFFF,
    text = black000000,
    isDark = false
)

val DarkThemeColors = VlueColors(
    primary = primary,
    background = black444444,
    text = whiteFFFFFF,
    isDark = true
)

@Stable
class VlueColors(
    primary: Color,
    background: Color,
    text: Color,
    isDark: Boolean
) {
    var primary = mutableStateOf(primary).value
        private set
    var background = mutableStateOf(background).value
        private set
    var text = mutableStateOf(text).value
        private set
    var isDark = mutableStateOf(isDark).value
        private set

    fun copy(): VlueColors = VlueColors(
        primary = primary,
        background = background,
        text = text,
        isDark = isDark
    )

    fun update(other: VlueColors) {
        primary = other.primary
        background = other.background
        text = other.text
        isDark = other.isDark
    }
}