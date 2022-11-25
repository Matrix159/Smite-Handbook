package com.matrix.materializedsmite

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.matrix.data.service.PatchSyncWorker
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest.plant
import javax.inject.Inject


@HiltAndroidApp
class SmiteApplication : Application(), Configuration.Provider {

  @Inject
  lateinit var workerFactory: HiltWorkerFactory

  @Inject
  lateinit var workManager: WorkManager

  override fun getWorkManagerConfiguration() =
    Configuration.Builder()
      .setWorkerFactory(workerFactory)
      .build()


  // Used to grab string resources from outside activities currently (secrets.xml)

  override fun onCreate() {
    super.onCreate()

    workManager.enqueueUniqueWork(
      "PatchSyncWorker",
      ExistingWorkPolicy.KEEP,
      PatchSyncWorker.startUpSyncWork()
//      PeriodicWorkRequestBuilder<PatchSyncWorker>(12, TimeUnit.HOURS)
//        .setConstraints(constraints)
//        .build()
    )


    if (BuildConfig.DEBUG) {
      plant(DebugTree())
    } else {
      //plant(CrashReportingTree())
    }
    // KMP Logging
  }
}