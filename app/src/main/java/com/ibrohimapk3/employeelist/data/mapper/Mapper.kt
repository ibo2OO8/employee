package com.ibrohimapk3.employeelist.data.mapper

import com.ibrohimapk3.employeelist.data.local.entity.EmployeeEntity
import com.ibrohimapk3.employeelist.data.remote.EmployeesDto
import com.ibrohimapk3.employeelist.domain.entety.EmployeeDomain

fun EmployeeEntity.toEmployee(): EmployeeDomain {
    return EmployeeDomain(
        id = this.id,
        this.firstName.toString(),
        this.lastName.toString(),
        email.toString(),
        phone.toString(),
        department?.random().toString(),
        position?.random().toString(),
        image,
    )
}

fun EmployeesDto.toEmployeeEntity(): EmployeeEntity {
    return EmployeeEntity(
        id = this.login.uuid,
        name.first,
        name.last,
        email,
        phone,
        department.random(),
        position.random(),
        picture.medium
    )
}

var department = listOf("IT", "Finance", "Sales", "Hr")
var position = listOf("Manager", "Backend", "Frontend", "android developer")