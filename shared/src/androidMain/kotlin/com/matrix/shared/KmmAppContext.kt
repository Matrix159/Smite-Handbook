package com.matrix.shared

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

actual class KmmAppContext private actual constructor() {
  lateinit var androidContext: Context
  companion object {
    @SuppressLint("StaticFieldLeak")
    val appContext: KmmAppContext = KmmAppContext()
  }
}

fun Application.initKmmAppContext() {
  KmmAppContext.appContext.androidContext = this
}