package com.composemultiplatform.book.book.presentation.di

import com.composemultiplatform.book.book.domain.usecase.BookSearchUseCase
import com.composemultiplatform.book.book.presentation.books.BooksViewModel
import com.composemultiplatform.book.book.presentation.sharedviewmodel.SelectedBookViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val bookPresentationModule = module {
    singleOf(::BookSearchUseCase)
    viewModelOf(::BooksViewModel)
    viewModelOf(::SelectedBookViewModel)
}