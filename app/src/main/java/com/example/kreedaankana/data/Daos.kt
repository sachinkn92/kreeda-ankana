package com.example.kreedaankana.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {
    @Query("SELECT * FROM teams")
    fun getAllTeams(): Flow<List<TeamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(team: TeamEntity)
}

@Dao
interface BookingDao {
    @Query("SELECT * FROM bookings WHERE date = :date")
    fun getBookingsForDate(date: String): Flow<List<BookingEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertBooking(booking: BookingEntity)
    
    @Query("SELECT COUNT(*) FROM bookings WHERE date = :date AND timeSlot = :timeSlot")
    suspend fun checkSlotAvailability(date: String, timeSlot: String): Int
}

@Dao
interface MatchResultDao {
    @Query("SELECT * FROM match_results ORDER BY id DESC")
    fun getAllMatchResults(): Flow<List<MatchResultEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatchResult(matchResult: MatchResultEntity)
}
