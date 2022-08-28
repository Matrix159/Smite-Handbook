package com.matrix.data.service

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.matrix.domain.contracts.SmiteRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import timber.log.Timber


@HiltWorker
class PatchSyncWorker @AssistedInject constructor(
  @Assisted private val appContext: Context,
  @Assisted private val workerParams: WorkerParameters,
  private val smiteRepository: SmiteRepository
) : Worker(appContext, workerParams) {
  override fun doWork(): Result {
    Timber.i("I'm doing work here")
    return Result.success()
  }
}