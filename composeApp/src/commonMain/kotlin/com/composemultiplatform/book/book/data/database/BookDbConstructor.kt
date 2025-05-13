package com.composemultiplatform.book.book.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object BookDbConstructor: RoomDatabaseConstructor<FavoriteBookDb> {
    override fun initialize(): FavoriteBookDb
}