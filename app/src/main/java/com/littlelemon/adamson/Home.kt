package com.littlelemon.adamson

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController, database: AppDatabase) {
    var searchPhrase by remember {
        mutableStateOf("")
    }
    var selectedCategory by remember {
        mutableStateOf("All")
    }
    val menuItems by database
        .menuDao()
        .getAll()
        .observeAsState(emptyList())
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            CenterAlignedTopAppBar(
                title = {
                    Text("")
                },
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier.size(200.dp)
                    )
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Profile.route)
                    },
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Profile",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF495E57))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Text(
                        text = "Little Lemon",
                        color = Color(0xFFF4CE14),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold,
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Chicago",
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Traditional recipes served with a modern twist",
                                color = Color.White,
                                fontSize = 16.sp,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.heroimage),
                            contentDescription = "Hero Image",
                            modifier = Modifier.size(100.dp)
                        )
                    }
                    Spacer(Modifier.height(16.dp))
                    OutlinedTextField(
                        value = searchPhrase,
                        onValueChange = {
                            searchPhrase = it
                        },
                        label = {
                            Text("Search menu",
                                color = Color.White,)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }
            }
            Spacer(Modifier.height(20.dp))
            Text(
                text = "ORDER FOR DELIVERY!",
                modifier = Modifier.padding(start = 20.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(Modifier.height(10.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )
            Row {
                val categories = listOf(
                    "All",
                    "starters",
                    "mains",
                    "desserts"
                )

                categories.forEach { category ->

                    Button(
                        onClick = {
                            selectedCategory = category
                        }
                    ) {
                        Text(category)
                    }
                }
            }
            
            val filteredItems = menuItems.filter {
                (selectedCategory == "All" || it.category == selectedCategory) &&
                (it.title.contains(searchPhrase, ignoreCase = true))
            }.sortedBy { it.title }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                items(filteredItems) { item ->
                    MenuItemCard(item)
                }
            }
        }
    }
}

@Composable
fun MenuItemCard(item: MenuItemRoom) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.title, fontWeight = FontWeight.Bold)
            Text(text = item.description, color = Color.Gray, maxLines = 2)
            Text(text = "$${item.price}", fontWeight = FontWeight.SemiBold)
        }
    }
    HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)
}

@Preview (showBackground = true)
@Composable
fun HomePreview(){
    val database = AppDatabase.getDatabase(LocalContext.current)
    Home(rememberNavController(), database)
}
