package com.composemultiplatform.book.bookdetail.domain

import com.composemultiplatform.book.book.domain.repository.BookRepository

class DeleteFavoriteBookUseCase(
    private val repository: BookRepository
) {
    suspend operator fun invoke(id: String) = repository.deleteFromFavorites(id)
}