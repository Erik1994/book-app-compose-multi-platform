package com.composemultiplatform.book.bookdetail.presentation.di

import com.composemultiplatform.book.bookdetail.domain.AddFavoriteBookUseCase
import com.composemultiplatform.book.bookdetail.domain.BookDescriptionUseCase
import com.composemultiplatform.book.bookdetail.domain.CheckIsFavoriteBookUseCase
import com.composemultiplatform.book.bookdetail.domain.DeleteFavoriteBookUseCase
import com.composemultiplatform.book.bookdetail.domain.GetAllFavoriteBooksUseCase
import com.composemultiplatform.book.bookdetail.presentation.BookDetailViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val bookDetailPresentationModule = module {
    singleOf(::BookDescriptionUseCase)
    singleOf(::AddFavoriteBookUseCase)
    singleOf(::DeleteFavoriteBookUseCase)
    singleOf(::CheckIsFavoriteBookUseCase)
    singleOf(::GetAllFavoriteBooksUseCase)
    viewModelOf(::BookDetailViewModel)
}