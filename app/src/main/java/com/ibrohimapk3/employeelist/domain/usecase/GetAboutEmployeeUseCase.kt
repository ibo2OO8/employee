package com.ibrohimapk3.employeelist.domain.usecase

import com.ibrohimapk3.employeelist.domain.repository.EmployeeRepository
import com.ibrohimapk3.employeelist.presentation.model.Employee

class GetAboutEmployeeUseCase(
    private val employeeRepository: EmployeeRepository
) {
    suspend operator fun invoke(id: String): Employee {
        return employeeRepository.getAboutEmployeeById(id)
    }
}