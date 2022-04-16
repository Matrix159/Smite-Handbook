package com.matrix.materializedsmite.data.smite.impl

import com.matrix.materializedsmite.data.ApiResult
import com.matrix.materializedsmite.data.SmiteApi
import com.matrix.materializedsmite.data.models.GodInformation
import com.matrix.materializedsmite.data.smite.SmiteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SmiteRepositoryImpl : SmiteRepository {
  private val smiteApi = SmiteApi()

  override suspend fun getGods(): ApiResult<List<GodInformation>> {
    return withContext(Dispatchers.IO) {
      try {
        val gods = smiteApi.getGods()
        ApiResult.Success(gods)
      } catch (ex: Exception) {
        ApiResult.Error(ex)
      }
    }
  }
}