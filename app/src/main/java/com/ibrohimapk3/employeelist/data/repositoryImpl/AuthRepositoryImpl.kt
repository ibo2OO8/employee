package com.ibrohimapk3.employeelist.data.repositoryImpl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.ibrohimapk3.employeelist.domain.repository.AuthRepository
import com.ibrohimapk3.employeelist.domain.error.AppException
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl : AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    override suspend fun login(email: String, password: String): Boolean {
        var res = false
        if(email.isBlank() || password.isBlank())
            throw AppException.InvalidEmailOrPassword()
        try {
            auth.signInWithEmailAndPassword(email, password)
                .await()
            res = true
        } catch (e: Exception) {
            when (e) {
                is FirebaseAuthEmailException -> {
                    throw AppException.InvalidEmailOrPassword()
                }

                is FirebaseAuthInvalidCredentialsException -> {
                    throw AppException.InvalidEmailOrPassword()
                }

                else -> {
                    throw AppException.NetworkException()
                }
            }
            res = false
        }
        return res
    }

    override fun isLogged(): Boolean {
        return auth.currentUser != null
    }

    override fun logOut() {
        auth.signOut()
    }
}