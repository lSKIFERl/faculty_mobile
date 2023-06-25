package com.example.employeetimefixation.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Time
import java.sql.Timestamp

@Parcelize
data class CandidateModel(
    val personalNumber: Int,
    val number: Int,
    val name: String,
    val position: String,
    val expirationTime: Timestamp,
    val brakes: List<Timestamp?>?,
    var endWork: Boolean,

    val efficiency: Float
) : Parcelable
