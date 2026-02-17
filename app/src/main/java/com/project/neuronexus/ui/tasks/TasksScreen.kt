package com.project.neuronexus.ui.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.neuronexus.ui.components.CustomBottomBar
import com.project.neuronexus.ui.components.NeuroTopBar
import com.project.neuronexus.ui.components.SpeakerFab

@Composable
fun TasksScreen(
    navController: NavController,
    tasksText: String = "This is the tasks screen. You can choose activities like memory match, trail making, reading test and face capture."
) {
    Scaffold(
        topBar = { NeuroTopBar() },
        bottomBar = {    CustomBottomBar(
            onHomeClick = { navController.navigate("dashboard") },
            onTasksClick = { navController.navigate("tasks") },
            onSettingsClick = { /* TODO: navigate to settings */ },
            onShareClick = { navController.navigate("community") }
        )},
        floatingActionButton = {
            SpeakerFab(
                textToRead = tasksText,
                modifier = Modifier.padding(bottom = 0.dp)
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF3E5F5))
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(Color.White)
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = "Tasks",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                TaskItem(
                    title = "Memory Match",
                    description = "Learn and recall a list of words across multiple trials.",
                    bgColor = Color(0xFFD1FADF),
                    onStartClick = {
                        navController.navigate("memory_match")
                    }
                )

                TaskItem(
                    title = "Trail Making",
                    description = "Connect the dots in numerical and alphabetical order.",
                    bgColor = Color(0xFFD1E9FF),
                    onStartClick = { }
                )

                TaskItem(
                    title = "Reading Test",
                    description = "Read the text aloud to evaluate speech and fluency.",
                    bgColor = Color(0xFFFFE4F2),
                    onStartClick = {
                        navController.navigate("narrative_recall")
                    }
                )


                TaskItem(
                    title = "Face Capture",
                    description = "Perform facial expressions to assess motor function.",
                    bgColor = Color(0xFFFEF9C3),
                    onStartClick = { }
                )
            }
        }
    }
}

@Composable
fun TaskItem(
    title: String,
    description: String,
    bgColor: Color,
    onStartClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1D2939)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF475467)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                Button(
                    onClick = onStartClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(50),
                    elevation = ButtonDefaults.buttonElevation(2.dp)
                ) {
                    Text("Start Activity", color = Color.Black)
                }

                OutlinedButton(
                    onClick = { },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                ) {
                    Text("Demo Video")
                }
            }
        }
    }
}
