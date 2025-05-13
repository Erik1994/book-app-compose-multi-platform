package com.composemultiplatform.book.bookdetail.domain

import com.composemultiplatform.book.book.domain.model.Book
import com.composemultiplatform.book.book.domain.repository.BookRepository
import com.composemultiplatform.book.core.domain.DataError
import com.composemultiplatform.book.core.domain.EmptyResult

class AddFavoriteBookUseCase(
    private val repository: BookRepository
) {
    suspend operator fun invoke(book: Book): EmptyResult<DataError.Local> = repository.markAsFavorite(book)
}