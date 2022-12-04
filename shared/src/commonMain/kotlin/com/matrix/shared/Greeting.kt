package com.matrix.shared

class Greeting {
    private val platform: Platform = Platform()

    fun greeting(): String {
        return "Hellooo, ${platform.name}!"
    }
}