package com.composemultiplatform.book.book.presentation.books

import com.composemultiplatform.book.book.domain.Book
import com.composemultiplatform.book.core.presentation.UiText

private const val KOTLIN = "Kotlin"
data class BooksState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<Book> = books,
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)

private val books = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        imageUrl = "https://test.com",
        description = "Description $it",
        authors = listOf("Erik"),
        languages = emptyList(),
        firstPublishYear = null,
        averageRating = 4.67589,
        ratingCount = 5,
        numEditions = 3,
        numPages = 100
    )
}
