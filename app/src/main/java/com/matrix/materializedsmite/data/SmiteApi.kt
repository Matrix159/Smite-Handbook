package com.matrix.materializedsmite.data

import com.matrix.materializedsmite.data.models.GodInformation
import com.matrix.materializedsmite.data.models.GodSkin
import com.matrix.materializedsmite.data.models.Item
import io.ktor.client.*
import io.ktor.client.request.*

class SmiteApi(private val client: HttpClient = ktorHttpClient) {
  suspend fun getGods(): List<GodInformation> = client.get("http://matrixpi.ddns.net:3000/gods")
  suspend fun getGodSkins(godId: Int): List<GodSkin> =
    client.get("http://matrixpi.ddns.net:3000/godskins/${godId}")
  suspend fun getItems(): List<Item> = client.get("http://matrixpi.ddns.net:3000/items")
}