package com.composemultiplatform.book.book.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<FavoriteBookDb> {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "BookApp")
            os.contains("mac") -> File(userHome, "Library/Application Support/BookApp")
            else -> File(userHome, ".local/share/BookApp")
        }
        if (appDataDir.exists().not()) appDataDir.mkdirs()
        val dbFile = File(appDataDir, FavoriteBookDb.DB_NAME)
        return Room.databaseBuilder<FavoriteBookDb>(dbFile.absolutePath)
    }
}