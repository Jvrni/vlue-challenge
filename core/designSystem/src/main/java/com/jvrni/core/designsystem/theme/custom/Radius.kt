package com.jvrni.core.designsystem.theme.custom

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Radius(
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val extraLarge: Dp
)

val DefaultRadius = Radius(
    small = 4.dp,
    medium = 8.dp,
    large = 16.dp,
    extraLarge = 24.dp
)