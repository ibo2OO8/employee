package com.ibrohimapk3.employeelist.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ibrohimapk3.employeelist.presentation.screens.AboutEmployee
import com.ibrohimapk3.employeelist.presentation.screens.EmployeeList

@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "employeeList"
    ) {
        composable("employeeList") {
            EmployeeList(
                onEmployeeListToAboutEmployee = {
                    navController.navigate("aboutEmployee")
                }
            )
        }
        composable("aboutEmployee"){
            AboutEmployee("daa","sdf" , "fs" , "dssa" , "daasd" , 222)
        }
    }
}