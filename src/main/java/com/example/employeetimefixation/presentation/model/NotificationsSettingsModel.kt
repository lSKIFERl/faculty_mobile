package com.example.employeetimefixation.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NotificationsSettingsModel(
    var sortASC: Boolean = true,
    var showUrgent: Boolean = true,
    var showWarning: Boolean = true,
    var showOk: Boolean = true
): Parcelable
