package com.ibrohimapk3.employeelist.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ibrohimapk3.employeelist.presentation.screens.AboutEmployee
import com.ibrohimapk3.employeelist.presentation.screens.ListOfEmployee

@Composable
fun AppNavGraph(
    navController: NavHostController, modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController, startDestination = "employeeList"
    ) {
        composable("employeeList") {
            ListOfEmployee(
                onEmployeeListToAboutEmployee = { id ->
                    navController.navigate("aboutEmployee/$id")
                },
                modifier = modifier
            )

        }
        composable(
            route = "aboutEmployee/{employeeId}"
        ) { backStackEntry ->

            val employeeId = backStackEntry.arguments?.getString("employeeId")
            employeeId?.let {
                AboutEmployee(
                    id = it, navController = navController,
                    modifier = modifier
                )
            }
        }
    }
}