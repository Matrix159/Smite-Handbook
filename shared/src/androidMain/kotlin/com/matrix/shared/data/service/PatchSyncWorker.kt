//package com.matrix.shared.data.service
//
//import androidx.hilt.work.HiltWorker
//import androidx.work.Constraints
//import androidx.work.CoroutineWorker
//import androidx.work.NetworkType
//import androidx.work.OneTimeWorkRequestBuilder
//import androidx.work.OutOfQuotaPolicy
//import androidx.work.WorkerParameters
//import co.touchlab.kermit.Logger
//import com.matrix.shared.KmmAppContext
//import com.matrix.shared.data.contracts.SmiteRepository
//import dagger.assisted.Assisted
//import dagger.assisted.AssistedInject
//import kotlinx.coroutines.CancellationException
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//@HiltWorker
//class PatchSyncWorker @AssistedInject constructor(
//  @Assisted override val appContext: KmmAppContext,
//  @Assisted private val workerParams: WorkerParameters,
//  override val smiteRepository: SmiteRepository
//) : CoroutineWorker(appContext.androidContext, workerParams), IPatchSyncWorker {
//
//  private val logger = Logger.withTag(PatchSyncWorker::class.java.simpleName)
//
//  override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
//    logger.d("PATCH SYNC WORKER RUNNING...")
//    try {
//      smiteRepository.sync()
//    } catch (ex: Exception) {
//      if (ex !is CancellationException) {
//        logger.e(ex.stackTraceToString())
//        return@withContext Result.retry()
//      }
//      throw ex
//    }
//
//    return@withContext Result.success()
//  }
//
//  companion object {
//    /**
//     * Expedited one time work to sync data on app startup
//     */
//    fun startUpSyncWork() = OneTimeWorkRequestBuilder<PatchSyncWorker>()
//      .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
//      .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
//      .build()
//  }
//}
//
//
//
