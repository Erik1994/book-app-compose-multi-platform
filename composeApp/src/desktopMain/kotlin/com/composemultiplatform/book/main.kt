package com.composemultiplatform.book

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.composemultiplatform.book.app.App
import com.composemultiplatform.book.app.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "BookAppCMP",
        ) {
            App()
        }
    }
}