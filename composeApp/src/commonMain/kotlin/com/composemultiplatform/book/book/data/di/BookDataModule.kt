package com.composemultiplatform.book.book.data.di

import com.composemultiplatform.book.book.data.network.KtorRemoteBookDataSourceImpl
import com.composemultiplatform.book.book.data.network.RemoteBookDataSource
import com.composemultiplatform.book.book.data.repository.BookRepositoryImpl
import com.composemultiplatform.book.book.domain.repository.BookRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val bookDataModule = module {
    singleOf(::KtorRemoteBookDataSourceImpl).bind<RemoteBookDataSource>()
    singleOf(::BookRepositoryImpl).bind<BookRepository>()
}