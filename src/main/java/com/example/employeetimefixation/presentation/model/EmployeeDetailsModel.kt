package com.example.employeetimefixation.presentation.model

import android.os.Parcelable
import android.util.TimeUtils
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp

@Parcelize
data class EmployeeDetailsModel(
    val personalNumber: Int,
    val number: Int?,
    val todayPositions: List<String>?,

    val firstName: String?,
    val lastName: String?,
    val patronymic: String?,

    val negativeNotes: Int?,
    val positiveNotes: Int?,
    val kln: Float?,

    val availablePositions: List<String>?,
    val socialNets: List<SocialModel>?,

    val productivity: Float?,

    val timeFromSchedule: Timestamp?,
    val timeToSchedule: Timestamp?,
    val timeFromFact: Timestamp?,
    val timeToFact: Timestamp?,
    val breaks: List<Timestamp>?
): Parcelable
