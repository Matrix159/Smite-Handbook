package com.matrix.shared.testing.fakes

import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.model.builds.BuildInformation
import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.model.items.ItemInformation
import com.matrix.shared.data.model.skins.GodSkinInformation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class FakeSmiteRepository : SmiteRepository {
  /**
   * The backing hot flow for the list of gods for testing.
   */
  private val godsFlow: MutableStateFlow<List<GodInformation>> =
    MutableStateFlow(emptyList())

  private val itemsFlow: MutableStateFlow<List<ItemInformation>> =
    MutableStateFlow(emptyList())

  private val skinsFlow: MutableStateFlow<List<GodSkinInformation>> =
    MutableStateFlow(emptyList())

  private val buildsFlow: MutableStateFlow<List<BuildInformation>> =
    MutableStateFlow(emptyList())

  var shouldThrowError = false

  // Test usage only
  fun addGods(gods: List<GodInformation>) {
    godsFlow.tryEmit(gods)
  }

  fun addItems(items: List<ItemInformation>) {
    itemsFlow.tryEmit(items)
  }

  fun addSkins(skins: List<GodSkinInformation>) {
    skinsFlow.tryEmit(skins)
  }

  fun addBuilds(builds: List<BuildInformation>) {
    buildsFlow.tryEmit(builds)
  }

  override fun getGods(): Flow<List<GodInformation>> = godsFlow.map { throwOrAction { it } }

  override fun getGod(godId: Long): Flow<GodInformation> {
    return godsFlow.map { gods -> gods.first { god -> god.id == godId } }
  }

  override fun getGodSkins(godId: Long): Flow<List<GodSkinInformation>> = skinsFlow

  override fun getItems(): Flow<List<ItemInformation>> = itemsFlow.map { throwOrAction { it } }

  override fun getItem(itemId: Long): Flow<ItemInformation> {
    return itemsFlow.map { it.first { it.itemID == itemId } }
  }

  override fun getBuilds(): Flow<List<BuildInformation>> = buildsFlow.map { throwOrAction { it } }

  override fun getBuild(buildId: Long): Flow<BuildInformation> {
    return buildsFlow.map { builds -> builds.first { it.id == buildId } }
  }

  override suspend fun saveBuild(buildInformation: BuildInformation) {
    buildsFlow.tryEmit(buildsFlow.first() + buildInformation)
  }

  override suspend fun updateGodInBuild(buildId: Long, godId: Long) {
    val builds = buildsFlow.first()
    val updatedBuilds = builds.map { build ->
      if (build.id == buildId) {
        build.copy(god = godsFlow.first().first { it.id == godId })
      } else {
        build
      }
    }
    buildsFlow.tryEmit(updatedBuilds)
  }

  override suspend fun updateItemsInBuild(buildId: Long, itemIds: List<Long>) {
    val builds = buildsFlow.first()
    val updatedBuilds = builds.map { build ->
      if (build.id == buildId) {
        build.copy(items = itemIds.map { itemId -> itemsFlow.value.first { it.itemID == itemId } })
      } else {
        build
      }
    }
    buildsFlow.tryEmit(updatedBuilds)
  }

  override suspend fun deleteBuild(buildInformation: BuildInformation) {
    val builds = buildsFlow.first()
    val updatedBuilds = builds.filter { it.id != buildInformation.id }
    buildsFlow.tryEmit(updatedBuilds)
  }

  override suspend fun sync() {
    // No-op
  }

  private fun <T> throwOrAction(action: () -> T): T {
    if (shouldThrowError) {
      throw Exception("Error")
    }
    return action()
  }
}