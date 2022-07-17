package com.matrix.api.impl

import com.matrix.api.SmiteApiDataSource
import com.matrix.api.ktorHttpClient
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkin
import com.matrix.domain.models.Item
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class SmiteApiDataSourceImpl @Inject constructor(): SmiteApiDataSource {

  private val client: HttpClient = ktorHttpClient

  override suspend fun getGods(): List<GodInformation> =
    client.get("https://materialized-smite.herokuapp.com/gods")

  override suspend fun getGodSkins(godId: Int): List<GodSkin> =
    client.get("https://materialized-smite.herokuapp.com/godskins/${godId}")

  override suspend fun getItems(): List<Item> = client.get("https://materialized-smite.herokuapp.com/items")
}