package com.composemultiplatform.book.book.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

const val BOOK_TABLE = "favorite_book_table"
@Entity(tableName = BOOK_TABLE)
data class BookEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val title: String,
    val description: String?,
    val imageUrl: String,
    val languages: List<String>,
    val authors: List<String>,
    val firstPublishYear: String?,
    val ratingsAverage: Double?,
    val ratingsCount: Int?,
    val numPagesMedian: Int?,
    val numEditions: Int
)
