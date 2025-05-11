package com.composemultiplatform.book.core.data.util

sealed class Endpoint(val url: String) {
    data object Search: Endpoint("$BASE_URL/search.json")

    private companion object {
        const val BASE_URL = "https://openlibrary.org"
    }
}