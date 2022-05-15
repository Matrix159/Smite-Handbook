package com.matrix.materializedsmite.repositories.smite.impl

import com.matrix.api.SmiteApi
import com.matrix.api.models.GodInformation
import com.matrix.api.models.GodSkin
import com.matrix.api.models.Item
import com.matrix.materializedsmite.repositories.smite.SmiteRepository
import javax.inject.Inject

class SmiteRepositoryImpl @Inject constructor(
  private val smiteApi: SmiteApi
): SmiteRepository {

  override suspend fun getGods(): List<GodInformation> {
    try {
      return smiteApi.getGods()
    } catch (ex: Exception) {
      throw ex
    }
  }

  override suspend fun getGodSkins(godId: Int): List<GodSkin> {
    try {
      return smiteApi.getGodSkins(godId)
    } catch (ex: Exception) {
      throw ex
    }
  }

  override suspend fun getItems(): List<Item> {
    try {
      return smiteApi.getItems()
    } catch (ex: Exception) {
      throw ex
    }
  }
}