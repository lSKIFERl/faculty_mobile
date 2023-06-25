package com.example.employeetimefixation.presentation.ui.productivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.employeetimefixation.presentation.model.CandidateModel
import com.example.employeetimefixation.presentation.model.TableItemModel
import java.sql.Time
import java.sql.Timestamp
import java.util.*

class ProductivityViewModel : ViewModel() {

    private val mutableCandidateList: MutableLiveData<List<CandidateModel>> = MutableLiveData()

    val candidatesList: LiveData<List<CandidateModel>>
        get() = mutableCandidateList

    init {
        getCandidatesList()
    }

    private fun getCandidatesList() {
        mutableCandidateList.value = listOf(
            CandidateModel(
                0,
                197,
                "Роман",
                "DT",
                Timestamp(Time(18,0,0).time),
                2
            ),
            CandidateModel(
                0,
                195,
                "Александра",
                "DT",
                Timestamp(Time(20,0,0).time),
                1
            ),
            CandidateModel(
                0,
                123,
                "Егор",
                "C",
                Timestamp(Time(11,30,0).time),
                0
            ),
            CandidateModel(
                0,
                14,
                "Лера",
                "C",
                Timestamp(Time(22,30,0).time),
                0
            ),
            CandidateModel(
                0,
                54,
                "Михаил",
                "TRN",
                Timestamp(Time(17,30,0).time),
                0
            ),
        )
    }
}