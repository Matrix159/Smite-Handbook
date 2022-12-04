package com.matrix.shared

import platform.UIKit.UIDevice

actual class Platform actual constructor() {
    actual val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

//actual fun getPlatform(): Platform = IOSPlatform()