package com.matrix.shared.testing.fakes

import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.model.builds.BuildInformation
import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.model.items.ItemInformation
import com.matrix.shared.data.model.skins.GodSkinInformation
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map

class FakeSmiteRepository: SmiteRepository {
  /**
   * The backing hot flow for the list of gods for testing.
   */
  private val godsFlow: MutableSharedFlow<List<GodInformation>> =
    MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

  private val itemsFlow: MutableSharedFlow<List<ItemInformation>> =
    MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

  private val skinsFlow: MutableSharedFlow<List<GodSkinInformation>> =
    MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

  private val buildsFlow: MutableSharedFlow<List<BuildInformation>> =
    MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

  override fun getGods(): Flow<List<GodInformation>> = godsFlow

  override fun getGod(godId: Long): Flow<GodInformation> {
    return godsFlow.map { gods -> gods.first { god -> god.id == godId } }
  }

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

  override fun getGodSkins(godId: Long): Flow<List<GodSkinInformation>> = skinsFlow

  override fun getItems(): Flow<List<ItemInformation>> = itemsFlow

  override fun getItem(itemId: Long): Flow<ItemInformation> {
    return itemsFlow.map { it.first { it.itemID == itemId } }
  }

  override fun getBuilds(): Flow<List<BuildInformation>> = buildsFlow

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
        build.copy(god = build.god.copy(id = godId))
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
        build.copy(items = itemIds.map { itemId -> build.items.first { it.itemID == itemId } })
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
}