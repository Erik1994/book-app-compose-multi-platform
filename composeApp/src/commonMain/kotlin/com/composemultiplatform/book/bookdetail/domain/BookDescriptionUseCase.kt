package com.composemultiplatform.book.bookdetail.domain

import com.composemultiplatform.book.book.domain.repository.BookRepository
import com.composemultiplatform.book.core.domain.DataError
import com.composemultiplatform.book.core.domain.Result

class BookDescriptionUseCase(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(bookWorkId: String): Result<String?, DataError> =
        bookRepository.getBookDescription(bookWorkId)
}