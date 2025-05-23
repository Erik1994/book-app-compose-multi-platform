package com.composemultiplatform.book.book.domain.repository

import com.composemultiplatform.book.book.domain.model.Book
import com.composemultiplatform.book.core.domain.DataError
import com.composemultiplatform.book.core.domain.EmptyResult
import com.composemultiplatform.book.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
    suspend fun getBookDescription(bookId: String): Result<String?, DataError>

    fun getFavoriteBooks(): Flow<List<Book>>
    fun isBookFavorite(id: String): Flow<Boolean>
    suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local>
    suspend fun deleteFromFavorites(id: String)
}