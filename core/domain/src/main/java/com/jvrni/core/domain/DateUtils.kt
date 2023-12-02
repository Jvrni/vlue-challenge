package com.jvrni.core.domain

import java.text.SimpleDateFormat
import java.util.Date

fun formatDate(date: Date): String {
    return SimpleDateFormat("MMM dd, YYYY").format(date)
}

fun convertDate(dateString: String): Date {
    return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(dateString)
}