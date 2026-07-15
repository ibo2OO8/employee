package com.ibrohimapk3.employeelist.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.ibrohimapk3.employeelist.presentation.navigation.AppNavGraph
import com.ibrohimapk3.employeelist.presentation.screens.AuthScreen
import com.ibrohimapk3.employeelist.presentation.theme.SkyColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.statusBarColor = SkyColor.toArgb()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Column() {
                    val navController = rememberNavController()
                    AppNavGraph(
                        navController = navController,
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(Color.White)
                    )
                }
            }
        }
    }
}