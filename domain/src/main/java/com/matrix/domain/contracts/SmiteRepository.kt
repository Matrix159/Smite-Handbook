package com.matrix.domain.contracts

import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkin
import com.matrix.domain.models.Item

interface SmiteRepository {
  suspend fun getGods(): List<GodInformation>
  suspend fun getGodSkins(godId: Int): List<GodSkin>
  suspend fun getItems(): List<Item>
}