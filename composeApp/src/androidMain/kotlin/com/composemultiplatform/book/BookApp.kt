package com.composemultiplatform.book

import android.app.Application
import com.composemultiplatform.book.di.initKoin
import org.koin.android.ext.koin.androidContext

class BookApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BookApp)
        }
    }
}