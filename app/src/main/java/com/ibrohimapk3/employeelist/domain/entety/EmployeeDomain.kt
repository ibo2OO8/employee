package com.ibrohimapk3.employeelist.domain.entety

data class EmployeeDomain(
    val id: String,
    val firstName : String,
    val lastName: String,
    val email: String,
    val phone: String,
    val department: String,
    val position: String,
    val image: String?
)