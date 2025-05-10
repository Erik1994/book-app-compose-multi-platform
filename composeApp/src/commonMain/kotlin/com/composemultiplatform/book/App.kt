package com.composemultiplatform.book

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.composemultiplatform.book.book.presentation.books.BooksScreenRoot
import com.composemultiplatform.book.book.presentation.books.BooksViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        BooksScreenRoot(
            viewModel = remember { BooksViewModel() },
            onBookClick = {

            }
        )
    }
}