package com.ibrohimapk3.employeelist.domain.repository

interface AuthRepository {
    suspend fun login(email: String, password: String): Boolean

    fun isLogged(): Boolean

    fun logOut()
}