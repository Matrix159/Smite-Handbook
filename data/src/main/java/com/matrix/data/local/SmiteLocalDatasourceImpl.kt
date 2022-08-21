package com.matrix.data.local

import android.content.Context
import com.matrix.data.local.interfaces.SmiteLocalDataSource
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkin
import com.matrix.domain.models.Item
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SmiteLocalDatasourceImpl @Inject constructor(@ApplicationContext context: Context) :
  SmiteLocalDataSource {
  private val godListFileMutex = Mutex()
  private val godListFile = File(context.filesDir, "god-list.json")
  private val itemListFileMutex = Mutex()
  private val itemListFile = File(context.filesDir, "item-list.json")

  override suspend fun saveGods(godInformation: List<GodInformation>) {
    godListFileMutex.withLock {
      godListFile.writeText(Json.encodeToString(godInformation))
    }
  }

  override suspend fun readGods(): List<GodInformation> {
    return if (godListFile.exists() && godListFile.canRead()) {
      val textFromFile = godListFile.readText()
      when (textFromFile.isNotBlank()) {
        true -> {
          try {
            Json.decodeFromString(textFromFile)
          } catch (ex: Exception) {
            emptyList()
          }
        }
        false -> emptyList()
      }
    } else {
      emptyList()
    }
  }

  override suspend fun saveItems(items: List<Item>) {
    itemListFileMutex.withLock {
      itemListFile.writeText(Json.encodeToString(items))
    }
  }

  override suspend fun readItems(): List<Item> {
    return if (itemListFile.exists() && itemListFile.canRead()) {
      val textFromFile = itemListFile.readText()
      when (textFromFile.isNotBlank()) {
        true -> try {
          Json.decodeFromString(textFromFile)
        } catch (ex: Exception) {
          emptyList()
        }
        false -> emptyList()
      }
    } else {
      emptyList()
    }
  }
}