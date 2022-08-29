package com.matrix.data.local.interfaces

import kotlinx.coroutines.flow.Flow

interface DataStoreSource {
  suspend fun setPatchVersion(patchVersion: String)
  fun getPatchVersion(): Flow<String?>
}