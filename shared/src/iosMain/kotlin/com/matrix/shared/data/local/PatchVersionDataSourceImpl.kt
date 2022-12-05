package com.matrix.shared.data.local

import com.matrix.shared.data.local.interfaces.PatchVersionDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal actual class PatchVersionDataSourceImpl : PatchVersionDataSource {
  var _patchVersion: String? = null

  override suspend fun setPatchVersion(patchVersion: String) {
    println(patchVersion)
    _patchVersion = patchVersion
  }

  override fun getPatchVersion(): Flow<String?> = flow {
    emit(_patchVersion)
  }

}