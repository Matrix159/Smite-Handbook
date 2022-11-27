package com.matrix.shared.data.local

import com.matrix.shared.KmmAppContext
import com.matrix.shared.data.local.interfaces.PatchVersionDataSource
import kotlinx.coroutines.flow.Flow

actual class PatchVersionDataSourceImpl : PatchVersionDataSource {

  override suspend fun setPatchVersion(patchVersion: String) {
    TODO()
  }

  override fun getPatchVersion(): Flow<String?> {
    TODO()
  }

  actual val kmmAppContext: KmmAppContext
    get() = TODO("Not yet implemented")
}