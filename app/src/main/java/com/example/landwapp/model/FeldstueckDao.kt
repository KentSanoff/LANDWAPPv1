package com.example.landwapp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FeldstueckDao {
    @Insert
    suspend fun insert(feldstueck: Feldstueck)

    @Query("SELECT * FROM feldstuecke")
    fun getAll(): List<Feldstueck>
}