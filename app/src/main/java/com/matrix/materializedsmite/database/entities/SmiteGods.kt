package com.matrix.materializedsmite.database.entities

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.matrix.api.models.Ability
import com.matrix.api.models.AbilityDescription
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

const val SMITE_GODS_TABLE_NAME = "smite_gods"

//data class Ability(
//  @Embedded
//  val description: AbilityDescription,
//
//  val id: Long,
//
//  val summary: String,
//
//  val url: String
//)

@Entity(tableName = SMITE_GODS_TABLE_NAME)
data class SmiteGods(
  @PrimaryKey(autoGenerate = true)
  val id: Int = 0,
//  val ability1: String,
//
//  val ability2: String,
//
//  val ability3: String,
//
//  val ability4: String,
//
//  val ability5: String,
//
//  val abilityId1: Long,
//
//  val abilityId2: Long,
//
//  val abilityId3: Long,
//
//  val abilityId4: Long,
//
//  val abilityId5: Long,
//
//  @Embedded(prefix = "ability_details_1_")
//  val abilityDetails1: Ability,
//
//  @Embedded(prefix = "ability_details_2_")
//  val abilityDetails2: Ability,
//
//  @Embedded(prefix = "ability_details_3_")
//  val abilityDetails3: Ability,
//
//  @Embedded(prefix = "ability_details_4_")
//  val abilityDetails4: Ability,
//
//  @Embedded(prefix = "ability_details_5_")
//  val abilityDetails5: Ability,
//
//  val attackSpeed: Double,
//
//  val attackSpeedPerLevel: Double,
//
//  val autoBanned: String,
//
//  val cons: String,
//
//  val hp5PerLevel: Double,
//
//  val health: Long,
//
//  val healthPerFive: Double,
//
//  val healthPerLevel: Double,
//
//  val lore: String,
//
//  val mp5PerLevel: Double,
//
//  val magicProtection: Double,
//
//  val magicProtectionPerLevel: Double,
//
//  val magicalPower: Long,
//
//  val magicalPowerPerLevel: Double,
//
//  val mana: Long,
//
//  val manaPerFive: Double,
//
//  val manaPerLevel: Double,
//
//  val name: String,
//
//  val onFreeRotation: String,
//
//  val pantheon: String,
//
//  val physicalPower: Long,
//
//  val physicalPowerPerLevel: Double,
//
//  val physicalProtection: Double,
//
//  val physicalProtectionPerLevel: Double,
//
//  val pros: String,
//
//  val roles: String,
//
//  val speed: Long,
//
//  val title: String,
//
//  val type: String,
//
//  @Embedded(prefix = "ability_description_1_")
//  val abilityDescription1: AbilityDescription,
//  @Embedded(prefix = "ability_description_2_")
//  val abilityDescription2: AbilityDescription,
//  @Embedded(prefix = "ability_description_3_")
//  val abilityDescription3: AbilityDescription,
//  @Embedded(prefix = "ability_description_4_")
//  val abilityDescription4: AbilityDescription,
//  @Embedded(prefix = "ability_description_5_")
//  val abilityDescription5: AbilityDescription,
//  @Embedded(prefix = "basic_attack_description_")
//  val basicAttack: AbilityDescription,
//
//  val godAbility1URL: String,
//
//  val godAbility2URL: String,
//
//  val godAbility3URL: String,
//
//  val godAbility4URL: String,
//
//  val godAbility5URL: String,
//
//  var godCardURL: String,
//
//  val godIconURL: String,
//
//  val latestGod: String,
//
//  val retMsg: String?
)
