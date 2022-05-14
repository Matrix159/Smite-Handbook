package com.matrix.materializedsmite.data

import com.matrix.materializedsmite.data.models.GodInformation
import com.matrix.materializedsmite.data.models.GodSkin
import com.matrix.materializedsmite.data.models.Item
import io.ktor.client.*
import io.ktor.client.request.*

class SmiteApi(private val client: HttpClient = ktorHttpClient) {
  suspend fun getGods(): List<GodInformation> =
    client.get("https://materialized-smite.herokuapp.com/gods")

  suspend fun getGodSkins(godId: Int): List<GodSkin> =
    client.get("https://materialized-smite.herokuapp.com/godskins/${godId}")

  suspend fun getItems(): List<Item> = client.get("https://materialized-smite.herokuapp.com/items")
}