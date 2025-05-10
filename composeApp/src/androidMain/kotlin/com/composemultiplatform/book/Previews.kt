package com.composemultiplatform.book

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.composemultiplatform.book.book.domain.Book
import com.composemultiplatform.book.book.presentation.books.BooksScreen
import com.composemultiplatform.book.book.presentation.books.BooksState
import com.composemultiplatform.book.book.presentation.books.component.SearchBar
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SearchBarPreview() {
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxWidth().background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            SearchBar(
                "",
                onSearchQueryChange = {},
                onImeSearch = {},
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

private val books = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        imageUrl = "https://test.com",
        description = "Description $it",
        authors = listOf("Erik"),
        languages = emptyList(),
        firstPublishYear = null,
        averageRating = 4.67589,
        ratingCount = 5,
        numEditions = 3,
        numPages = 100
    )
}

@Preview
@Composable
private fun BookListScreenPreview() {
    BooksScreen(
        state = BooksState(searchResults = books),
        onAction = {}
    )
}
