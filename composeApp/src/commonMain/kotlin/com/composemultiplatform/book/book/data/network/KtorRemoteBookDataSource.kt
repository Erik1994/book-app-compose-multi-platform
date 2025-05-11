package com.composemultiplatform.book.book.data.network

import com.composemultiplatform.book.book.data.dto.SearchResponseDto
import com.composemultiplatform.book.core.data.util.Endpoint
import com.composemultiplatform.book.core.data.util.safeCall
import com.composemultiplatform.book.core.domain.DataError
import com.composemultiplatform.book.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter


class KtorRemoteBookDataSourceImpl(
    private val httpClient: HttpClient
) : RemoteBookDataSource {
    override suspend fun searchBooks(
        query: String,
        resultLimit: Int?
    ): Result<SearchResponseDto, DataError.Remote> = safeCall {
        httpClient.get(
            urlString = Endpoint.Search.url
        ) {
            parameter("q", query)
            parameter("limit", resultLimit)
            parameter("language", "eng")
            parameter("fields", "key,title,author_name,author_key,cover_edition_key,cover_i,ratings_average,ratings_count,first_publish_year,language,number_of_pages_median,edition_count")
        }
    }
}