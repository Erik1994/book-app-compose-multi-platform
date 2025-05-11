package com.composemultiplatform.book.book.presentation.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composemultiplatform.book.book.domain.model.Book
import com.composemultiplatform.book.book.domain.usecase.BookSearchUseCase
import com.composemultiplatform.book.core.domain.onError
import com.composemultiplatform.book.core.domain.onSuccess
import com.composemultiplatform.book.core.presentation.util.toUiText
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BooksViewModel(
    private val bookSearchUseCase: BookSearchUseCase
) : ViewModel() {

    private var cachedBooks: List<Book> = emptyList()
    private var searchJob: Job? = null

    private val _state = MutableStateFlow(BooksState())
    val state = _state
        .onStart {
            if (cachedBooks.isEmpty()) {
                observeSearchQuery()
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(TIME_OUT_MILLIS),
            _state.value
        )

    fun onAction(action: BooksAction) {
        when (action) {
            is BooksAction.OnBookClick -> {

            }

            is BooksAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }

            is BooksAction.OnTabSelected -> {
                _state.update {
                    it.copy(selectedTabIndex = action.index)
                }
            }
        }
    }

    private fun observeSearchQuery() {
        state
            .map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(DEBOUNCE_MILLIS)
            .onEach { query ->
                when {
                    query.isBlank() -> _state.update {
                        it.copy(
                            errorMessage = null,
                            isLoading = false,
                            searchResults = cachedBooks
                        )
                    }

                    query.length >= QUERY_LENGTH_MIN_LIMIT -> {
                        searchJob?.children
                        searchJob = searchBooks(query)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun searchBooks(query: String) = viewModelScope.launch {
        _state.update {
            it.copy(isLoading = true)
        }

        bookSearchUseCase(query)
            .onSuccess { books ->
                cachedBooks = books
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = null,
                        searchResults = books
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        searchResults = emptyList(),
                        isLoading = false,
                        errorMessage = error.toUiText()
                    )
                }
            }

    }

    private companion object {
        const val DEBOUNCE_MILLIS = 500L
        const val TIME_OUT_MILLIS = 500L
        const val QUERY_LENGTH_MIN_LIMIT = 2
    }
}