package com.matrix.materializedsmite

import android.app.Application
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.matrix.materializedsmite.worker.PatchSyncWorker
import com.matrix.presentation.injection.presentationKoinModule
import com.matrix.shared.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import timber.log.Timber
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest.plant


class SmiteApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    if (BuildConfig.DEBUG) {
      plant(DebugTree())
    } else {
      plant(CrashReportingTree())
    }

    WorkManager
      .getInstance(this)
      .enqueueUniqueWork(
        PatchSyncWorker.WORK_NAME,
        ExistingWorkPolicy.KEEP,
        PatchSyncWorker.startUpSyncWork()
      )

    initKoin {
      androidContext(this@SmiteApplication)
      androidLogger()
      modules(presentationKoinModule())
    }
  }
}