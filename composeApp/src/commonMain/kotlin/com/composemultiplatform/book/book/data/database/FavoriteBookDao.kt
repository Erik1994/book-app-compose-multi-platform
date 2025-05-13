package com.composemultiplatform.book.book.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteBookDao {

    @Upsert
    suspend fun upsert(book: BookEntity)

    @Query("SELECT * FROM $BOOK_TABLE")
    fun getFavoriteBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM $BOOK_TABLE WHERE id=:id")
    suspend fun getFavoriteBook(id: String): BookEntity?

    @Query("DELETE FROM $BOOK_TABLE WHERE id=:id")
    suspend fun deleteFavoriteBook(id: String)
}