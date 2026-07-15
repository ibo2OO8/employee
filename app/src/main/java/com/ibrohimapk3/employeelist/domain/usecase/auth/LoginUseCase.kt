package com.ibrohimapk3.employeelist.domain.usecase.auth

import com.ibrohimapk3.employeelist.domain.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) : Boolean{
        return  authRepository.login(email , password)
    }
}