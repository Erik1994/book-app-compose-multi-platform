package com.composemultiplatform.book.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object BookGraph : Route

    @Serializable
    data object Books : Route

    @Serializable
    data class BookDetail(val id: String) : Route
}