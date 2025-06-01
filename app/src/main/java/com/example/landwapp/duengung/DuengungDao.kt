package com.example.landwapp.duengung

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DuengungDao {

    @Query("SELECT * FROM duengungen WHERE feldstueckId = :feldstueckId ORDER BY datum DESC")
    fun getDuengungenForFeldstueck(feldstueckId: Int): Flow<List<Duengung>>

    @Query("SELECT * FROM duengungen ORDER BY datum DESC")
    fun getAlleDuengungen(): Flow<List<Duengung>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(duengung: Duengung)

    @Update
    suspend fun update(duengung: Duengung)

    @Delete
    suspend fun delete(duengung: Duengung)

    @Query("DELETE FROM duengungen")
    suspend fun deleteAll()
}
