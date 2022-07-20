package com.matrix.api.impl

import com.matrix.api.SmiteRemoteDataSource
import com.matrix.api.ktorHttpClient
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkin
import com.matrix.domain.models.Item
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

private const val baseUrl = "https://materialized-smite.herokuapp.com"

@OptIn(ExperimentalSerializationApi::class)
class SmiteRemoteDataSourceImpl @Inject constructor(): SmiteRemoteDataSource {

  private val client: HttpClient = ktorHttpClient

  override suspend fun getGods(): List<GodInformation> =
    client.get("${baseUrl}/gods")

  override suspend fun getGodSkins(godId: Int): List<GodSkin> =
    client.get("${baseUrl}/godskins/${godId}")

  override suspend fun getItems(): List<Item> = client.get("${baseUrl}/items")
}