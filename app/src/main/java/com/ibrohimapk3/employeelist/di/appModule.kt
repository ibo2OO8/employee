package com.ibrohimapk3.employeelist.di

import com.ibrohimapk3.employeelist.data.EmployeeRepositoryImpl
import com.ibrohimapk3.employeelist.data.local.MainDb
import com.ibrohimapk3.employeelist.data.local.dao.EmployeeDao
import com.ibrohimapk3.employeelist.data.remote.RetrofitInstance
import com.ibrohimapk3.employeelist.domain.repository.EmployeeRepository
import com.ibrohimapk3.employeelist.domain.usecase.GetAboutEmployeeUseCase
import com.ibrohimapk3.employeelist.domain.usecase.GetEmployeeUseCase
import com.ibrohimapk3.employeelist.domain.usecase.RefreshUseCase
import com.ibrohimapk3.employeelist.presentation.viewmodel.EmployeesListViewModel
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
    single {
        GetAboutEmployeeUseCase(get())
    }
    single {
        GetEmployeeUseCase(get())
    }
    single {
        RefreshUseCase(get())
    }
    viewModel {
        EmployeesListViewModel(get())
    }
}