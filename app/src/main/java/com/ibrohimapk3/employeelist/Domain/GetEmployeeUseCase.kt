package com.ibrohimapk3.employeelist.Domain

class GetEmployeeUseCase(private val employeeRepository: EmployeeRepository): EmployeeRepository {
    override fun getEmployees(): List<Employee> {
        return employeeRepository.getEmployees()
    }
}