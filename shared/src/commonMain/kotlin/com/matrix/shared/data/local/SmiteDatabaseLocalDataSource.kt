package com.matrix.shared.data.local

import com.matrix.shared.data.local.interfaces.SmiteLocalDataSource
import com.matrix.shared.data.model.builds.BuildInformation
import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.model.items.ItemInformation
import com.matrix.shared.data.model.toDomain
import com.matrix.shared.sqldelight.DatabaseDriverFactory
import com.matrix.shared.sqldelight.createDatabase
import com.squareup.sqldelight.ColumnAdapter
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal inline fun <reified T : Any> getAdapter() = object : ColumnAdapter<T, String> {
  override fun decode(databaseValue: String) = Json.decodeFromString<T>(databaseValue)
  override fun encode(value: T) = Json.encodeToString(value)
}

internal class SmiteDatabaseLocalDataSource constructor(
  databaseDriverFactory: DatabaseDriverFactory,
) : SmiteLocalDataSource {

  private val database = createDatabase(databaseDriverFactory)

  override suspend fun saveGods(gods: List<GodInformation>) = database.transaction {
    gods.forEach {
      database.godEntityQueries.upsertGod(
        id = it.id,
        abilityDetails1 = it.abilityDetails1,
        abilityDetails2 = it.abilityDetails2,
        abilityDetails3 = it.abilityDetails3,
        abilityDetails4 = it.abilityDetails4,
        abilityDetails5 = it.abilityDetails5,
        basicAttack = it.basicAttack,
        attackSpeed = it.attackSpeed,
        attackSpeedPerLevel = it.attackSpeedPerLevel,
        autoBanned = it.autoBanned,
        cons = it.cons,
        hp5PerLevel = it.hp5PerLevel,
        health = it.health,
        healthPerFive = it.healthPerFive,
        healthPerLevel = it.healthPerLevel,
        lore = it.lore,
        mp5PerLevel = it.mp5PerLevel,
        magicProtection = it.magicProtection,
        magicProtectionPerLevel = it.magicProtectionPerLevel,
        magicalPower = it.magicalPower,
        magicalPowerPerLevel = it.magicalPowerPerLevel,
        mana = it.mana,
        manaPerFive = it.manaPerFive,
        manaPerLevel = it.manaPerLevel,
        name = it.name,
        onFreeRotation = it.onFreeRotation,
        pantheon = it.pantheon,
        physicalPower = it.physicalPower,
        physicalPowerPerLevel = it.physicalPowerPerLevel,
        physicalProtection = it.physicalProtection,
        physicalProtectionPerLevel = it.physicalProtectionPerLevel,
        pros = it.pros,
        roles = it.roles,
        speed = it.speed,
        title = it.title,
        type = it.type,
        godIconURL = it.godIconURL,
        godCardURL = it.godCardURL,
        latestGod = it.latestGod
      )
    }
  }

  override fun getGods(): Flow<List<GodInformation>> =
    database.godEntityQueries.selectAllGods().asFlow().mapToList()
      .map { godList -> godList.map { it.toDomain() } }

  override fun getGod(godId: Long): Flow<GodInformation> =
    database.godEntityQueries.selectGodById(godId).asFlow().mapToOne().map { it.toDomain() }

  override suspend fun saveItems(items: List<ItemInformation>) = database.transaction {
    items.forEach {
      database.itemEntityQueries.upsertItem(
        id = it.itemID,
        activeFlag = it.activeFlag,
        childItemID = it.childItemID,
        deviceName = it.deviceName,
        glyph = it.glyph,
        iconID = it.iconID,
        itemTier = it.itemTier,
        price = it.price,
        restrictedRoles = it.restrictedRoles,
        rootItemID = it.rootItemID,
        shortDesc = it.shortDesc,
        startingItem = it.startingItem,
        type = it.type,
        itemIconURL = it.itemIconURL,
        itemDescription = it.itemDescription
      )
    }
  }

  override fun getItems(isActive: Boolean): Flow<List<ItemInformation>> =
    database.itemEntityQueries.selectAllItems(isActive).asFlow().mapToList()
      .map { itemList -> itemList.map { it.toDomain() } }

  override fun getItem(itemId: Long): Flow<ItemInformation> =
    database.itemEntityQueries.selectItemById(itemId).asFlow().mapToOne().map { it.toDomain() }

  override suspend fun saveBuild(buildInformation: BuildInformation) {
    database.buildEntityQueries.transaction {
      database.buildEntityQueries.upsertBuild(
        buildId = buildInformation.id,
        buildName = buildInformation.name,
        godId = buildInformation.god.id
      )
      val lastInsertId = database.buildEntityQueries.lastInsertRowId().executeAsOne()
      buildInformation.items.forEach {
        database.buildEntityQueries.insertBuildItems(
          buildId = lastInsertId,
          itemId = it.itemID
        )
      }
    }
  }


  override fun getBuilds(): Flow<List<BuildInformation>> =
    database.buildEntityQueries.selectAllBuilds().asFlow().mapToList().map { it.toDomain() }

  override fun getBuild(buildId: Long): Flow<BuildInformation> = database
    .buildEntityQueries
    .selectAllBuilds()
    .asFlow()
    .mapToList()
    .map { it.toDomain() }
    .map { list ->
      list.first { it.id == buildId }
    }

  override suspend fun deleteBuild(buildId: Long) =
    database.buildEntityQueries.deleteBuildById(buildId)
}
