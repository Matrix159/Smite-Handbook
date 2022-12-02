package com.matrix.shared.data.network

import com.matrix.shared.data.network.interfaces.SmiteRemoteDataSource
import com.matrix.shared.data.network.model.GodApiResult
import com.matrix.shared.data.network.model.GodSkinApiResult
import com.matrix.shared.data.network.model.ItemApiResult
import com.matrix.shared.data.network.model.PatchVersionInfo
import com.matrix.shared.network.ktorHttpClient
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
class SmiteApiRemoteDataSource() : SmiteRemoteDataSource {
  private val baseUrl = "https://smite-handbook.onrender.com"
  private val client: HttpClient = ktorHttpClient

  override suspend fun getGods(): List<GodApiResult> =
    client.get("$baseUrl/gods").body()

  override suspend fun getGodSkins(godId: Int): List<GodSkinApiResult> =
    client.get("$baseUrl/godskins/${godId}").body()

  override suspend fun getItems(): List<ItemApiResult> = client.get("$baseUrl/items").body()
  override suspend fun getPatchVersion(): PatchVersionInfo = client.get("$baseUrl/patchinfo").body()
}