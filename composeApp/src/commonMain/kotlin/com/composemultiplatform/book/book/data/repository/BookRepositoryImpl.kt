package com.composemultiplatform.book.book.data.repository

import com.composemultiplatform.book.book.data.mapper.toModel
import com.composemultiplatform.book.book.data.network.RemoteBookDataSource
import com.composemultiplatform.book.book.domain.model.Book
import com.composemultiplatform.book.book.domain.repository.BookRepository
import com.composemultiplatform.book.core.domain.DataError
import com.composemultiplatform.book.core.domain.Result
import com.composemultiplatform.book.core.domain.map

class BookRepositoryImpl(
    private val remoteBookDataSource: RemoteBookDataSource
) : BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> =
        remoteBookDataSource.searchBooks(query)
            .map { dto ->
                dto.results.map { it.toModel() }
            }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {
        return remoteBookDataSource
            .getBookDescription(bookId)
            .map { it.description }
    }
}