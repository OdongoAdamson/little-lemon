package com.littlelemon.adamson

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Profile(navController: NavHostController) {
    val context = LocalContext.current

    val sharedPreferences = context.getSharedPreferences(
        "LittleLemon",
        Context.MODE_PRIVATE
    )

    val firstName = sharedPreferences.getString("firstName", "") ?: ""
    val lastName = sharedPreferences.getString("lastName", "") ?: ""
    val email = sharedPreferences.getString("email", "") ?: ""


    Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

            ) {
                Image(painter = painterResource(R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth())
                Spacer(modifier = Modifier.height(30.dp))
                Column(

                ) {
                    Text(
                        modifier = Modifier
                            .padding(10.dp),
                        text = "Personal Information",
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Right,

                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = "First Name",
                        textAlign = TextAlign.Right,

                    )
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(50.dp),
                        shape = RoundedCornerShape(8.dp),

                    )  {
                        Text(text = "$firstName")
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = "Last Name",
                        textAlign = TextAlign.Right
                    )
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(50.dp),
                        shape = RoundedCornerShape(8.dp),

                        )  {
                        Text(
                            text = "$lastName",
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Email",
                        modifier = Modifier.padding(10.dp),
                        textAlign = TextAlign.Right
                    )
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(50.dp),
                        shape = RoundedCornerShape(8.dp),

                        )  {
                        Text(text = "$email")
                    }

                }
                    Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    sharedPreferences.edit().clear().apply()
                    navController.navigate(Onboarding.route) {
                        popUpTo(Home.route) { inclusive = true }
                    }
                },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF4CE14) // Little Lemon yellow
                    )
                ) {
                    Text(
                        text = "Logout",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }


}

@Preview(showBackground = true)
@Composable
fun ProfilePreview(){
    Profile(rememberNavController())
}