package com.example.kreedaankana.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kreedaankana.ui.navigation.Screen
import com.example.kreedaankana.ui.theme.PrimaryBlue
import com.example.kreedaankana.ui.theme.SecondaryOrange

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "KREEDA-ANKANA",
            style = MaterialTheme.typography.titleLarge,
            color = PrimaryBlue,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(top = 32.dp, bottom = 8.dp)
        )
        Text(
            text = "Ground & Match Organizer",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 48.dp)
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            HomeCard("Book Slot", "Reserve ground time", SecondaryOrange, Modifier.weight(1f)) {
                navController.navigate(Screen.Booking.route)
            }
            Spacer(modifier = Modifier.width(16.dp))
            HomeCard("Challenges", "Find opponents", PrimaryBlue, Modifier.weight(1f)) {
                navController.navigate(Screen.Challenge.route)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        HomeCard("Score Wall", "Latest village matches", Color(0xFF4CAF50), Modifier.fillMaxWidth()) {
            navController.navigate(Screen.ScoreWall.route)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeCard(title: String, subtitle: String, color: Color, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = modifier.height(140.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(color.copy(alpha = 0.2f), Color.Transparent)))
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium, color = color, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Color.White, textAlign = TextAlign.Center)
        }
    }
}
