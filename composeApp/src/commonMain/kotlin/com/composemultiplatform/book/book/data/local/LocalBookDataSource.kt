package com.composemultiplatform.book.book.data.local

import com.composemultiplatform.book.book.data.database.BookEntity
import com.composemultiplatform.book.core.domain.DataError
import com.composemultiplatform.book.core.domain.EmptyResult
import kotlinx.coroutines.flow.Flow

interface LocalBookDataSource {
    fun getFavoriteBooks(): Flow<List<BookEntity>>
    fun isBookFavorite(id: String): Flow<Boolean>
    suspend fun markAsFavorite(book: BookEntity): EmptyResult<DataError.Local>
    suspend fun getFavoriteBook(id: String): BookEntity?
    suspend fun deleteFromFavorites(id: String)
}