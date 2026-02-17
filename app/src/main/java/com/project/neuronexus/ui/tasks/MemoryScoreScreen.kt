package com.project.neuronexus.ui.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MemoryScoreScreen(navController: NavController, score: Int) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F1F8)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Great Job!", fontSize = 28.sp)

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Score $score / 4",
            fontSize = 40.sp,
            color = Color(0xFFB39DDB)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                navController.navigate("tasks")
            }
        ) {
            Text("NEXT")
        }
    }
}
