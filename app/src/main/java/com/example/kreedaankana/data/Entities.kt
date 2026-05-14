package com.example.kreedaankana.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class TeamEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val village: String,
    val sport: String,
    val contactPhone: String
)

@Entity(tableName = "bookings")
data class BookingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val teamId: Int,
    val teamName: String, // Storing for easier UI display
    val date: String, // Format YYYY-MM-DD
    val timeSlot: String // e.g. "16:00-18:00"
)

@Entity(tableName = "match_results")
data class MatchResultEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val sport: String,
    val team1Name: String,
    val team2Name: String,
    val resultSummary: String
)
