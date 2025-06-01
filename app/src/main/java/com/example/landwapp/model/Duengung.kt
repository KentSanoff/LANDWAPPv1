package com.example.landwapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Duengung(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val produkt: String,
    val menge: Double,
    val einheit: String,
    val schlagName: String
)
