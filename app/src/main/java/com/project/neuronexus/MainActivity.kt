package com.project.neuronexus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Scaffold
import androidx.compose.material3.FabPosition
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import com.project.neuronexus.ui.VoiceTaskScreen
import com.project.neuronexus.ui.VoiceTaskViewModel
import com.project.neuronexus.ui.dashboard.NeuroNexusDashboard
import com.project.neuronexus.ui.tasks.TasksScreen
import com.project.neuronexus.ui.theme.NeuroNexusTheme
import com.project.neuronexus.ui.components.SpeakerFab



class MainActivity : ComponentActivity() {

    private val viewModel: VoiceTaskViewModel by viewModels {
        VoiceTaskViewModel.Factory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)

        setContent {
            NeuroNexusTheme {

                val navController = rememberNavController()
                var currentScreen by remember { mutableStateOf("dashboard") }

                val screenText = when (currentScreen) {
                    "dashboard" -> "Welcome to NeuroNexus Dashboard."
                    "tasks" -> "This is the Tasks screen."
                    "voice_task" -> "This is the Voice Task screen."
                    else -> "Welcome to NeuroNexus."
                }

                Scaffold(
                    floatingActionButton = {
                        // SpeakerFab positioned just above bottom navbar
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp), // distance from bottom navbar
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.End // FAB on the right
                        ) {
                            SpeakerFab(textToRead = screenText)
                        }
                    },
                    floatingActionButtonPosition = FabPosition.End // can also use Center
                ) { paddingValues ->

                    NavHost(
                        navController = navController,
                        startDestination = "dashboard",
                        modifier = Modifier
                            .padding(
                                top = 8.dp, // reduced top margin
                                bottom = paddingValues.calculateBottomPadding()
                            )
                            .fillMaxSize()
                    ) {

                        composable("dashboard") {
                            currentScreen = "dashboard"
                            NeuroNexusDashboard(
                                onTasksClick = {
                                    currentScreen = "tasks"
                                    navController.navigate("tasks")
                                }
                            )
                        }

                        composable("tasks") {
                            currentScreen = "tasks"
                            TasksScreen(navController = navController)
                        }

                        composable("voice_task") {
                            currentScreen = "voice_task"
                            VoiceTaskScreen(viewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}
