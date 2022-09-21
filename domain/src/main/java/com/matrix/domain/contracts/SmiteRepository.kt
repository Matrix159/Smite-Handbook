package com.matrix.domain.contracts

import com.matrix.domain.models.BuildInformation
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkinInformation
import com.matrix.domain.models.ItemInformation
import kotlinx.coroutines.flow.Flow

interface SmiteRepository {
  fun getGods(refresh: Boolean = false): Flow<List<GodInformation>>
  fun getGod(godId: Int): Flow<GodInformation>
  suspend fun syncGods()
  fun getGodSkins(godId: Int): Flow<List<GodSkinInformation>>
  fun getItems(refresh: Boolean = false): Flow<List<ItemInformation>>
  suspend fun syncItems()
  fun getBuilds(): Flow<List<BuildInformation>>
  suspend fun createBuild(buildInformation: BuildInformation)

  suspend fun deleteBuild(buildInformation: BuildInformation)
  suspend fun syncWithPatchVersion(blockToSync: suspend () -> Unit)
}