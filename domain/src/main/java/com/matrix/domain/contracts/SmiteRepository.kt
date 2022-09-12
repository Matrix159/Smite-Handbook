package com.matrix.domain.contracts

import com.matrix.domain.models.BuildInformation
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkinInformation
import com.matrix.domain.models.ItemInformation
import kotlinx.coroutines.flow.Flow

interface SmiteRepository {
  suspend fun getGods(refresh: Boolean = false): List<GodInformation>
  suspend fun getGodSkins(godId: Int): List<GodSkinInformation>
  suspend fun getItems(refresh: Boolean = false): List<ItemInformation>
  fun getBuilds(): Flow<List<BuildInformation>>
  suspend fun createBuild(buildInformation: BuildInformation)

  suspend fun deleteBuild(buildInformation: BuildInformation)
  suspend fun syncPatchVersion()
}