package com.composemultiplatform.book.book.presentation.books

import com.composemultiplatform.book.book.domain.Book

sealed interface BooksAction {
    data class OnSearchQueryChange(val query: String) : BooksAction
    data class OnBookClick(val book: Book) : BooksAction
    data class OnTabSelected(val index: Int) : BooksAction
}