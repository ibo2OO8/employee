package com.ibrohimapk3.employeelist.domain.usecase

import com.ibrohimapk3.employeelist.domain.entety.EmployeeDomain
import com.ibrohimapk3.employeelist.domain.repository.EmployeeRepository

class GetAboutEmployeeUseCase(
    private val employeeRepository: EmployeeRepository
) {
     suspend operator fun invoke(): EmployeeDomain {
        return employeeRepository.getAboutEmployee()
    }
}