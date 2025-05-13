package com.composemultiplatform.book.bookdetail.presentation.di

import com.composemultiplatform.book.bookdetail.domain.BookDescriptionUseCase
import com.composemultiplatform.book.bookdetail.presentation.BookDetailViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val bookDetailPresentationModule = module {
    singleOf(::BookDescriptionUseCase)
    viewModelOf(::BookDetailViewModel)
}