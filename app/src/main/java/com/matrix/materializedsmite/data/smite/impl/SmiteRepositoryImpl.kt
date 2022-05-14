package com.matrix.materializedsmite.data.smite.impl

import com.matrix.materializedsmite.data.SmiteApi
import com.matrix.materializedsmite.data.models.GodInformation
import com.matrix.materializedsmite.data.models.GodSkin
import com.matrix.materializedsmite.data.models.Item
import com.matrix.materializedsmite.data.smite.SmiteRepository

class SmiteRepositoryImpl : SmiteRepository {
  private val smiteApi = SmiteApi()

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