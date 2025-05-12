package com.composemultiplatform.book.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.composemultiplatform.book.book.presentation.books.BooksScreenRoot
import com.composemultiplatform.book.book.presentation.sharedviewmodel.SelectedBookViewModel
import com.composemultiplatform.book.bookdetail.presentation.BookDetailAction
import com.composemultiplatform.book.bookdetail.presentation.BookDetailScreenRoot
import com.composemultiplatform.book.bookdetail.presentation.BookDetailViewModel
import com.composemultiplatform.book.core.presentation.util.sharedKoinViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.BookGraph
        ) {
            navigation<Route.BookGraph>(
                startDestination = Route.Books
            ) {

                composable<Route.Books> {
                    val selectedBookSharedViewModel = it.sharedKoinViewModel<SelectedBookViewModel>(
                        navController = navController
                    )

                    LaunchedEffect(true) {
                        selectedBookSharedViewModel.onSelectBook(null)
                    }

                    BooksScreenRoot(
                        viewModel = koinViewModel(),
                        onBookClick = { book ->
                            selectedBookSharedViewModel.onSelectBook(book)
                            navController.navigate(
                                route = Route.BookDetail(book.id)
                            )
                        }
                    )
                }

                composable<Route.BookDetail> { entry ->
                    val selectedBookSharedViewModel = entry.sharedKoinViewModel<SelectedBookViewModel>(
                        navController = navController
                    )
                    val bookDetailViewModel = koinViewModel<BookDetailViewModel>()
                    val selectedBook by selectedBookSharedViewModel.selectedBook.collectAsStateWithLifecycle()

                    LaunchedEffect(selectedBook) {
                        selectedBook?.let {
                            bookDetailViewModel.onAction(BookDetailAction.OnSelectedBookChange(it))
                        }
                    }

                    BookDetailScreenRoot(
                        viewModel = bookDetailViewModel,
                        onBackClick = {
                            navController.navigateUp()
                        }
                    )
                }
            }
        }
    }
}

