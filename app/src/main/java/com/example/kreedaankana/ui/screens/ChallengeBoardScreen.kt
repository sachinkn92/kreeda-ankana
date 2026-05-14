package com.example.kreedaankana.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kreedaankana.ui.theme.GreenSuccess
import com.example.kreedaankana.ui.theme.PrimaryBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChallengeBoardScreen(viewModel: AppViewModel = viewModel()) {
    val challenges by viewModel.challenges.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }, containerColor = PrimaryBlue) {
                Icon(Icons.Filled.Add, "Post Challenge")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Text("Challenge Board", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Find an opponent for your next match", color = Color.Gray, modifier = Modifier.padding(bottom = 16.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(challenges) { challenge ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                                Text(challenge.postingTeamName, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                                Badge(containerColor = if (challenge.status == "OPEN") PrimaryBlue else GreenSuccess) {
                                    Text(challenge.status, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Sport: ${challenge.sport}")
                            Text("When: ${challenge.date}")
                            
                            if (challenge.status == "OPEN") {
                                Spacer(modifier = Modifier.height(12.dp))
                                Button(
                                    onClick = { viewModel.acceptChallenge(challenge.id, "My Team") },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Accept Challenge")
                                }
                            } else {
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Accepted by: ${challenge.acceptedByTeam}", color = GreenSuccess, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        var teamName by remember { mutableStateOf("") }
        var sport by remember { mutableStateOf("") }
        var date by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Post a Challenge") },
            text = {
                Column {
                    OutlinedTextField(value = teamName, onValueChange = { teamName = it }, label = { Text("Team Name") })
                    OutlinedTextField(value = sport, onValueChange = { sport = it }, label = { Text("Sport") })
                    OutlinedTextField(value = date, onValueChange = { date = it }, label = { Text("Date/Time") })
                }
            },
            confirmButton = {
                Button(onClick = {
                    if (teamName.isNotBlank() && sport.isNotBlank() && date.isNotBlank()) {
                        viewModel.postChallenge(teamName, sport, date)
                        showDialog = false
                    }
                }) { Text("Post") }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) { Text("Cancel") }
            }
        )
    }
}
