package com.example.landwapp.util

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.OutputStream

object BackupUtils {

    suspend fun backupDatabase(context: Context, destinationUri: Uri): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val dbFile = context.getDatabasePath("landwapp.db")
                if (!dbFile.exists()) return@withContext false

                val outputStream: OutputStream? =
                    context.contentResolver.openOutputStream(destinationUri)
                val inputStream = dbFile.inputStream()

                outputStream?.use { out ->
                    inputStream.copyTo(out)
                }
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }
}
