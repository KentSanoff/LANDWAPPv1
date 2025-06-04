package com.example.landwapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.landwapp.model.Duengung
import com.example.landwapp.model.Feldstueck
import com.example.landwapp.model.DuengungDao
import com.example.landwapp.model.FeldstueckDao

@Database(
    entities = [Feldstueck::class, Duengung::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun feldstueckDao(): FeldstueckDao
    abstract fun duengungDao(): DuengungDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "landwapp.db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
