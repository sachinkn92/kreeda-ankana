package com.example.kreedaankana.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class Challenge(
    val id: String,
    val postingTeamName: String,
    val sport: String,
    val date: String,
    val status: String = "OPEN", // "OPEN", "ACCEPTED"
    val acceptedByTeam: String? = null
)

// Mock repository simulating Firebase Realtime Database
class ChallengeRepository {
    private val _challenges = MutableStateFlow<List<Challenge>>(
        listOf(
            Challenge("1", "Warriors FC", "Volleyball", "This Sunday"),
            Challenge("2", "Eagles Club", "Cricket", "Next Saturday")
        )
    )
    val challenges: Flow<List<Challenge>> = _challenges.asStateFlow()

    suspend fun postChallenge(challenge: Challenge) {
        delay(500) // simulate network delay
        _challenges.update { current ->
            current + challenge
        }
    }

    suspend fun acceptChallenge(challengeId: String, teamName: String) {
        delay(500)
        _challenges.update { current ->
            current.map {
                if (it.id == challengeId) {
                    it.copy(status = "ACCEPTED", acceptedByTeam = teamName)
                } else {
                    it
                }
            }
        }
    }
}
