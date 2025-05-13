package com.composemultiplatform.book.book.data.network

import com.composemultiplatform.book.book.data.dto.BookWorkDto
import com.composemultiplatform.book.book.data.dto.SearchResponseDto
import com.composemultiplatform.book.core.domain.DataError
import com.composemultiplatform.book.core.domain.Result

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, DataError.Remote>

    suspend fun getBookDescription(bookWorkId: String): Result<BookWorkDto, DataError.Remote>
}