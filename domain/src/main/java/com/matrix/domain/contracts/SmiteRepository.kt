package com.matrix.domain.contracts

import com.matrix.domain.models.BuildInformation
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkinInformation
import com.matrix.domain.models.ItemInformation

interface SmiteRepository {
  suspend fun getGods(refresh: Boolean = false): List<GodInformation>
  suspend fun getGodSkins(godId: Int): List<GodSkinInformation>
  suspend fun getItems(refresh: Boolean = false): List<ItemInformation>
  suspend fun getBuilds(): List<BuildInformation>
  suspend fun syncPatchVersion()
}