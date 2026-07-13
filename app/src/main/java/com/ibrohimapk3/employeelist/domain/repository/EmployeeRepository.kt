package com.ibrohimapk3.employeelist.domain.repository

import com.ibrohimapk3.employeelist.presentation.model.Employee
import kotlinx.coroutines.flow.Flow

interface EmployeeRepository {
    suspend fun refresh()
    fun getEmployees(): Flow<List<Employee>>
    suspend fun getAboutEmployeeById(id : String): Employee

}