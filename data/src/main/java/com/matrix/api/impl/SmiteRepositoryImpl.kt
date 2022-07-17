package com.matrix.api.impl

import com.matrix.api.SmiteApiDataSource
import com.matrix.domain.contracts.SmiteRepository
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkin
import com.matrix.domain.models.Item
import javax.inject.Inject

class SmiteRepositoryImpl @Inject constructor(
  private val smiteApi: SmiteApiDataSource
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