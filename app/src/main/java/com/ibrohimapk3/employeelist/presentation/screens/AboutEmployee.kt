package com.ibrohimapk3.employeelist.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibrohimapk3.employeelist.R

@Composable
private fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF70B4EC))
            .padding(10.dp)

    ) {
        IconButton(
            onClick = {}, modifier = Modifier
                .padding(top = 30.dp, start = 7.dp)
                .size(40.dp)
                .background(Color.Blue)
        ) {}
        Text(
            modifier = Modifier.padding(top = 30.dp, bottom = 20.dp, start = 20.dp, end = 20.dp),
            color = Color.White,
            fontSize = 28.sp,
            text = "Профил сотрудника"
        )
    }
}

@Composable
fun AboutEmployee(name: String, job: String, department: String, email: String, number: Int) {
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
    ) {
        Image(
            painterResource(R.drawable.ic_launcher_background), contentDescription = "img",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(120.dp)
        )
        Text(text = name, fontSize = 35.sp, fontWeight = FontWeight.Bold)

        Text(text = "Должность:$job", fontSize = 22.sp)
        Text(text = "Отдел:$department", fontSize = 22.sp)
        Text(text = "Email:$email", fontSize = 22.sp)
        Text(text = "Телефон:$number", fontSize = 22.sp)
    }
}
