package com.example.kreedaankana.ui.screens

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(viewModel: AppViewModel = viewModel()) {
    val context = LocalContext.current
    var selectedDate by remember { mutableStateOf("2024-05-15") } // Hardcoded for demo
    var teamName by remember { mutableStateOf("") }
    var selectedSlot by remember { mutableStateOf<String?>(null) }
    
    val bookings by viewModel.bookingsForDate.collectAsState()
    val status by viewModel.bookingStatus.collectAsState()

    val timeSlots = listOf("06:00-08:00", "08:00-10:00", "10:00-12:00", "14:00-16:00", "16:00-18:00", "18:00-20:00")

    LaunchedEffect(selectedDate) {
        viewModel.loadBookingsForDate(selectedDate)
    }

    LaunchedEffect(status) {
        status?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.clearBookingStatus()
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Ground Booking", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = teamName,
            onValueChange = { teamName = it },
            label = { Text("Your Team Name") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        Text("Select Time Slot ($selectedDate)", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(timeSlots) { slot ->
                val isBooked = bookings.any { it.timeSlot == slot }
                val isSelected = selectedSlot == slot
                
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(enabled = !isBooked) { selectedSlot = slot }
                        .border(
                            width = if (isSelected) 2.dp else 0.dp,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isBooked) Color.DarkGray else MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Box(modifier = Modifier.padding(16.dp).fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(slot, color = if (isBooked) Color.Gray else Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        
        Button(
            onClick = {
                if (teamName.isNotBlank() && selectedSlot != null) {
                    viewModel.bookSlot(teamName, selectedDate, selectedSlot!!)
                    selectedSlot = null
                } else {
                    Toast.makeText(context, "Enter team name and select slot", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            enabled = teamName.isNotBlank() && selectedSlot != null
        ) {
            Text("Confirm Booking")
        }
    }
}
