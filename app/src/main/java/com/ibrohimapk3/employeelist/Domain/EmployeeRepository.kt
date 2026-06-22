package com.ibrohimapk3.employeelist.Domain

interface EmployeeRepository {
    fun getEmployees(): List<Employee>

}