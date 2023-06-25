package com.example.employeetimefixation.presentation.ui.timetable

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.employeetimefixation.presentation.model.AttentionLevel
import com.example.employeetimefixation.presentation.model.TableItemModel

class TimeTableViewModel : ViewModel() {

    private val mutableEmployeeList: MutableLiveData<List<TableItemModel>> = MutableLiveData()

    val employeeList: LiveData<List<TableItemModel>>
        get() = mutableEmployeeList

    init {
        getEmployeeList()
    }

    private fun getEmployeeList() {
        mutableEmployeeList.value = listOf(TableItemModel(
            0,
            "197",
            "Роман",
        "DT",
        "10:00",
        "18:00",
        true,
        AttentionLevel.URGENT),
            TableItemModel(
                0,
                "14",
                "Лера",
                "C",
                "17:00",
                "22:00",
                false,
                AttentionLevel.WARNING),
        TableItemModel(
            0,
            "195",
            "Александра",
            "DT",
            "11:00",
            "20:00",
            true,
            AttentionLevel.WARNING),
            TableItemModel(
                0,
                "123",
                "Егор",
                "C",
                "11:00",
                "16:00",
                false,
                AttentionLevel.URGENT),
            TableItemModel(
                0,
                "18",
                "Марина",
                "K",
                "08:00",
                "12:00",
                true,
                AttentionLevel.OK),
            TableItemModel(
                0,
                "145",
                "Никита",
                "K",
                "12:00",
                "21:00",
                false,
                AttentionLevel.OK),
            TableItemModel(
                0,
                "68",
                "Михаил",
                "FF",
                "10:00",
                "17:30",
                false,
                AttentionLevel.OK),
            TableItemModel(
                0,
                "79",
                "Иван",
                "TR",
                "10:00",
                "14:00",
                true,
                AttentionLevel.OK),
            TableItemModel(
                0,
                "54",
                "Михаил",
                "TRN",
                "10:00",
                "17:00",
                false,
                AttentionLevel.WARNING),
            TableItemModel(
                0,
                "100",
                "Алексей",
                "DT",
                "16:00",
                "22:00",
                false,
                AttentionLevel.WARNING)
        )
    }


    fun setFilterOn(item: Boolean) {

    }
}