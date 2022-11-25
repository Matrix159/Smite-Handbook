package com.matrix.domain.contracts

import com.matrix.domain.models.BuildInformation
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkinInformation
import com.matrix.domain.models.ItemInformation
import kotlinx.coroutines.flow.Flow

interface SmiteRepository {
  fun getGods(): Flow<List<GodInformation>>
  fun getGod(godId: Int): Flow<GodInformation>
  suspend fun syncGods()
  fun getGodSkins(godId: Int): Flow<List<GodSkinInformation>>
  fun getItems(): Flow<List<ItemInformation>>
  fun getItem(itemId: Int): Flow<ItemInformation>
  suspend fun syncItems()
  fun getBuilds(): Flow<List<BuildInformation>>
  fun getBuild(buildId: Int): Flow<BuildInformation>
  suspend fun createBuild(buildInformation: BuildInformation)
  suspend fun deleteBuild(buildInformation: BuildInformation)
  suspend fun sync()
}