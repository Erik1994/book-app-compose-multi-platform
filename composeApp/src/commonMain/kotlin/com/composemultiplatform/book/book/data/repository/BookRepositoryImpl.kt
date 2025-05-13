package com.composemultiplatform.book.book.data.repository

import com.composemultiplatform.book.book.data.local.LocalBookDataSource
import com.composemultiplatform.book.book.data.mapper.toEntity
import com.composemultiplatform.book.book.data.mapper.toModel
import com.composemultiplatform.book.book.data.remote.RemoteBookDataSource
import com.composemultiplatform.book.book.domain.model.Book
import com.composemultiplatform.book.book.domain.repository.BookRepository
import com.composemultiplatform.book.core.domain.DataError
import com.composemultiplatform.book.core.domain.EmptyResult
import com.composemultiplatform.book.core.domain.Result
import com.composemultiplatform.book.core.domain.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookRepositoryImpl(
    private val remoteBookDataSource: RemoteBookDataSource,
    private val localBookDataSource: LocalBookDataSource
) : BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> =
        remoteBookDataSource.searchBooks(query)
            .map { dto ->
                dto.results.map { it.toModel() }
            }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> =
        localBookDataSource.getFavoriteBook(bookId)?.let {
            Result.Success(it.description)
        } ?: run {
            remoteBookDataSource
                .getBookDescription(bookId)
                .map { it.description }
        }

    override fun getFavoriteBooks(): Flow<List<Book>> =
        localBookDataSource.getFavoriteBooks().map { bookEntities ->
            bookEntities.map { it.toModel() }
        }

    override fun isBookFavorite(id: String): Flow<Boolean> = localBookDataSource.isBookFavorite(id)

    override suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local> =
        localBookDataSource.markAsFavorite(book.toEntity())

    override suspend fun deleteFromFavorites(id: String) =
        localBookDataSource.deleteFromFavorites(id)
}