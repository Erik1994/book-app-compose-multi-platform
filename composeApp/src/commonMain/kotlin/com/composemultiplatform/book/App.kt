package com.composemultiplatform.book

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.composemultiplatform.book.book.presentation.books.BooksScreenRoot
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        BooksScreenRoot(
            viewModel = koinViewModel(),
            onBookClick = {

            }
        )
    }
}