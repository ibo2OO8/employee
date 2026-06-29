package com.ibrohimapk3.employeelist.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import com.ibrohimapk3.employeelist.data.EmployeeRepositoryImpl
import com.ibrohimapk3.employeelist.data.remote.ApiService
import com.ibrohimapk3.employeelist.data.remote.RetrofitInstance
import com.ibrohimapk3.employeelist.presentation.screens.EmployeeList

import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column() {
                EmployeeList()
            }
        }
    }
}
