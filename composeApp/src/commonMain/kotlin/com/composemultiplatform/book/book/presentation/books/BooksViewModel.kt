package com.composemultiplatform.book.book.presentation.books

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BooksViewModel: ViewModel() {
    private val _state = MutableStateFlow(BooksState())
    val state = _state.asStateFlow()

    fun onAction(action: BooksAction) {
        when(action) {
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
}