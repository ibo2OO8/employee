package com.ibrohimapk3.employeelist.domain.usecase.auth

import com.ibrohimapk3.employeelist.domain.repository.AuthRepository

class LogOutUseCase(private val authRepository: AuthRepository) {
    operator fun invoke(){
        authRepository.logOut()
    }
}