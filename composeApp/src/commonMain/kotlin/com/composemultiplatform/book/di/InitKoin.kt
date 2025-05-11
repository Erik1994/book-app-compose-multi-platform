package com.composemultiplatform.book.di

import com.composemultiplatform.book.book.data.di.bookDataModule
import com.composemultiplatform.book.book.presentation.di.bookPresentationModule
import com.composemultiplatform.book.core.data.di.coreDataModule
import com.composemultiplatform.book.core.data.di.platformModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            platformModule,
            coreDataModule,
            bookDataModule,
            bookPresentationModule
        )
    }
}