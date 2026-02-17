package com.project.neuronexus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.neuronexus.ui.VoiceTaskScreen
import com.project.neuronexus.ui.VoiceTaskViewModel
import com.project.neuronexus.ui.dashboard.NeuroNexusDashboard
import com.project.neuronexus.ui.tasks.TasksScreen
import com.project.neuronexus.ui.tasks.MemoryMatchScreen
import com.project.neuronexus.ui.community.CommunityPage
import com.project.neuronexus.ui.tasks.MemoryMcqScreen
import com.project.neuronexus.ui.tasks.MemoryPreviewScreen
import com.project.neuronexus.ui.tasks.MemoryRecallScreen
import com.project.neuronexus.ui.theme.NeuroNexusTheme
import com.project.neuronexus.ui.tasks.NarrativeRecallScreen
import com.project.neuronexus.ui.tasks.StoryScreen
import com.project.neuronexus.ui.tasks.RecallPhaseScreen
import com.project.neuronexus.ui.tasks.RecallQuestionScreen
import com.project.neuronexus.ui.tasks.RecallResultScreen
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.project.neuronexus.ui.tasks.MemoryScoreScreen


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
                            onHomeClick = { navController.navigate("dashboard") },
                            onTasksClick = { navController.navigate("tasks") },
                            onSettingsClick = { /* TODO: navigate to settings */ },
                            onShareClick = { navController.navigate("community") }
                        )
                    }

                    // -------- TASK LIST --------
                    composable("tasks") {
                        TasksScreen(navController = navController)
                    }

                    // -------- MEMORY MATCH --------
                    composable("memory_match") {
                        MemoryMatchScreen(navController = navController)
                    }

                    // -------- NARRATIVE RECALL --------
                    composable("narrative_recall") {
                        NarrativeRecallScreen(navController)
                    }

                    composable("memory_preview") {
                        MemoryPreviewScreen(navController)
                    }
                    composable("memory_recall") {
                        MemoryRecallScreen(navController)
                    }


                    // -------- VOICE TASK --------
                    composable("voice_task") {
                        VoiceTaskScreen(
                            navController = navController,
                            viewModel = viewModel
                        )
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
                    composable("recall_result/{score}") { backStackEntry ->
                        val score = backStackEntry.arguments?.getString("score")?.toInt() ?: 0
                        RecallResultScreen(navController, score)
                    }

                    composable("memory_mcq") {
                        MemoryMcqScreen(navController)
                    }

                    composable(
                        route = "memory_score/{score}",
                        arguments = listOf(navArgument("score") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val score = backStackEntry.arguments?.getInt("score") ?: 0
                        MemoryScoreScreen(navController, score)
                    }




                    // -------- COMMUNITY PAGE --------
                    composable("community") {
                        CommunityPage(navController = navController)
                    }
                }

            }
        }
    }
}
