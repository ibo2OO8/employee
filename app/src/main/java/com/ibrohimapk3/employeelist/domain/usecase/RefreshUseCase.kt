package com.ibrohimapk3.employeelist.domain.usecase

import com.ibrohimapk3.employeelist.domain.repository.EmployeeRepository

class RefreshUseCase(
    private val employeeRepository: EmployeeRepository
) {
    operator fun invoke(){
        employeeRepository.refresh()
    }
}