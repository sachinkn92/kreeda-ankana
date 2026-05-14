package com.example.kreedaankana.ui.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kreedaankana.data.AppDatabase
import com.example.kreedaankana.data.BookingEntity
import com.example.kreedaankana.data.Challenge
import com.example.kreedaankana.data.ChallengeRepository
import com.example.kreedaankana.data.MatchResultEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

class AppViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    private val bookingDao = db.bookingDao()
    private val matchDao = db.matchResultDao()
    private val challengeRepo = ChallengeRepository()

    val matchResults: StateFlow<List<MatchResultEntity>> = matchDao.getAllMatchResults()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val challenges: StateFlow<List<Challenge>> = challengeRepo.challenges
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _bookingsForDate = MutableStateFlow<List<BookingEntity>>(emptyList())
    val bookingsForDate = _bookingsForDate.asStateFlow()

    private val _bookingStatus = MutableStateFlow<String?>(null)
    val bookingStatus = _bookingStatus.asStateFlow()

    init {
        // Pre-populate some dummy data if empty
        viewModelScope.launch {
            matchDao.insertMatchResult(MatchResultEntity(0, "2024-05-10", "Cricket", "Warriors FC", "Eagles Club", "Warriors won by 20 runs"))
            matchDao.insertMatchResult(MatchResultEntity(0, "2024-05-12", "Volleyball", "Strikers", "Titans", "Strikers won 3-1"))
        }
    }

    fun loadBookingsForDate(date: String) {
        viewModelScope.launch {
            bookingDao.getBookingsForDate(date).collect {
                _bookingsForDate.value = it
            }
        }
    }

    fun bookSlot(teamName: String, date: String, timeSlot: String) {
        viewModelScope.launch {
            val count = bookingDao.checkSlotAvailability(date, timeSlot)
            if (count > 0) {
                _bookingStatus.value = "Error: Slot already booked!"
            } else {
                bookingDao.insertBooking(BookingEntity(0, 1, teamName, date, timeSlot))
                _bookingStatus.value = "Success: Slot booked!"
            }
        }
    }

    fun clearBookingStatus() {
        _bookingStatus.value = null
    }

    fun postChallenge(teamName: String, sport: String, date: String) {
        viewModelScope.launch {
            val challenge = Challenge(UUID.randomUUID().toString(), teamName, sport, date)
            challengeRepo.postChallenge(challenge)
        }
    }

    fun acceptChallenge(challengeId: String, teamName: String) {
        viewModelScope.launch {
            challengeRepo.acceptChallenge(challengeId, teamName)
        }
    }
}
