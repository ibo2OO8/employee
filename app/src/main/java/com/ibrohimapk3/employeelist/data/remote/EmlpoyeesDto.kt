package com.ibrohimapk3.employeelist.data.remote

data class ResponseUser(
    val results: List<EmployeesDto>
)
data class EmployeesDto(
    var login : Login,
    var name: Name,
    val email: String,
    val phone: String,
    var picture: Picture
)
data class Name(val first: String, val last: String)
data class Picture(val large: String, val medium: String)
data class Login(val uuid:String)