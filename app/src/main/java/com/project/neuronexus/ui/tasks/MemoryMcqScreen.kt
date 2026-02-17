package com.project.neuronexus.ui.tasks


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.neuronexus.R
import com.project.neuronexus.ui.components.NeuroTopBar

@Composable
fun MemoryMcqScreen(navController: NavController) {

    val questionList = remember {
        listOf(
            RecallQuestionMem(
                R.drawable.person1,
                "Ross",
                listOf("Mike", "Ross", "Alen", "Max").shuffled()
            ),
            RecallQuestionMem(
                R.drawable.person2,
                "Rachel",
                listOf("Rachel", "Monica", "Lily", "Emma").shuffled()
            ),
            RecallQuestionMem(
                R.drawable.person3,
                "Monica",
                listOf("Phoebe", "Monica", "Nina", "Sara").shuffled()
            ),
            RecallQuestionMem(
                R.drawable.person4,
                "Chandler",
                listOf("Ross", "Chandler", "Mike", "David").shuffled()
            )
        )
    }

    var currentIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var showAnswer by remember { mutableStateOf(false) }

    val currentQuestion = questionList[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F1F8))
    ) {

        // 🔹 Top Bar
        NeuroTopBar()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(currentQuestion.imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(20.dp))

        LinearProgressIndicator(
            progress = (currentIndex + 1) / questionList.size.toFloat(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("${currentIndex + 1} of ${questionList.size}")

        Spacer(modifier = Modifier.height(20.dp))

        // OPTIONS
        currentQuestion.options.forEach { option ->

            val backgroundColor = when {
                showAnswer && option == currentQuestion.correctAnswer -> Color(0xFF81C784) // GREEN
                showAnswer && option == selectedAnswer && option != currentQuestion.correctAnswer -> Color(0xFFE57373) // RED
                selectedAnswer == option -> Color(0xFFB39DDB)
                else -> Color.LightGray
            }

            Button(
                onClick = {
                    if (!showAnswer) {
                        selectedAnswer = option
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Text(option)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (!showAnswer) {
                    showAnswer = true
                    if (selectedAnswer == currentQuestion.correctAnswer) {
                        score++
                    }
                } else {
                    if (currentIndex < questionList.lastIndex) {
                        currentIndex++
                        selectedAnswer = null
                        showAnswer = false
                    } else {
                        navController.navigate("memory_score/$score")
                    }
                }
            },
            enabled = selectedAnswer != null,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFB39DDB)
            )
        ) {
            Text(if (showAnswer) "NEXT" else "CHECK")
        }
    }
}}
