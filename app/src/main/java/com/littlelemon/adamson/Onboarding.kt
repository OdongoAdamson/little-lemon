package com.littlelemon.adamson

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Onboarding(navController: NavController){
    Scaffold { padding ->
        Box(
            modifier = Modifier.padding(top = 70.dp)
        )
        {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth(),

                )
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Spacer(modifier = Modifier.height(12.dp))

                    // Header banner
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF495E57)) // dark green
                            .padding(16.dp)
                            .height(150.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Let's get to know you",
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Personal information",
                        style = MaterialTheme.typography.titleSmall
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // First name
                    var firstName by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = firstName,
                        onValueChange = { firstName = it },
                        label = { Text("First name") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Last name
                    var lastName by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        label = { Text("Last name") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Email
                    var email by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    val context = LocalContext.current
                    // Register button
                    Button(
                        onClick = {
                            if (firstName.isBlank() || lastName.isBlank() || email.isBlank()){
                                Toast.makeText(
                                    context,
                                    "Registration unsuccessful. Please enter all data.",
                                    Toast.LENGTH_LONG
                                ).show()
                                navController.navigate(Onboarding.route)
                            }else {
                                val sharedPreferences = context.getSharedPreferences(
                                    "LittleLemon",
                                    Context.MODE_PRIVATE
                                )

                                with(sharedPreferences.edit()) {
                                    putString("firstName", firstName)
                                    putString("lastName", lastName)
                                    putString("email", email)
                                    apply()
                                }

                                Toast.makeText(
                                    context,
                                    "Registration successful!",
                                    Toast.LENGTH_SHORT
                                ).show()

                                navController.navigate(Home.route) {
                                    popUpTo(Onboarding.route) { inclusive = true }
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF4CE14) // Little Lemon yellow
                        )
                    ) {
                        Text(
                            text = "Register",
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun OnboardingPreview(){
    Onboarding(rememberNavController())
}