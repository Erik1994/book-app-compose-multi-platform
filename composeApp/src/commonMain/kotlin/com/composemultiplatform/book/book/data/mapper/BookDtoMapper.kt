package com.composemultiplatform.book.book.data.mapper

import com.composemultiplatform.book.book.data.dto.SearchBookDto
import com.composemultiplatform.book.book.domain.model.Book

fun SearchBookDto.toModel(): Book = Book (
    id = id,
    title = title,
    imageUrl = if (coverKey != null) {
        "https://covers.openlibrary.org/b/olid/${coverKey}-L.jpg"
    } else {
        "https://covers.openlibrary.org/b/olid/${coverAlternativeKey}-L.jpg"
    },
    authors = authorNames.orEmpty(),
    description = null,
    languages = languages.orEmpty(),
    firstPublishYear = firstPublishYear.toString(),
    averageRating = ratingsAverage,
    numPages = numPagesMedian,
    numEditions = numEditions ?: 0,
    ratingCount = ratingsCount
)