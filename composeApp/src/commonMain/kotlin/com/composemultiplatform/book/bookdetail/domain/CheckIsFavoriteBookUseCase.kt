package com.composemultiplatform.book.bookdetail.domain

import com.composemultiplatform.book.book.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class CheckIsFavoriteBookUseCase(
    private val repository: BookRepository
) {
    operator fun invoke(id: String): Flow<Boolean> = repository.isBookFavorite(id)
}