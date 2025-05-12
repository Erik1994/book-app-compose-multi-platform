package com.composemultiplatform.book.book.presentation.books.util

import kotlin.math.round

fun Double.formatToOneDecimalText(): String = "${round(this * 10) / 10.0}"