package com.matrix.api

import com.matrix.api.models.GodInformation
import com.matrix.api.models.GodSkin
import com.matrix.api.models.Item
import io.ktor.client.*
import io.ktor.client.request.*

class SmiteApi(private val client: HttpClient = ktorHttpClient) {
  suspend fun getGods(): List<GodInformation> =
    client.get("https://materialized-smite.herokuapp.com/gods")

  suspend fun getGodSkins(godId: Int): List<GodSkin> =
    client.get("https://materialized-smite.herokuapp.com/godskins/${godId}")

  suspend fun getItems(): List<Item> = client.get("https://materialized-smite.herokuapp.com/items")
}