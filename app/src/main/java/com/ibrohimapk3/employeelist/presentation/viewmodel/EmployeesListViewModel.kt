package com.ibrohimapk3.employeelist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrohimapk3.employeelist.domain.error.AppException
import com.ibrohimapk3.employeelist.domain.usecase.employee.GetEmployeeUseCase
import com.ibrohimapk3.employeelist.domain.usecase.employee.RefreshUseCase
import com.ibrohimapk3.employeelist.presentation.model.Employee
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
class EmployeesListViewModel(
    private val getEmployeeUseCase: GetEmployeeUseCase,
    private val refreshUseCase: RefreshUseCase
) : ViewModel() {
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()
    private val _listOfEmployee = MutableStateFlow<List<Employee>>(emptyList())
    val listOfEmployee = _listOfEmployee.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            getEmployeeUseCase.invoke().collect { list ->
                _listOfEmployee.value = list.map { it }
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                refreshUseCase()
            } catch (e: AppException) {
                _error.emit(e.message.toString())
            } finally {
                _isRefreshing.value = false
            }
        }
    }
}