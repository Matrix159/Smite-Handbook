package com.matrix.data.fakes

import com.matrix.data.builder.getMockGodApiResult
import com.matrix.data.model.PatchVersionInfo
import com.matrix.data.network.interfaces.SmiteRemoteDataSource
import com.matrix.data.network.model.GodApiResult
import com.matrix.data.network.model.GodSkinApiResult
import com.matrix.data.network.model.ItemApiResult

class SmiteRemoteDataSourceFake: SmiteRemoteDataSource {
  private var godsToReturn = mutableListOf(getMockGodApiResult(1), getMockGodApiResult(2))
  override suspend fun getGods(): List<GodApiResult> {
    return godsToReturn
  }

  override suspend fun getGodSkins(godId: Int): List<GodSkinApiResult> {
    TODO("Not yet implemented")
  }

  override suspend fun getItems(): List<ItemApiResult> {
    TODO("Not yet implemented")
  }

  override suspend fun getPatchVersion(): PatchVersionInfo {
    TODO("Not yet implemented")
  }

  fun increaseReturnedGodsByOne() {
    val latestId = godsToReturn.maxBy { it.id }.id
    godsToReturn.add(getMockGodApiResult(latestId + 1))
  }
}