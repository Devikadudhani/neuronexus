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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import com.project.neuronexus.ui.VoiceTaskScreen
import com.project.neuronexus.ui.VoiceTaskViewModel
import com.project.neuronexus.ui.dashboard.NeuroNexusDashboard
import com.project.neuronexus.ui.tasks.TasksScreen
import com.project.neuronexus.ui.tasks.MemoryMatchScreen
import com.project.neuronexus.ui.theme.NeuroNexusTheme
import com.project.neuronexus.ui.tasks.NarrativeRecallScreen
import com.project.neuronexus.ui.tasks.StoryScreen
import com.project.neuronexus.ui.tasks.RecallPhaseScreen
import com.project.neuronexus.ui.tasks.RecallQuestionScreen

class MainActivity : ComponentActivity() {

    private val viewModel: VoiceTaskViewModel by viewModels {
        VoiceTaskViewModel.Factory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            NeuroNexusTheme {

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "dashboard"
                ) {

                    // -------- DASHBOARD --------
                    composable("dashboard") {
                        NeuroNexusDashboard(
                            onTasksClick = {
                                navController.navigate("tasks")
                            }
                        )
                    }

                    // -------- TASK LIST --------
                    composable("tasks") {
                        TasksScreen(navController = navController)
                    }

                    // -------- MEMORY MATCH --------
                    composable("memory_match") {
                        MemoryMatchScreen(navController)
                    }

                    // -------- NARRATIVE RECALL --------
                    composable("narrative_recall") {
                        NarrativeRecallScreen(navController)
                    }

                    // -------- VOICE TASK --------
                    composable("voice_task") {
                        VoiceTaskScreen(viewModel = viewModel)
                    }
                    composable("story") {
                        StoryScreen(navController)
                    }
                    composable("recall_phase") {
                        RecallPhaseScreen(navController)
                    }
                    composable("recall_question") {
                        RecallQuestionScreen(navController)
                    }

                }

            }
        }
    }
}
