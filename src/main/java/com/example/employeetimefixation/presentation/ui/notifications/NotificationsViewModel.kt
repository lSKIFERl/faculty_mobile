package com.example.employeetimefixation.presentation.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.employeetimefixation.presentation.model.AttentionLevel
import com.example.employeetimefixation.presentation.model.NotificationModel
import java.sql.Timestamp
import java.util.*

class NotificationsViewModel : ViewModel() {

    private val mutableNotificationList: MutableLiveData<List<NotificationModel>> = MutableLiveData()

    val notificationList: LiveData<List<NotificationModel>>
        get() = mutableNotificationList

    init {
        getNotifications()
    }

    private fun getNotifications() {
        mutableNotificationList.value = listOf(
            NotificationModel(
                0,
                197,
                "Роман",
                "DT",
                "Опоздал на смену (12 мин.)",
                AttentionLevel.URGENT,
                Timestamp(Date(System.currentTimeMillis()).time)
            ),
            NotificationModel(
                0,
                123,
                "Егор",
                "С",
                "Опоздал на смену (22 мин.)",
                AttentionLevel.URGENT,
                Timestamp(Date(System.currentTimeMillis()).time + 100)
            ),
            NotificationModel(
                0,
                123,
                "Александра",
                "DT",
                "Вышел раньше (2 мин.)",
                AttentionLevel.WARNING,
                Timestamp(Date(System.currentTimeMillis()).time + 100)
            ),
            NotificationModel(
                0,
                14,
                "Лера",
                "C",
                "Вышел с перерыва раньше (3 мин.)",
                AttentionLevel.WARNING,
                Timestamp(Date(System.currentTimeMillis()).time + 100)
            ),
            NotificationModel(
                0,
                145,
                "Никита",
                "Л",
                "Вышел на смену",
                AttentionLevel.OK,
                Timestamp(Date(System.currentTimeMillis()).time + 100)
            ),
            NotificationModel(
                0,
                54,
                "Михаил",
                "TRN",
                "Не было перерыва 03:21",
                AttentionLevel.WARNING,
                Timestamp(Date(System.currentTimeMillis()).time + 100)
            ),
            NotificationModel(
                0,
                79,
                "Иван",
                "TR",
                "Вышел на смену",
                AttentionLevel.OK,
                Timestamp(Date(System.currentTimeMillis()).time + 100)
            )
        )
    }
}