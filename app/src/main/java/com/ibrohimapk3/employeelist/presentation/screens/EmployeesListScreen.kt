package com.ibrohimapk3.employeelist.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ibrohimapk3.employeelist.presentation.viewmodel.EmployeesListViewModel
import com.ibrohimapk3.employeelist.presentation.theme.SkyColor
import com.ibrohimapk3.employeelist.presentation.viewmodel.LoginViewModel
import com.ibrohimapk3.employeelist.presentation.viewmodel.ShowDialogViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListOfEmployee(
    onEmployeeListToAboutEmployee: (String) -> Unit,
    viewModel: EmployeesListViewModel = getViewModel(),
    modifier: Modifier = Modifier,
    onEmployeeListToAboutAuth: () -> Unit,
    viewModelAuth: LoginViewModel = getViewModel(),
    viewModelShowDialog: ShowDialogViewModel = getViewModel()
) {
    val context = LocalContext.current
    val listOfEmployee by viewModel.listOfEmployee.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val pullRefreshState = rememberPullToRefreshState()
    var searchText by remember { mutableStateOf("") }
    val showDialog = viewModelShowDialog.showDialog.collectAsState()
    val filteredList = listOfEmployee.filter {
        it.firstName.contains(searchText, ignoreCase = true)
    }

    Column(modifier = modifier.background(Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        bottomStart = 20.dp, bottomEnd = 20.dp
                    )
                )
                .background(SkyColor)
                .padding(10.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .padding(
                            top = 15.dp,
                            bottom = 20.dp,
                            start = 20.dp,
                            end = 20.dp,
                        )
                        .weight(1F), color = Color.White, fontSize = 28.sp, text = "Сотрудники"
                )
                IconButton(
                    onClick = {
                        viewModelShowDialog.openDialog()
                    }, modifier = Modifier
                        .padding(end = 10.dp)
                        .size(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "img",
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
            TextField(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                value = searchText,
                maxLines = 1,
                placeholder = {
                    Text(text = "Поиск сотрудника...")
                },
                onValueChange = { searchText = it },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
            )
        }
        if (showDialog.value) {
            ShowDialog(viewModelShowDialog, onEmployeeListToAboutAuth)
        }
        LaunchedEffect(Unit) {
            viewModel.error.collect {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
        PullToRefreshBox(state = pullRefreshState, isRefreshing = isRefreshing, onRefresh = {
            viewModel.refresh()

        }, indicator = {
            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                PullToRefreshDefaults.Indicator(
                    state = pullRefreshState, isRefreshing = isRefreshing, color = SkyColor
                )
            }
        }) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                items(filteredList) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 7.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 7.dp, vertical = 10.dp)
                            .shadow(10.dp, RoundedCornerShape(8.dp))
                            .background(Color.White)
                            .clickable {
                                onEmployeeListToAboutEmployee(it.id)
                            },
                    ) {
                        Box(
                            modifier = Modifier.size(120.dp), contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(RoundedCornerShape(50.dp)),
                                model = it.image,
                                contentDescription = "photo",
                            )

                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                fontSize = 22.sp,
                                color = Color.Black,
                                text = it.firstName,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                fontSize = 20.sp,
                                color = Color.Blue,
                                text = it.department,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                            Text(fontSize = 19.sp, color = Color.Blue, text = it.position)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShowDialog(
    viewModel: ShowDialogViewModel = getViewModel(),
    onEmployeeListToAboutAuth: () -> Unit
) {

    AlertDialog(
        containerColor = Color.White,
        onDismissRequest = { },
        title = { Text("Подтверждение") },
        text = { Text("Перейти на следующий экран?") },
        confirmButton = {
            TextButton(
                onClick = {
                    viewModel.closeDialog()
                    onEmployeeListToAboutAuth()
                }
            ) {
                Text("Да", color = SkyColor)
            }
        },
        dismissButton = {
            TextButton(
                onClick = { viewModel.closeDialog() }
            ) {
                Text("Нет", color = SkyColor)
            }
        }
    )
}