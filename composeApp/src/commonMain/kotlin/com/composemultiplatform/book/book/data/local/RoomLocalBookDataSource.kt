package com.composemultiplatform.book.book.data.local

import androidx.sqlite.SQLiteException
import com.composemultiplatform.book.book.data.database.BookEntity
import com.composemultiplatform.book.book.data.database.FavoriteBookDao
import com.composemultiplatform.book.core.domain.DataError
import com.composemultiplatform.book.core.domain.EmptyResult
import com.composemultiplatform.book.core.domain.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomLocalBookDataSource(
    private val favoriteBookDao: FavoriteBookDao
) : LocalBookDataSource {

    override fun getFavoriteBooks(): Flow<List<BookEntity>> = favoriteBookDao.getFavoriteBooks()

    override fun isBookFavorite(id: String): Flow<Boolean> = favoriteBookDao
        .getFavoriteBooks()
        .map { bookEntities ->
            bookEntities.any {
                it.id == id
            }
        }

    override suspend fun markAsFavorite(book: BookEntity): EmptyResult<DataError.Local> = try {
        favoriteBookDao.upsert(book)
        Result.Success(Unit)
    } catch (e: SQLiteException) {
        Result.Error(DataError.Local.DISK_FULL)
    }

    override suspend fun getFavoriteBook(id: String): BookEntity? = favoriteBookDao.getFavoriteBook(id)

    override suspend fun deleteFromFavorites(id: String) = favoriteBookDao.deleteFavoriteBook(id)
}