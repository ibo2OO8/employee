package com.ibrohimapk3.employeelist.domain.usecase.employee

import com.ibrohimapk3.employeelist.domain.repository.EmployeeRepository

class RefreshUseCase(
    private val employeeRepository: EmployeeRepository
) {
    suspend operator fun invoke() {
        return employeeRepository.refresh()
    }
}