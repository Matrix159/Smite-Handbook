package com.matrix159.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform