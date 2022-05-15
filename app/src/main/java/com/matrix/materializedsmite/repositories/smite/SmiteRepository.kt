package com.matrix.materializedsmite.repositories.smite

import com.matrix.api.models.GodInformation
import com.matrix.api.models.GodSkin
import com.matrix.api.models.Item

interface SmiteRepository {
  suspend fun getGods(): List<GodInformation>
  suspend fun getGodSkins(godId: Int): List<GodSkin>
  suspend fun getItems(): List<Item>
}