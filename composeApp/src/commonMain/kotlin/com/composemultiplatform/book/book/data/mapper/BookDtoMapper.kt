package com.composemultiplatform.book.book.data.mapper

import com.composemultiplatform.book.book.data.database.BookEntity
import com.composemultiplatform.book.book.data.dto.SearchBookDto
import com.composemultiplatform.book.book.domain.model.Book

fun SearchBookDto.toModel(): Book = Book (
    id = id.substringAfterLast("/"),
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

fun Book.toEntity(): BookEntity = BookEntity(
    id = id,
    title = title,
    description = description,
    imageUrl = imageUrl,
    languages = languages,
    authors = authors,
    firstPublishYear = firstPublishYear,
    ratingsAverage = averageRating,
    ratingsCount = ratingCount,
    numPagesMedian = numPages,
    numEditions = numEditions
)

fun BookEntity.toModel(): Book = Book(
    id = id,
    title = title,
    description = description,
    imageUrl = imageUrl,
    languages = languages,
    authors = authors,
    firstPublishYear = firstPublishYear,
    averageRating = ratingsAverage,
    ratingCount = ratingsCount,
    numPages = numPagesMedian,
    numEditions = numEditions
)