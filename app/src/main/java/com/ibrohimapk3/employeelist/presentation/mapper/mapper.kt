package com.ibrohimapk3.employeelist.presentation.mapper

import com.ibrohimapk3.employeelist.domain.entety.EmployeeDomain
import com.ibrohimapk3.employeelist.presentation.model.Employee

fun EmployeeDomain.toEmployee(): Employee {
    return Employee(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        phone = this.phone,
        department = this.department,
        position = this.position,
        image = this.image
    )
}