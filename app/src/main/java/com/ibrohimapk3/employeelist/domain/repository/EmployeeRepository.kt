package com.ibrohimapk3.employeelist.domain.repository

import com.ibrohimapk3.employeelist.domain.entety.EmployeeDomain
import kotlinx.coroutines.flow.Flow

interface EmployeeRepository {
    fun refresh()
    fun getEmployees(): Flow<List<EmployeeDomain>>
    suspend fun getAboutEmployeeById(): EmployeeDomain

}