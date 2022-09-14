package com.matrix.data.fakes

import com.matrix.data.builder.getMockGodApiResult
import com.matrix.data.builder.getMockItemApiResult
import com.matrix.data.model.PatchVersionInfo
import com.matrix.data.network.interfaces.SmiteRemoteDataSource
import com.matrix.data.network.model.GodApiResult
import com.matrix.data.network.model.GodSkinApiResult
import com.matrix.data.network.model.ItemApiResult

class SmiteRemoteDataSourceFake: SmiteRemoteDataSource {
  private var godsToReturn = mutableListOf(getMockGodApiResult(1), getMockGodApiResult(2))
  private var itemsToReturn = mutableListOf(getMockItemApiResult(1), getMockItemApiResult(2))
  private var currentPatchVersionInfo = PatchVersionInfo("9.7")
  override suspend fun getGods(): List<GodApiResult> = godsToReturn

  override suspend fun getGodSkins(godId: Int): List<GodSkinApiResult> {
    TODO("Not yet implemented")
  }

  override suspend fun getItems(): List<ItemApiResult> = itemsToReturn

  override suspend fun getPatchVersion(): PatchVersionInfo = currentPatchVersionInfo

  fun increaseReturnedGodsByOne() {
    val latestId = godsToReturn.maxBy { it.id }.id
    godsToReturn.add(getMockGodApiResult(latestId + 1))
  }

  fun increaseReturnedItemsByOne() {
    val latestId = itemsToReturn.maxBy { it.itemID }.itemID
    itemsToReturn.add(getMockItemApiResult(latestId + 1))
  }
}