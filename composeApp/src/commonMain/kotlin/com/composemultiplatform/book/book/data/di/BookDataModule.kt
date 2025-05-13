package com.composemultiplatform.book.book.data.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.composemultiplatform.book.book.data.database.DatabaseFactory
import com.composemultiplatform.book.book.data.database.FavoriteBookDb
import com.composemultiplatform.book.book.data.local.LocalBookDataSource
import com.composemultiplatform.book.book.data.local.RoomLocalBookDataSource
import com.composemultiplatform.book.book.data.remote.KtorRemoteBookDataSourceImpl
import com.composemultiplatform.book.book.data.remote.RemoteBookDataSource
import com.composemultiplatform.book.book.data.repository.BookRepositoryImpl
import com.composemultiplatform.book.book.domain.repository.BookRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val bookDataModule = module {
    singleOf(::KtorRemoteBookDataSourceImpl).bind<RemoteBookDataSource>()
    singleOf(::RoomLocalBookDataSource).bind<LocalBookDataSource>()
    singleOf(::BookRepositoryImpl).bind<BookRepository>()
    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<FavoriteBookDb>().favoriteBookDao }
}