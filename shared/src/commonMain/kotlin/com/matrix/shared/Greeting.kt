package com.matrix.shared

class Greeting {
    private val platform: Platform = getPlatform()

    fun greeting(): String {
        return "Hellooo, ${platform.name}!"
    }
}