package com.ibrohimapk3.employeelist
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
private fun TopBar() {
    var searchText by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomStart = 20.dp,
                    bottomEnd = 20.dp
                )
            )
            .background(Color(0xFF70B4EC))
            .padding(10.dp)

    ) {
        Text(
            modifier = Modifier.padding(top = 30.dp, bottom = 20.dp, start = 20.dp, end = 20.dp),
            color = Color.White,
            fontSize = 28.sp,
            text = "Сотрудники"
        )
        TextField(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            value = searchText,
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
}

var empl: List<Employee> = listOf(
    Employee("id", "ib", "us", ".com", "92779", "it", "and", "i")
)

@Composable
fun ListOfEmployee() {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(empl) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 7.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 7.dp, vertical = 10.dp)
                    .shadow(10.dp, RoundedCornerShape(8.dp))
                    .background(Color(0xFFFFFFFF)),
            ) {
                Box(modifier = Modifier.size(120.dp), contentAlignment = Alignment.Center) {
                    Image(
                        modifier = Modifier
                            .size(90.dp)
                            .clip(RoundedCornerShape(50.dp)),
                        painter = painterResource(R.drawable.ic_launcher_background),
                        contentDescription = "",
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
                        text = it.position,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text(fontSize = 19.sp, color = Color.Blue, text = it.department)
                }
            }
        }
    }
}
