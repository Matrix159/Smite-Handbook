package com.matrix.data.local.interfaces

import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkin
import com.matrix.domain.models.Item

interface SmiteLocalDataSource {
  suspend fun saveGods(godInformation: List<GodInformation>)
  suspend fun readGods(): List<GodInformation>
  suspend fun saveItems(items: List<Item>)
  suspend fun readItems(): List<Item>
}