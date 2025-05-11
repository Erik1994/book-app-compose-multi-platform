package com.composemultiplatform.book.book.presentation.books

import com.composemultiplatform.book.book.domain.model.Book
import com.composemultiplatform.book.core.presentation.util.UiText

private const val KOTLIN = "Kotlin"
data class BooksState(
    val searchQuery: String = KOTLIN,
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = true,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)
