package com.matrix.data

import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkin
import com.matrix.domain.models.Item


interface SmiteRemoteDataSource {
  suspend fun getGods(): List<GodInformation>

  suspend fun getGodSkins(godId: Int): List<GodSkin>

  suspend fun getItems(): List<Item>
}