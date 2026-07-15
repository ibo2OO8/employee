package com.ibrohimapk3.employeelist.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ibrohimapk3.employeelist.presentation.viewmodel.AboutEmployeeViewModel
import com.ibrohimapk3.employeelist.presentation.theme.ImgBorderColor
import com.ibrohimapk3.employeelist.presentation.theme.SkyColor
import com.ibrohimapk3.employeelist.presentation.viewmodel.LoginViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun AboutEmployee(
    navController: NavHostController,
    id: String,
    viewModel: AboutEmployeeViewModel = getViewModel(),
    modifier: Modifier = Modifier,
) {
    val employee by viewModel.employee.collectAsState()
    LaunchedEffect(id) {
        viewModel.loadEmployee(id)
    }

    Column(modifier = modifier.background(Color.White)) {
        TopBar(navController)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 40.dp, start = 25.dp, end = 25.dp, top = 20.dp)
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(10.dp))
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(130.dp)
                    .border(2.dp, ImgBorderColor, CircleShape)
                    .clip(RoundedCornerShape(80.dp)),
                model = employee.image,
                contentDescription = "photo",
            )
            Text(
                modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),
                text = "${employee.firstName}  ${employee.lastName}",
                fontSize = 30.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 22.dp, end = 22.dp, bottom = 12.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(35.dp)
                        .align(Alignment.CenterVertically),
                    imageVector = Icons.Default.Badge,
                    tint = SkyColor,
                    contentDescription = "icon",
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp)
                ) {
                    Text("Должность", color = Color.Gray, fontSize = 22.sp)
                    Text(employee.position, color = Color.Black, fontSize = 25.sp)
                }
            }
            //
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 22.dp, end = 22.dp, top = 3.dp, bottom = 3.dp),
                thickness = 2.dp,
                color = Color.Gray
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 22.dp, end = 22.dp, bottom = 12.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(35.dp)
                        .align(Alignment.CenterVertically),
                    imageVector = Icons.Default.Business,
                    tint = SkyColor,
                    contentDescription = "icon",
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp)
                ) {
                    Text("Отдел", color = Color.Gray, fontSize = 22.sp)
                    Text(employee.department, color = Color.Black, fontSize = 25.sp)
                }
            }
            //
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 22.dp, end = 22.dp, top = 3.dp, bottom = 3.dp),
                thickness = 2.dp,
                color = Color.Gray
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 22.dp, end = 22.dp, bottom = 12.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(35.dp)
                        .align(Alignment.CenterVertically),
                    imageVector = Icons.Default.Email,
                    tint = SkyColor,
                    contentDescription = "icon",
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp)
                ) {
                    Text("Email", color = Color.Gray, fontSize = 22.sp)
                    Text(employee.email, color = Color.Black, fontSize = 25.sp)
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 22.dp, end = 22.dp, top = 3.dp, bottom = 3.dp),
                thickness = 2.dp,
                color = Color.Gray
            )
            //
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 22.dp, end = 22.dp, bottom = 12.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(35.dp)
                        .align(Alignment.CenterVertically),
                    imageVector = Icons.Default.Phone,
                    tint = SkyColor,
                    contentDescription = "icon",
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp)
                ) {
                    Text("Телефон", color = Color.Gray, fontSize = 22.sp)
                    Text(employee.phone, color = Color.Black, fontSize = 25.sp)
                }

            }
        }
    }
}
@Composable
private fun TopBar(navController: NavHostController) {
    var enabled by remember { mutableStateOf(true) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(SkyColor)
            .padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
    ) {
        IconButton(
            onClick = {
                enabled = false
                navController.popBackStack()
            }, enabled = enabled, modifier = Modifier
                .padding(top = 30.dp, start = 7.dp)
                .size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "img",
                modifier = Modifier.size(40.dp)
            )
        }
        Text(
            modifier = Modifier.padding(
                top = 30.dp, bottom = 20.dp, start = 20.dp, end = 20.dp
            ), color = Color.White, fontSize = 28.sp, text = "Профил сотрудника"
        )
    }
}