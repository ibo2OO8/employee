package com.ibrohimapk3.employeelist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrohimapk3.employeelist.data.EmployeeRepositoryImpl
import com.ibrohimapk3.employeelist.data.local.dao.EmployeeDao
import com.ibrohimapk3.employeelist.data.remote.RetrofitInstance
import com.ibrohimapk3.employeelist.domain.usecase.GetEmployeeUseCase
import com.ibrohimapk3.employeelist.presentation.Employee
import com.ibrohimapk3.employeelist.presentation.mapper.toEmployee

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EmployeesListViewModel(
    private val getEmployeeUseCase: GetEmployeeUseCase
) : ViewModel() {
    private val _listOfEmployee = MutableStateFlow<List<Employee>>(emptyList())
    val listOfEmployee = _listOfEmployee.asStateFlow()

    init {
        viewModelScope.launch {
            getEmployeeUseCase.invoke().collect { list ->
                _listOfEmployee.value = list.map { it.toEmployee() }
            }
        }
    }
}