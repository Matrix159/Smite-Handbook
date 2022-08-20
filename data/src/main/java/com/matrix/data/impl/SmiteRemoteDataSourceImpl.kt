package com.matrix.data.impl

import com.matrix.data.SmiteRemoteDataSource
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkin
import com.matrix.domain.models.Item
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

private const val baseUrl = "https://smite-session-tracker-ktor.herokuapp.com"

@OptIn(ExperimentalSerializationApi::class)
class SmiteRemoteDataSourceImpl @Inject constructor(): SmiteRemoteDataSource {

  private val client: HttpClient = ktorHttpClient

  override suspend fun getGods(): List<GodInformation> =
    client.get("${baseUrl}/gods").body()

  override suspend fun getGodSkins(godId: Int): List<GodSkin> =
    client.get("${baseUrl}/godskins/${godId}").body()

  override suspend fun getItems(): List<Item> = client.get("${baseUrl}/items").body()
}