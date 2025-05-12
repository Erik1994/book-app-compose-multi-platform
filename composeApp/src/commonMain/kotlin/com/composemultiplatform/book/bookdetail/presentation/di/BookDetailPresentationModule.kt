package com.composemultiplatform.book.bookdetail.presentation.di

import com.composemultiplatform.book.bookdetail.presentation.BookDetailViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val bookDetailPresentationModule = module {
    viewModelOf(::BookDetailViewModel)
}