package com.matrix.shared.data.contracts

import com.matrix.shared.data.model.builds.BuildInformation
import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.model.items.ItemInformation
import com.matrix.shared.data.model.skins.GodSkinInformation
import kotlinx.coroutines.flow.Flow

interface SmiteRepository {
  fun getGods(): Flow<List<GodInformation>>
  fun getGod(godId: Long): Flow<GodInformation>
  fun getGodSkins(godId: Long): Flow<List<GodSkinInformation>>
  fun getItems(): Flow<List<ItemInformation>>
  fun getItem(itemId: Long): Flow<ItemInformation>
  fun getBuilds(): Flow<List<BuildInformation>>
  fun getBuild(buildId: Long): Flow<BuildInformation>
  suspend fun saveBuild(buildInformation: BuildInformation)
  suspend fun updateGodInBuild(buildId: Long, godId: Long)
  suspend fun updateItemsInBuild(buildId: Long, itemIds: List<Long>)
  suspend fun deleteBuild(buildInformation: BuildInformation)
  suspend fun sync()
}