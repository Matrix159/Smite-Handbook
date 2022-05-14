package com.matrix.materializedsmite.data.smite

import com.matrix.materializedsmite.data.models.GodInformation
import com.matrix.materializedsmite.data.models.GodSkin
import com.matrix.materializedsmite.data.models.Item

interface SmiteRepository {
  suspend fun getGods(): List<GodInformation>
  suspend fun getGodSkins(godId: Int): List<GodSkin>
  suspend fun getItems(): List<Item>
}