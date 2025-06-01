package com.example.landwapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feldstuecke")
data class Feldstueck(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val groesse: Double
)