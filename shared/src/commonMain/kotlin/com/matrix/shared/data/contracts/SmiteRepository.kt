package com.matrix.shared.data.contracts

import com.matrix.shared.data.model.builds.BuildInformation
import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.model.skins.GodSkinInformation
import com.matrix.shared.data.model.items.ItemInformation
import kotlinx.coroutines.flow.Flow

interface SmiteRepository {
  fun getGods(): Flow<List<GodInformation>>
  fun getGod(godId: Int): Flow<GodInformation>
  fun getGodSkins(godId: Int): Flow<List<GodSkinInformation>>
  fun getItems(): Flow<List<ItemInformation>>
  fun getItem(itemId: Int): Flow<ItemInformation>
  fun getBuilds(): Flow<List<BuildInformation>>
  fun getBuild(buildId: Int): Flow<BuildInformation>
  suspend fun createBuild(buildInformation: BuildInformation)
  suspend fun deleteBuild(buildInformation: BuildInformation)
  suspend fun sync()
}