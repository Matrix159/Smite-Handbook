package com.matrix.data.network.interfaces

import com.matrix.data.model.PatchVersionInfo
import com.matrix.data.network.model.GodApiResult
import com.matrix.data.network.model.GodSkinApiResult
import com.matrix.data.network.model.ItemApiResult


interface SmiteRemoteDataSource {
  suspend fun getGods(): List<GodApiResult>

  suspend fun getGodSkins(godId: Int): List<GodSkinApiResult>

  suspend fun getItems(): List<ItemApiResult>

  suspend fun getPatchVersion(): PatchVersionInfo
}