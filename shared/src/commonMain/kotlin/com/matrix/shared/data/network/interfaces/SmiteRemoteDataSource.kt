package com.matrix.shared.data.network.interfaces

import com.matrix.shared.data.network.model.GodApiResult
import com.matrix.shared.data.network.model.GodSkinApiResult
import com.matrix.shared.data.network.model.ItemApiResult
import com.matrix.shared.data.network.model.PatchVersionInfo


interface SmiteRemoteDataSource {
  suspend fun getGods(): List<GodApiResult>

  suspend fun getGodSkins(godId: Long): List<GodSkinApiResult>

  suspend fun getItems(): List<ItemApiResult>

  suspend fun getPatchVersion(): PatchVersionInfo
}