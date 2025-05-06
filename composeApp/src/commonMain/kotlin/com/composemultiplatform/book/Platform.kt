package com.composemultiplatform.book

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform