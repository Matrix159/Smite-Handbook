package com.matrix.materializedsmite

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SmiteApplication : Application() {
  // Used to grab string resources from outside activities currently (secrets.xml)
  companion object {
    lateinit var instance: SmiteApplication
      private set
  }

  override fun onCreate() {
    super.onCreate()
    instance = this
  }
}