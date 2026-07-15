package com.ibrohimapk3.employeelist.di

import com.ibrohimapk3.employeelist.data.repositoryImpl.EmployeeRepositoryImpl
import com.ibrohimapk3.employeelist.data.local.MainDb
import com.ibrohimapk3.employeelist.data.local.dao.EmployeeDao
import com.ibrohimapk3.employeelist.data.remote.RetrofitInstance
import com.ibrohimapk3.employeelist.data.repositoryImpl.AuthRepositoryImpl
import com.ibrohimapk3.employeelist.domain.repository.AuthRepository
import com.ibrohimapk3.employeelist.domain.repository.EmployeeRepository
import com.ibrohimapk3.employeelist.domain.usecase.auth.IsLoggedUseCase
import com.ibrohimapk3.employeelist.domain.usecase.auth.LogOutUseCase
import com.ibrohimapk3.employeelist.domain.usecase.auth.LoginUseCase
import com.ibrohimapk3.employeelist.domain.usecase.employee.GetAboutEmployeeUseCase
import com.ibrohimapk3.employeelist.domain.usecase.employee.GetEmployeeUseCase
import com.ibrohimapk3.employeelist.domain.usecase.employee.RefreshUseCase
import com.ibrohimapk3.employeelist.presentation.viewmodel.AboutEmployeeViewModel
import com.ibrohimapk3.employeelist.presentation.viewmodel.EmployeesListViewModel
import com.ibrohimapk3.employeelist.presentation.viewmodel.IsLoggedViewModel
import com.ibrohimapk3.employeelist.presentation.viewmodel.LoginViewModel
import com.ibrohimapk3.employeelist.presentation.viewmodel.ShowDialogViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var appModule = module {
    single { RetrofitInstance.api }

    single<MainDb> {
        MainDb.getDb(androidContext())
    }

    single<EmployeeDao> { get<MainDb>().getDao() }

    single<EmployeeRepository> {
        EmployeeRepositoryImpl(get(), get())
    }
    single<AuthRepository> {
        AuthRepositoryImpl()
    }

    single {
        GetAboutEmployeeUseCase(get())
    }
    single {
        GetEmployeeUseCase(get())
    }
    single {
        RefreshUseCase(get())
    }
    single {
        IsLoggedUseCase(get())
    }
    single {
        LoginUseCase(get())
    }
    single {
        LogOutUseCase(get())
    }
    viewModel {
        EmployeesListViewModel(get(), get())
    }

    viewModel {
        LoginViewModel(get(), get())
    }
    viewModel {
        IsLoggedViewModel(get())
    }
    viewModel {
        AboutEmployeeViewModel(get())
    }
    viewModel {
        ShowDialogViewModel()
    }
}