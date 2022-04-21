package com.matrix.materializedsmite.data.smite.impl

import com.matrix.materializedsmite.data.SmiteApi
import com.matrix.materializedsmite.data.models.GodInformation
import com.matrix.materializedsmite.data.models.GodSkin
import com.matrix.materializedsmite.data.smite.SmiteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SmiteRepositoryImpl : SmiteRepository {
  private val smiteApi = SmiteApi()

  override suspend fun getGods(): List<GodInformation> {
    return withContext(Dispatchers.IO) {
      try {
        smiteApi.getGods()
      } catch (ex: Exception) {
        throw ex
      }
    }
  }

  override suspend fun getGodSkins(godId: Int): List<GodSkin> {
    return withContext(Dispatchers.IO) {
      try {
        smiteApi.getGodSkins(godId)
      } catch (ex: Exception) {
        throw ex
      }
    }
  }
}