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
import androidx.compose.material.icons.Icons


@Composable
fun NarrativeRecallScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F1F8))
    ) {

        NeuroTopBar()

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "NARRATIVE RECALL",
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
                    text = "Listen carefully to the short story. After the story, you will be asked some questions. Answer each question aloud. Press the microphone to start and stop recording. When finished, press Next.",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            BoxWithConstraints(
                modifier = Modifier.fillMaxSize()
            ) {
                val boxWidth = maxWidth
                val boxHeight = maxHeight

                // Mascot
                Image(
                    painter = painterResource(id = R.drawable.mascot_img),
                    contentDescription = "Mascot",
                    modifier = Modifier
                        .size(boxWidth * 1.3f)
                        .align(Alignment.BottomStart)
                        .offset(
                            x = boxWidth * (-0.09f),
                            y = boxHeight * (-0.05f)
                        ),
                    contentScale = ContentScale.Crop
                )

                // Start button
                Button(
                    onClick = {
                        navController.navigate("story")
                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFB39DDB)
                    ),
                    modifier = Modifier
                        .height(55.dp)
                        .width(170.dp)
                        .align(Alignment.CenterEnd)
                        .offset(y = boxHeight * (-0.32f))
                        .padding(end = 32.dp)
                ) {
                    Text(
                        text = "START",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }

                // Sound FAB
                FloatingActionButton(
                    onClick = { },
                    containerColor = Color(0xFF8E6BAF),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                ) {
//                    Icon(
//                        imageVector = Icons.Default.VolumeUp,
//                        contentDescription = "Sound",
//                        tint = Color.White
//                    )
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
