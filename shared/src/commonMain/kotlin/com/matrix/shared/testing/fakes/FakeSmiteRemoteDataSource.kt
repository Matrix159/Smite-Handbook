package com.matrix.shared.testing.fakes

import com.matrix.shared.data.network.interfaces.SmiteRemoteDataSource
import com.matrix.shared.data.network.model.GodApiResult
import com.matrix.shared.data.network.model.GodSkinApiResult
import com.matrix.shared.data.network.model.ItemApiResult
import com.matrix.shared.data.network.model.PatchVersionInfo
import com.matrix.shared.testing.builder.getMockGodApiResult
import com.matrix.shared.testing.builder.getMockGodSkinApiResult
import com.matrix.shared.testing.builder.getMockItemApiResult

internal class FakeSmiteRemoteDataSource : SmiteRemoteDataSource {
  private val godsToReturn = mutableListOf(getMockGodApiResult(1), getMockGodApiResult(2))
  private val itemsToReturn = mutableListOf(getMockItemApiResult(1), getMockItemApiResult(2))
  private val godSkinsToReturn =
    listOf(
      getMockGodSkinApiResult(1, "first item"),
      getMockGodSkinApiResult(1, "second item"),
      getMockGodSkinApiResult(2, "first item"),
      getMockGodSkinApiResult(2, "second item")
    )
  private val currentPatchVersionInfo = PatchVersionInfo("9.7")
  override suspend fun getGods(): List<GodApiResult> = godsToReturn

  override suspend fun getGodSkins(godId: Long): List<GodSkinApiResult> =
    godSkinsToReturn.filter { it.godID.toLong() == godId }

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