package com.matrix.materializedsmite

import android.app.Application
import com.matrix.presentation.injection.presentationKoinModule
import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.di.initKoin
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import timber.log.Timber
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
    initKoin {
      androidContext(this@SmiteApplication)
      androidLogger()
      modules(presentationKoinModule())
    }

    val scope = CoroutineScope(
      Dispatchers.Default +
      CoroutineExceptionHandler { context, throwable ->
        Timber.e(throwable)
      }
    )
    scope.launch {
      val smiteRepository: SmiteRepository by inject()
      smiteRepository.sync()
    }
  }
}