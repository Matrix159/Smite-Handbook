package com.matrix.data.local.interfaces

import kotlinx.coroutines.flow.Flow

interface PatchVersionDataSource {
  suspend fun setPatchVersion(patchVersion: String)
  fun getPatchVersion(): Flow<String?>
}