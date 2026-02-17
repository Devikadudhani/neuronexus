package com.project.neuronexus.ui.tasks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.neuronexus.R
import com.project.neuronexus.ui.components.CustomBottomBar
import com.project.neuronexus.ui.components.NeuroTopBar

@Composable
fun MemoryMatchScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F1F8))
    ) {

        // 🔹 Your existing top bar
        NeuroTopBar()

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "MEMORY MATCH",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6B4E8E)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Text(
                    text = "You will be shown pictures with names. Try to remember each pair. Later you’ll be asked to match the correct name with its picture.",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()    // takes remaining screen space
            ) {

                // 🔹 Mascot Image (Bottom Left)
                Image(
                    painter = painterResource(id = R.drawable.mascot_img),
                    contentDescription = "Mascot",
                    modifier = Modifier
                        .size(260.dp)   // 🔥 now this WILL change properly
                        .scale(1.8f)
                        .offset(x = (-10).dp, y = (-50).dp)
                        .align(Alignment.BottomStart),
//                      .padding(start = 24.dp, bottom = 40.dp)
                    contentScale = ContentScale.Crop
                )

                // 🔹 Start Button (Right Side, Mid Level)
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFB39DDB)
                    ),
                    modifier = Modifier
                        .height(55.dp)
                        .width(170.dp)
                        .offset(x = (9).dp, y = (-150).dp)
                        .align(Alignment.CenterEnd)
                        .padding(end = 32.dp)
                ) {
                    Text(
                        text = "START",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }



        }
        CustomBottomBar(
            onHomeClick = { navController.navigate("dashboard") },
            onTasksClick = { navController.navigate("tasks") },
            onSettingsClick = { /* TODO: navigate to settings */ },
            onShareClick = { navController.navigate("community") }
        )
    }
}
