package com.example.employeetimefixation.presentation.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.employeetimefixation.presentation.model.EmployeeDetailsModel

class EmployeeDetailViewModel: ViewModel() {
    private val mutableEmployeeDetails: MutableLiveData<EmployeeDetailsModel> = MutableLiveData()

    val employeeDetails: LiveData<EmployeeDetailsModel>
        get() = mutableEmployeeDetails

    fun getDetailsById(id: Int) {

    }
}