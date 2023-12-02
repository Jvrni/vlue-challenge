package com.jvrni.core.navigation

enum class Destination(val route: String, val key: String) {
    Home("home", ""),
    Details("details/{user}", "user"),
}