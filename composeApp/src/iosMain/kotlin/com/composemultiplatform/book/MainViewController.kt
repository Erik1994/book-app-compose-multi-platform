package com.composemultiplatform.book

import androidx.compose.ui.window.ComposeUIViewController
import com.composemultiplatform.book.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) { App() }