package com.matrix.shared

// Android
actual class Platform actual constructor() {
    actual val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

//actual fun getPlatform(): Platform = AndroidPlatform()