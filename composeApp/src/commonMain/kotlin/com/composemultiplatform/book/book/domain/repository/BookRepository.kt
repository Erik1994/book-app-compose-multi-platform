package com.composemultiplatform.book.book.domain.repository

import com.composemultiplatform.book.book.domain.model.Book
import com.composemultiplatform.book.core.domain.DataError
import com.composemultiplatform.book.core.domain.Result

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
    suspend fun getBookDescription(bookId: String): Result<String?, DataError>
}