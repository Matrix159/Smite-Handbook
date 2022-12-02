package com.matrix.shared.data.network.interfaces

import com.matrix.shared.data.network.model.PatchVersionInfo
import com.matrix.shared.data.network.model.GodApiResult
import com.matrix.shared.data.network.model.GodSkinApiResult
import com.matrix.shared.data.network.model.ItemApiResult


interface SmiteRemoteDataSource {
  suspend fun getGods(): List<GodApiResult>

  suspend fun getGodSkins(godId: Int): List<GodSkinApiResult>

  suspend fun getItems(): List<ItemApiResult>

  suspend fun getPatchVersion(): PatchVersionInfo
}