package com.matrix.data.network

import com.matrix.data.impl.ktorHttpClient
import com.matrix.data.network.interfaces.SmiteRemoteDataSource
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkin
import com.matrix.domain.models.Item
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@OptIn(ExperimentalSerializationApi::class)
class SmiteRemoteDataSourceImpl @Inject constructor(): SmiteRemoteDataSource {
  private val baseUrl = "https://smite-session-tracker-ktor.herokuapp.com"
  private val client: HttpClient = ktorHttpClient

  override suspend fun getGods(): List<GodInformation> =
    client.get("$baseUrl/gods").body()

  override suspend fun getGodSkins(godId: Int): List<GodSkin> =
    client.get("$baseUrl/godskins/${godId}").body()

  override suspend fun getItems(): List<Item> = client.get("$baseUrl/items").body()
}