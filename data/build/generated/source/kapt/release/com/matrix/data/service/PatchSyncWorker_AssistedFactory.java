package com.matrix.data.service;

import androidx.hilt.work.WorkerAssistedFactory;
import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface PatchSyncWorker_AssistedFactory extends WorkerAssistedFactory<PatchSyncWorker> {
}
