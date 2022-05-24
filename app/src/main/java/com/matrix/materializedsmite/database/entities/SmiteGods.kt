package com.matrix.materializedsmite.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.matrix.api.models.Ability
import com.matrix.api.models.AbilityDescription
import kotlinx.serialization.SerialName

const val SMITE_GODS_TABLE_NAME = "smite_gods"

@Entity(tableName = SMITE_GODS_TABLE_NAME)
data class SmiteGods(
  @PrimaryKey(autoGenerate = true)
  val id: Int = 0,
  val ability1: String,

  val ability2: String,

  val ability3: String,

  val ability4: String,

  val ability5: String,

  val abilityId1: Long,

  val abilityId2: Long,

  val abilityId3: Long,

  val abilityId4: Long,

  val abilityId5: Long,

  val abilityDetails1: Ability,

  val abilityDetails2: Ability,

  val abilityDetails3: Ability,

  val abilityDetails4: Ability,

  val abilityDetails5: Ability,

  val attackSpeed: Double,

  val attackSpeedPerLevel: Double,

  val autoBanned: String,

  val cons: String,

  val hp5PerLevel: Double,

  val health: Long,

  val healthPerFive: Double,

  val healthPerLevel: Double,

  val lore: String,

  val mp5PerLevel: Double,

  val magicProtection: Double,

  val magicProtectionPerLevel: Double,

  val magicalPower: Long,

  val magicalPowerPerLevel: Double,

  val mana: Long,

  val manaPerFive: Double,

  val manaPerLevel: Double,

  val name: String,

  val onFreeRotation: String,

  val pantheon: String,

  val physicalPower: Long,

  val physicalPowerPerLevel: Double,

  val physicalProtection: Double,

  val physicalProtectionPerLevel: Double,

  val pros: String,

  val roles: String,

  val speed: Long,

  val title: String,

  val type: String,

  val abilityDescription1: AbilityDescription,
  val abilityDescription2: AbilityDescription,
  val abilityDescription3: AbilityDescription,
  val abilityDescription4: AbilityDescription,
  val abilityDescription5: AbilityDescription,
  val basicAttack: AbilityDescription,

  val godAbility1URL: String,

  val godAbility2URL: String,

  val godAbility3URL: String,

  val godAbility4URL: String,

  val godAbility5URL: String,

  var godCardURL: String,

  val godIconURL: String,

  val latestGod: String,

  val retMsg: String?
)
