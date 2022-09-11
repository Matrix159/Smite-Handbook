package com.matrix.data.service

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
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
    Timber.d("PATCH WORKER RUNNING...")
    try {
      smiteRepository.syncPatchVersion()
    } catch (ex: Exception) {
      if (ex !is CancellationException) {
        Timber.e(ex)
        return@withContext Result.retry()
      }
      throw ex
    }

    return@withContext Result.success()
  }
}