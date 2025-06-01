package com.example.landwapp.feldstuecke

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FeldstueckDao {

    @Query("SELECT * FROM feldstuecke ORDER BY name ASC")
    fun getAllFeldstuecke(): Flow<List<Feldstueck>>

    @Query("SELECT * FROM feldstuecke WHERE id = :id")
    suspend fun getFeldstueckById(id: Int): Feldstueck?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(feldstueck: Feldstueck)

    @Update
    suspend fun update(feldstueck: Feldstueck)

    @Delete
    suspend fun delete(feldstueck: Feldstueck)

    @Query("DELETE FROM feldstuecke")
    suspend fun deleteAll()

    @Query("SELECT kultur FROM feldstuecke WHERE id = :feldstueckId LIMIT 1")
    suspend fun getKulturById(feldstueckId: Int): String?

}
