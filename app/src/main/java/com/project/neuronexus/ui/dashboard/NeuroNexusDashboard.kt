 package com.project.neuronexus.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.offset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.neuronexus.ui.components.ActivityChart
import com.project.neuronexus.ui.components.CustomBottomBar
import com.project.neuronexus.ui.components.NeuroTopBar
import com.project.neuronexus.ui.components.SpeakerFab

@Composable
fun NeuroNexusDashboard(
    onTasksClick: () -> Unit,
    dashboardText: String = "Welcome to your NeuroNexus dashboard. Points: 550 coins. Condition: All is well."
) {
    Scaffold(
        topBar = { NeuroTopBar() },
        bottomBar = { CustomBottomBar() },
        floatingActionButton = {
            SpeakerFab(
                textToRead = dashboardText,
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
                    .padding(20.dp)
            ) {

                Text(
                    text = "Dashboard",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    StatusCard(
                        title = "Points",
                        value = "550 coins",
                        modifier = Modifier.weight(1f)
                    )

                    StatusCard(
                        title = "Condition",
                        value = "All is well",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                ActivityChart()

                Spacer(modifier = Modifier.height(28.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Tasks",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "View All",
                        color = Color(0xFF7E57C2),
                        modifier = Modifier.clickable {
                            onTasksClick()
                        }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                DashboardTaskRow("Memory Match", Color(0xFFD1FADF))
                DashboardTaskRow("Trail Making", Color(0xFFD1E9FF))
                DashboardTaskRow("Reading Test", Color(0xFFFFE4F2))
                DashboardTaskRow("Face Capture", Color(0xFFFEF9C3))
            }
        }
    }
}

@Composable
fun StatusCard(
    title: String,
    value: String,
    modifier: Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                color = Color.Gray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun DashboardTaskRow(
    name: String,
    color: Color
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = color),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
        }
    }
}
