package com.matrix.data.service

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkerParameters
import com.matrix.domain.contracts.SmiteRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

@HiltWorker
class PatchSyncWorker @AssistedInject constructor(
  @Assisted private val appContext: Context,
  @Assisted private val workerParams: WorkerParameters,
  private val smiteRepository: SmiteRepository
) : CoroutineWorker(appContext, workerParams) {
  override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
    Timber.d("PATCH SYNC WORKER RUNNING...")
    try {
      smiteRepository.sync()
    } catch (ex: Exception) {
      if (ex !is CancellationException) {
        Timber.e(ex)
        return@withContext Result.retry()
      }
      throw ex
    }

    return@withContext Result.success()
  }

  companion object {
    /**
     * Expedited one time work to sync data on app startup
     */
    fun startUpSyncWork() = OneTimeWorkRequestBuilder<PatchSyncWorker>()
      .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
      .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
      .build()
  }
}



