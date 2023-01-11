package com.matrix.materializedsmite.worker

import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkerParameters
import com.matrix.shared.data.contracts.SmiteRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber

const val PatchSyncWorkerNotificationId = 56767116

class PatchSyncWorker constructor(
  private val appContext: Context,
  workerParams: WorkerParameters,
) : CoroutineWorker(appContext, workerParams), KoinComponent {

  private val smiteRepository: SmiteRepository by inject()
  override suspend fun getForegroundInfo(): ForegroundInfo {
    return ForegroundInfo(
      PatchSyncWorkerNotificationId,
      Notification.Builder(appContext, NotificationChannel.DEFAULT_CHANNEL_ID)
        .setContentTitle("Retrieving latest information for Smite Handbook")
        .setContentText("subject")
        .setSmallIcon(R.drawable.bottom_bar)
        //.setLargeIcon(aBitmap)
        .build()
    )
  }

  override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
    Timber.d("PATCH SYNC WORKER RUNNING...")
    try {
      smiteRepository.sync()
    } catch (ex: Exception) {
      if (ex !is CancellationException) {
        Timber.e(ex.stackTraceToString())
        return@withContext Result.retry()
      }
      throw ex
    }

    return@withContext Result.success()
  }

  companion object {
    const val WORK_NAME = "PatchSyncWorker"

    /**
     * Expedited one time work to sync data on app startup
     */
    fun startUpSyncWork() = OneTimeWorkRequestBuilder<PatchSyncWorker>()
      .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
      .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
      .build()
  }
}



