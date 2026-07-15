package com.ibrohimapk3.employeelist.presentation.viewmodel
import androidx.lifecycle.ViewModel
import com.ibrohimapk3.employeelist.domain.usecase.auth.IsLoggedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class IsLoggedViewModel(
    private val getIsLoggedUseCase: IsLoggedUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(false)
    val state = _state.asStateFlow()

    fun getIsLogged(){
        _state.value = getIsLoggedUseCase()
    }
}