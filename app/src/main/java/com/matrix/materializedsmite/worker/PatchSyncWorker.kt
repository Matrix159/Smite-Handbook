package com.matrix.materializedsmite.worker

import android.R
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
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.matrix.shared.data.contracts.SmiteRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber


const val PatchSyncWorkerNotificationId = 1

class PatchSyncWorker constructor(
  private val appContext: Context,
  workerParams: WorkerParameters,
) : CoroutineWorker(appContext, workerParams), KoinComponent {

  private val smiteRepository: SmiteRepository by inject()
  private val notificationManager =
    appContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

  override suspend fun getForegroundInfo(): ForegroundInfo {
    //val chan = NotificationManager.chann
    return ForegroundInfo(
      PatchSyncWorkerNotificationId,
      createNotification()
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

  /**
   * Create the notification and required channel (O+) for running work in a foreground service.
   */
  private fun createNotification(): Notification {
    val channelId = "1"
    val title = "title"
    val cancel = "cancel"
    val name = "name"
    // This PendingIntent can be used to cancel the Worker.
    val intent = WorkManager.getInstance(applicationContext).createCancelPendingIntent(id)

    val builder = Notification.Builder(applicationContext, channelId)
      .setContentTitle(title)
      .setTicker(title)
      .setSmallIcon(R.drawable.ic_lock_idle_alarm)
      .setOngoing(true)
      .addAction(R.drawable.ic_delete, cancel, intent)
    createNotificationChannel(channelId, name).also {
      builder.setChannelId(it.id)
    }
    return builder.build()
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



