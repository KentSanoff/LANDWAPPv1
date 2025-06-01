package com.example.landwapp.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DuengungDao {
    @Query("SELECT * FROM duengung")
    fun getAll(): Flow<List<Duengung>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(duengung: Duengung)

    @Delete
    suspend fun delete(duengung: Duengung)
}
