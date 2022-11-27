package com.matrix.data.service;

import androidx.hilt.work.WorkerAssistedFactory;
import androidx.work.ListenableWorker;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
@InstallIn(SingletonComponent.class)
@OriginatingElement(
    topLevelClass = PatchSyncWorker.class
)
public interface PatchSyncWorker_HiltModule {
  @Binds
  @IntoMap
  @StringKey("com.matrix.data.service.PatchSyncWorker")
  WorkerAssistedFactory<? extends ListenableWorker> bind(PatchSyncWorker_AssistedFactory factory);
}
