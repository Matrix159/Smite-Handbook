package com.matrix.shared.testing.fakes

import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.model.builds.BuildInformation
import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.model.items.ItemInformation
import com.matrix.shared.data.model.skins.GodSkinInformation
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeSmiteRepository: SmiteRepository {
  /**
   * The backing hot flow for the list of gods for testing.
   */
  private val godsFlow: MutableSharedFlow<List<GodInformation>> =
    MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

  override fun getGods(): Flow<List<GodInformation>> = godsFlow

  override fun getGod(godId: Long): Flow<GodInformation> {
    TODO("Not yet implemented")
  }

  // Test usage only
  fun addGods(gods: List<GodInformation>) {
    godsFlow.tryEmit(gods)
  }

  override fun getGodSkins(godId: Long): Flow<List<GodSkinInformation>> {
    TODO("Not yet implemented")
  }

  override fun getItems(): Flow<List<ItemInformation>> {
    TODO("Not yet implemented")
  }

  override fun getItem(itemId: Long): Flow<ItemInformation> {
    TODO("Not yet implemented")
  }

  override fun getBuilds(): Flow<List<BuildInformation>> {
    TODO("Not yet implemented")
  }

  override fun getBuild(buildId: Long): Flow<BuildInformation> {
    TODO("Not yet implemented")
  }

  override suspend fun saveBuild(buildInformation: BuildInformation) {
    TODO("Not yet implemented")
  }

  override suspend fun updateGodInBuild(buildId: Long, godId: Long) {
    TODO("Not yet implemented")
  }

  override suspend fun updateItemsInBuild(buildId: Long, itemIds: List<Long>) {
    TODO("Not yet implemented")
  }

  override suspend fun deleteBuild(buildInformation: BuildInformation) {
    TODO("Not yet implemented")
  }

  override suspend fun sync() {
    TODO("Not yet implemented")
  }
}