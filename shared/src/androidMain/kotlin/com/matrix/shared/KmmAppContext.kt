package com.matrix.shared

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.matrix.SmiteHandbookDatabase
import com.matrix.shared.sqldelight.DatabaseDriverFactory

actual class KmmAppContext private actual constructor() {
  lateinit var androidContext: Context
  lateinit var _database: SmiteHandbookDatabase
  companion object {
    @SuppressLint("StaticFieldLeak")
    val appContext: KmmAppContext = KmmAppContext()
  }

  actual fun getDatabase(): SmiteHandbookDatabase {
    return _database
  }
}

fun Application.initKmmAppContext() {
  KmmAppContext.appContext.androidContext = this
  KmmAppContext.appContext._database = SmiteHandbookDatabase(DatabaseDriverFactory(this).createDriver())
}