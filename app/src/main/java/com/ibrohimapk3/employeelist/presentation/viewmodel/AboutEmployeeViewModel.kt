package com.ibrohimapk3.employeelist.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrohimapk3.employeelist.domain.usecase.GetAboutEmployeeUseCase
import com.ibrohimapk3.employeelist.presentation.mapper.toEmployee
import com.ibrohimapk3.employeelist.presentation.model.Employee
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AboutEmployeeViewModel(
    private val getAboutEmployeeUseCase: GetAboutEmployeeUseCase
) : ViewModel() {

    private val _employee = MutableStateFlow(Employee("", "", "", "", "", "", "", ""))
    val employee = _employee.asStateFlow()

    fun loadEmployee(id: String) {
        viewModelScope.launch {
            val result = getAboutEmployeeUseCase.invoke(id)
                .toEmployee()

            _employee.value = result

            Log.d("VM", "Loaded employee = $result")
        }
    }
}