package com.ibrohimapk3.employeelist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrohimapk3.employeelist.domain.error.AppException
import com.ibrohimapk3.employeelist.domain.usecase.auth.LogOutUseCase
import com.ibrohimapk3.employeelist.domain.usecase.auth.LoginUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val getLoggedUseCase: LoginUseCase,
    private val getLogOutUseCase: LogOutUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(false)
    val state = _state.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val result = getLoggedUseCase(email, password)

                _state.value = result

                _isLoading.value = false
            } catch (e: AppException) {
                _error.emit(e.message.toString())
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun logOut() {
        getLogOutUseCase.invoke()
    }
}