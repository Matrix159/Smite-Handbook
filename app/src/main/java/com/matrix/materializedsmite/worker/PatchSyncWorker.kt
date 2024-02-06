package com.matrix.materializedsmite.worker

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkerParameters
import com.matrix.materializedsmite.R
import com.matrix.shared.data.contracts.SmiteRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber

class PatchSyncWorker(
  appContext: Context,
  workerParams: WorkerParameters,
) : CoroutineWorker(appContext, workerParams), KoinComponent {
  private val patchSyncWorkerNotificationId = 1
  private val smiteRepository: SmiteRepository by inject()
  private val notificationManager =
    appContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

  override suspend fun getForegroundInfo(): ForegroundInfo {
    return ForegroundInfo(
      patchSyncWorkerNotificationId,
      createNotification()
    )
  }

  override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
    Timber.d("PATCH SYNC WORKER RUNNING...")
    try {
      smiteRepository.sync()
    } catch (ex: CancellationException) {
      throw ex
    } catch (ex: Exception) {
      Timber.e(ex.stackTraceToString())
      return@withContext Result.retry()
    }

    return@withContext Result.success()
  }

  /**
   * Create the notification and required channel (O+) for running work in a foreground service.
   */
  private fun createNotification(): Notification {
    val channelId = "1"
    val title = "Loading information from server..."
    val name = "Syncing Information"

    return Notification.Builder(applicationContext, channelId)
      .setContentTitle(title)
      .setTicker(title)
      .setSmallIcon(R.drawable.sync_24)
      .setOngoing(true)
      .also { builder ->
        createNotificationChannel(channelId, name).also {
          builder.setChannelId(it.id)
        }
      }
      .build()
  }

  /**
   * Create the required notification channel for O+ devices.
   */
  private fun createNotificationChannel(
    channelId: String,
    name: String,
  ): NotificationChannel {
    return NotificationChannel(
      channelId, name, NotificationManager.IMPORTANCE_LOW
    ).also { channel ->
      notificationManager.createNotificationChannel(channel)
    }
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



