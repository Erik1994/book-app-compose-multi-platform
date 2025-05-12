package com.composemultiplatform.book.bookdetail.presentation

import com.composemultiplatform.book.book.domain.model.Book

data class BookDetailState(
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val book: Book? = null
)
