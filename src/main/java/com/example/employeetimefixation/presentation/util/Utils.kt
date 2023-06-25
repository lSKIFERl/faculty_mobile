package com.example.employeetimefixation.presentation.util

import android.icu.text.SimpleDateFormat
import java.sql.Time
import java.sql.Timestamp
import java.util.*

fun Timestamp.convertToString(): String {
    return SimpleDateFormat("HH:mm", Locale("ru", "RU")).format(this)
}

fun <T> List<T>.nonNullCount(): Int {
    return count { it != null }
}

fun <T> List<T>.nullCount(): Int {
    return count { it == null }
}

fun List<Timestamp?>.lastMinusNow(): Long {
    val index = indexOfLast {
        it != null
    }
    return if(index % 2 != 0) {
        System.currentTimeMillis() - (get(index)?.time ?: 0)
    } else {
        0
    }
}