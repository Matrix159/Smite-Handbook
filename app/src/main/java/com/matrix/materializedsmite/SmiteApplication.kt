package com.matrix.materializedsmite

import android.app.Application
import com.matrix.presentation.injection.presentationKoinModule
import com.matrix.shared.data.injection.androidKoinModule
import com.matrix.shared.data.injection.appKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest.plant


//@HiltAndroidApp
class SmiteApplication : Application()/*, Configuration.Provider*/ {

  //@Inject
  //lateinit var workerFactory: HiltWorkerFactory

//  @Inject
//  lateinit var workManager: WorkManager

//  override fun getWorkManagerConfiguration() =
//    Configuration.Builder()
//      .setWorkerFactory(workerFactory)
//      .build()


  // Used to grab string resources from outside activities currently (secrets.xml)

  override fun onCreate() {
    super.onCreate()

//    workManager.enqueueUniqueWork(
//      "PatchSyncWorker",
//      ExistingWorkPolicy.KEEP,
//      PatchSyncWorker.startUpSyncWork()
////      PeriodicWorkRequestBuilder<PatchSyncWorker>(12, TimeUnit.HOURS)
////        .setConstraints(constraints)
////        .build()
//    )


    if (BuildConfig.DEBUG) {
      plant(DebugTree())
    } else {
      //plant(CrashReportingTree())
    }

    // TODO: Need to hook patch sync worker back up
    startKoin {
      androidContext(this@SmiteApplication)
      androidLogger()
      modules(presentationKoinModule() + androidKoinModule() + appKoinModule())
    }
  }
}