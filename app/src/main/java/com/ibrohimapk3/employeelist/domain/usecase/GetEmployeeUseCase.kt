package com.ibrohimapk3.employeelist.domain.usecase

import com.ibrohimapk3.employeelist.domain.entety.EmployeeDomain
import com.ibrohimapk3.employeelist.domain.repository.EmployeeRepository
import kotlinx.coroutines.flow.Flow

class GetEmployeeUseCase(
    private val employeeRepository: EmployeeRepository
) {
     operator fun invoke(): Flow<List<EmployeeDomain>> {
        return employeeRepository.getEmployees()
    }
}