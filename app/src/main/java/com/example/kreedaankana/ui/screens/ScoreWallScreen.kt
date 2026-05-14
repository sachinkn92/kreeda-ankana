package com.example.kreedaankana.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kreedaankana.ui.theme.SecondaryOrange

@Composable
fun ScoreWallScreen(viewModel: AppViewModel = viewModel()) {
    val matchResults by viewModel.matchResults.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Score Wall", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text("Latest match results from around the village", color = Color.Gray, modifier = Modifier.padding(bottom = 16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(matchResults) { match ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Text("${match.team1Name} vs ${match.team2Name}", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                            Text(match.date, color = Color.Gray)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(match.sport, color = SecondaryOrange, fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(match.resultSummary, style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}
