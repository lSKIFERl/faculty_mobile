package com.example.employeetimefixation.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp

@Parcelize
data class NotificationModel(
    val personalNumber: Int?,
    val number: Int?,
    val name: String?,
    val position: String?,
    val message: String?,
    val attentionLevel: AttentionLevel,
    val created: Timestamp
): Parcelable