package com.matrix.data.network.interfaces

import com.matrix.data.model.PatchVersionInfo
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkin
import com.matrix.domain.models.Item


interface SmiteRemoteDataSource {
  suspend fun getGods(): List<GodInformation>

  suspend fun getGodSkins(godId: Int): List<GodSkin>

  suspend fun getItems(): List<Item>

  suspend fun getPatchVersion(): PatchVersionInfo
}