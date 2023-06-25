package com.example.employeetimefixation.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TableItemModel(
    val personalNumber: Int,
    val number: String,
    val name: String,
    val position: String,
    val timeFrom: String,
    val timeTo: String,
    val brake: Boolean,
    val attention: AttentionLevel
) : Parcelable

