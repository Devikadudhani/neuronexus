package com.project.neuronexus.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.project.neuronexus.tts.TtsController

@Composable
fun SpeakerFab(
    textToRead: String,
    modifier: Modifier = Modifier // Add modifier parameter with default
) {
    val context = LocalContext.current
    val ttsController = remember { TtsController(context) }

    DisposableEffect(Unit) {
        onDispose {
            ttsController.shutdown()
        }
    }

    FloatingActionButton(
        onClick = { ttsController.speak(textToRead) },
        modifier = modifier // Apply modifier here
    ) {
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "Read Aloud"
        )
    }
}
