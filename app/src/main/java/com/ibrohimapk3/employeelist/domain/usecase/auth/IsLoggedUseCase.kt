package com.ibrohimapk3.employeelist.domain.usecase.auth

import com.ibrohimapk3.employeelist.domain.repository.AuthRepository

class IsLoggedUseCase(private val authRepository: AuthRepository) {
    operator fun invoke(): Boolean{
        return authRepository.isLogged()
    }
}