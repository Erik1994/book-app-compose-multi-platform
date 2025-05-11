package com.composemultiplatform.book.book.domain.usecase

import com.composemultiplatform.book.book.domain.model.Book
import com.composemultiplatform.book.book.domain.repository.BookRepository
import com.composemultiplatform.book.core.domain.DataError
import com.composemultiplatform.book.core.domain.Result

class BookSearchUseCase(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(query: String): Result<List<Book>, DataError.Remote> =
        bookRepository.searchBooks(query)
}