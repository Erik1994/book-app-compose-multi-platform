package com.composemultiplatform.book.bookdetail.domain

import com.composemultiplatform.book.book.domain.model.Book
import com.composemultiplatform.book.book.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class GetAllFavoriteBooksUseCase(
    private val repository: BookRepository
) {
    operator fun invoke(): Flow<List<Book>> = repository.getFavoriteBooks()
}