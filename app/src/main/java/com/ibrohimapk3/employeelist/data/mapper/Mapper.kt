package com.ibrohimapk3.employeelist.data.mapper

import com.ibrohimapk3.employeelist.data.local.entity.EmployeeEntity
import com.ibrohimapk3.employeelist.data.remote.EmployeesDto
import com.ibrohimapk3.employeelist.presentation.model.Employee

private val departmentL = listOf("IT", "Finance", "Sales", "Hr")
private val positionL = listOf("Manager", "Backend", "Frontend", "android developer")
fun EmployeeEntity.toEmployee(): Employee {
    return Employee(
        id = this.id,
        firstName = this.firstName.toString(),
        lastName = this.lastName.toString(),
        email = email.toString(),
        phone = phone.toString(),
        department = department.toString(),
        position = position.toString(),
        image = image,
    )
}
fun EmployeesDto.toEmployeeEntity(): EmployeeEntity {
    return EmployeeEntity(
        id = this.login.uuid,
        name.first,
        name.last,
        email = email,
        phone = phone,
        department = departmentL.random(),
        position = positionL.random(),
        image = picture.large
    )
}