// Generated by Dagger (https://dagger.dev).
package com.matrix.data.service;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.matrix.domain.contracts.SmiteRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class PatchSyncWorker_Factory {
  private final Provider<SmiteRepository> smiteRepositoryProvider;

  public PatchSyncWorker_Factory(Provider<SmiteRepository> smiteRepositoryProvider) {
    this.smiteRepositoryProvider = smiteRepositoryProvider;
  }

  public PatchSyncWorker get(Context appContext, WorkerParameters workerParams) {
    return newInstance(appContext, workerParams, smiteRepositoryProvider.get());
  }

  public static PatchSyncWorker_Factory create(Provider<SmiteRepository> smiteRepositoryProvider) {
    return new PatchSyncWorker_Factory(smiteRepositoryProvider);
  }

  public static PatchSyncWorker newInstance(Context appContext, WorkerParameters workerParams,
      SmiteRepository smiteRepository) {
    return new PatchSyncWorker(appContext, workerParams, smiteRepository);
  }
}
