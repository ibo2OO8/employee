package com.ibrohimapk3.employeelist.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ibrohimapk3.employeelist.presentation.screens.AboutEmployee
import com.ibrohimapk3.employeelist.presentation.screens.AuthScreen
import com.ibrohimapk3.employeelist.presentation.screens.ListOfEmployee
import com.ibrohimapk3.employeelist.presentation.viewmodel.IsLoggedViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController, modifier: Modifier = Modifier
) {
    val isLoggedVM: IsLoggedViewModel = getViewModel()
    isLoggedVM.getIsLogged()
    val isLogged by isLoggedVM.state.collectAsState()
    var startDestination: String
    if (!isLogged) startDestination = "authScreen"
    else startDestination = "employeeList"
    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        composable("employeeList") {
            ListOfEmployee(
                onEmployeeListToAboutEmployee = { id ->
                    navController.navigate("aboutEmployee/$id")
                },
                onEmployeeListToAboutAuth = {
                    navController.navigate("authScreen"){
                        popUpTo("employeeList") { inclusive = true }
                    }
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
        composable("authScreen") {
            AuthScreen(modifier, onAuthToListOfEmployee = {
                navController.navigate("employeeList"){
                    popUpTo("authScreen") { inclusive = true }
                }
            })
        }
    }
}