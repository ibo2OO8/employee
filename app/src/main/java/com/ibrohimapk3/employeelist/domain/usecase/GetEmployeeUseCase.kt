package com.ibrohimapk3.employeelist.domain.usecase

import android.util.Log
import com.ibrohimapk3.employeelist.domain.entety.EmployeeDomain
import com.ibrohimapk3.employeelist.domain.repository.EmployeeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class GetEmployeeUseCase(
    private val employeeRepository: EmployeeRepository
) {
     operator fun invoke(): Flow<List<EmployeeDomain>> {
         return employeeRepository.getEmployees()
             .onEach { list ->
                 Log.d("logInDao3" , list.toString())
             }
    }
}