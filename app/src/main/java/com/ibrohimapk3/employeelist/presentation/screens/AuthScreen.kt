package com.ibrohimapk3.employeelist.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibrohimapk3.employeelist.presentation.theme.SkyColor
import com.ibrohimapk3.employeelist.presentation.viewmodel.LoginViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = getViewModel(),
    onAuthToListOfEmployee: () -> Unit,
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    val isLoading by viewModel.isLoading.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .imePadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Добро пожаловать",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        TextField(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxWidth()
                .border(1.dp, color = Color.Black, shape = RoundedCornerShape(20.dp)),
            shape = RoundedCornerShape(20.dp),
            value = emailText,
            maxLines = 1,
            placeholder = {
                Text(text = "Электронная почта")
            },
            onValueChange = { emailText = it },
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "email", tint = SkyColor)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
        TextField(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxWidth()
                .border(1.dp, color = Color.Black, shape = RoundedCornerShape(20.dp)),
            shape = RoundedCornerShape(20.dp),
            value = passwordText,
            placeholder = {
                Text(text = "Пароль")
            },
            maxLines = 1,
            onValueChange = { passwordText = it },
            leadingIcon = {
                Icon(Icons.Default.Password, contentDescription = "email", tint = SkyColor)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
        Button(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = SkyColor,
            ),
            onClick = {
                viewModel.login(emailText.trimEnd(), passwordText.trimEnd())
            }
        ) {
            Text("Войти", color = Color.White, fontSize = 22.sp)
        }
        if (state) onAuthToListOfEmployee()
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = SkyColor
            )
        }
        LaunchedEffect(Unit) {
            viewModel.error.collect {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}