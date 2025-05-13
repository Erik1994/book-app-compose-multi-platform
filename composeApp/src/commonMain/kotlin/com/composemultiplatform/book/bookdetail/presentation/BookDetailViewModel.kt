package com.composemultiplatform.book.bookdetail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.composemultiplatform.book.app.Route
import com.composemultiplatform.book.bookdetail.domain.BookDescriptionUseCase
import com.composemultiplatform.book.core.domain.onError
import com.composemultiplatform.book.core.domain.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailViewModel(
    private val bookDescriptionUseCase: BookDescriptionUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val bookId = savedStateHandle.toRoute<Route.BookDetail>().id

    private val _state = MutableStateFlow(BookDetailState())
    val state = _state
        .onStart {
            fetchBookDescription()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(TIME_OUT_MILLIS),
            _state.value
        )

    fun onAction(action: BookDetailAction) {
        when (action) {
            is BookDetailAction.OnSelectedBookChange -> _state.update {
                it.copy(
                    book = action.book
                )
            }

            BookDetailAction.OnFavoriteClick -> {

            }

            else -> Unit
        }
    }

    private fun fetchBookDescription() {
        viewModelScope.launch {
            bookDescriptionUseCase(bookId)
                .onSuccess { description ->
                    _state.update {
                        it.copy(
                            book = it.book?.copy(
                                description = description
                            ),
                            isLoading = false
                        )
                    }
                }
                .onError {
                    _state.update {
                        it.copy(
                            book = it.book?.copy(
                                description = null
                            ),
                            isLoading = false
                        )
                    }
                }
        }
    }

    private companion object {
        const val TIME_OUT_MILLIS = 500L
    }

}