package com.composemultiplatform.book

import androidx.compose.ui.window.ComposeUIViewController
import com.composemultiplatform.book.app.App
import com.composemultiplatform.book.app.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) { App() }