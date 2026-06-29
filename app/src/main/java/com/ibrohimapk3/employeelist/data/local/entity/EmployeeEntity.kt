package com.ibrohimapk3.employeelist.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EmployeesList")
data class EmployeeEntity(
    @PrimaryKey
    val id: String,
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val department: String? = null,
    val position: String? = null,
    val image: String? = null,
)