package com.example.employeetimefixation.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SocialModel(
    val link: String?,
    val text: String?,
    val icon: SocialsIcons?
): Parcelable
