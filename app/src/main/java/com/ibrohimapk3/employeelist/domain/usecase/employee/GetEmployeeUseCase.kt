package com.ibrohimapk3.employeelist.domain.usecase.employee

import com.ibrohimapk3.employeelist.domain.repository.EmployeeRepository
import com.ibrohimapk3.employeelist.presentation.model.Employee
import kotlinx.coroutines.flow.Flow

class GetEmployeeUseCase(
    private val employeeRepository: EmployeeRepository
) {
    operator fun invoke(): Flow<List<Employee>> {
        return employeeRepository.getEmployees()
    }
}