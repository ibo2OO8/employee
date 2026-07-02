package com.ibrohimapk3.employeelist.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ibrohimapk3.employeelist.R
import com.ibrohimapk3.employeelist.presentation.viewmodel.AboutEmployeeViewModel
import com.ibrohimapk3.employeelist.presentation.viewmodel.EmployeesListViewModel
import org.koin.androidx.compose.getViewModel

@Composable
private fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF70B4EC))
            .padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
    ) {
        IconButton(
            onClick = {},
            modifier = Modifier
                .padding(top = 30.dp, start = 7.dp)
                .size(40.dp)

        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "img",
                modifier = Modifier
                    .size(40.dp)
            )
        }
        Text(
            modifier = Modifier.padding(top = 30.dp, bottom = 20.dp, start = 20.dp, end = 20.dp),
            color = Color.White,
            fontSize = 28.sp,
            text = "Профил сотрудника"
        )
    }
}

@Composable
fun AboutEmployee(
    id: String
) {
    val viewModel: AboutEmployeeViewModel = getViewModel()
    val employee by viewModel.employee.collectAsState()

    LaunchedEffect(id) {
        viewModel.loadEmployee(id)
    }

    Column {
        TopBar()
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(50.dp)),
                model = employee.image,
                contentDescription = "photo",

                )
            Text(text = employee.firstName, fontSize = 35.sp, fontWeight = FontWeight.Bold)
            Text(text = "Должность:${employee.position}", fontSize = 22.sp)
            Text(text = "Отдел:${employee.department}", fontSize = 22.sp)
            Text(text = "Email:${employee.email}", fontSize = 22.sp)
            Text(text = "Телефон:${employee.phone}", fontSize = 22.sp)

        }
    }
}
