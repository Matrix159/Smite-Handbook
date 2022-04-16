package com.matrix.materializedsmite.data

import com.matrix.materializedsmite.data.models.GodInformation
import io.ktor.client.*
import io.ktor.client.request.*

class SmiteApi(private val client: HttpClient = ktorHttpClient) {
  suspend fun getGods(): List<GodInformation> = client.get("http://matrixpi.ddns.net:3000/gods")
}