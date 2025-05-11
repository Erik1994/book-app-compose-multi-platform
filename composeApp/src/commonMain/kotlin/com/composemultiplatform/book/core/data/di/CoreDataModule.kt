package com.composemultiplatform.book.core.data.di

import com.composemultiplatform.book.core.data.appdispatchers.AppDispatchersImpl
import com.composemultiplatform.book.core.data.network.HttpClientFactory
import com.composemultiplatform.book.core.domain.dispatchers.AppDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coreDataModule = module {
    single { HttpClientFactory.create(get()) }
    single<AppDispatchers> { AppDispatchersImpl }
    single<CoroutineDispatcher>(named(AppDispatchers.DISPATCHER_IO)) { AppDispatchersImpl.ioDispatcher }
    single<CoroutineDispatcher>(named(AppDispatchers.DISPATCHER_DEFAULT)) { AppDispatchersImpl.defaultDispatcher }
    single<CoroutineDispatcher>(named(AppDispatchers.DISPATCHER_UI)) { AppDispatchersImpl.mainDispatcher }
}