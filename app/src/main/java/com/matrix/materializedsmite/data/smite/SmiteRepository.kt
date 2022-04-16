package com.matrix.materializedsmite.data.smite

import com.matrix.materializedsmite.data.ApiResult
import com.matrix.materializedsmite.data.models.GodInformation

interface SmiteRepository {
  suspend fun getGods(): ApiResult<List<GodInformation>>
}