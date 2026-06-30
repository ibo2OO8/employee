package com.ibrohimapk3.employeelist.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.navigation.compose.rememberNavController
import com.ibrohimapk3.employeelist.presentation.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column() {

                val navController = rememberNavController()

                AppNavGraph(navController)
            }
        }
    }
}