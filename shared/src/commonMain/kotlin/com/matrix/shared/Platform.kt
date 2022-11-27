package com.matrix.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
