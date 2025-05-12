package com.composemultiplatform.book.bookdetail.presentation

import com.composemultiplatform.book.book.domain.model.Book

sealed interface BookDetailAction {
    data object OnBackClick : BookDetailAction
    data object OnFavoriteClick : BookDetailAction
    data class OnSelectedBookChange(val book: Book) : BookDetailAction
}