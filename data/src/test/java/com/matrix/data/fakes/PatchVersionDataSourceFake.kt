package com.matrix.data.fakes

import com.matrix.data.local.interfaces.PatchVersionDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PatchVersionDataSourceFake: PatchVersionDataSource {
  private var localPatchVersion: String? = null

  override suspend fun setPatchVersion(patchVersion: String) {
    localPatchVersion = patchVersion
  }

  override fun getPatchVersion(): Flow<String?> = flow {
    emit(localPatchVersion)
  }
}