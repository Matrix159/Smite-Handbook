package com.matrix.shared.data.local.db.entity

import com.matrix.shared.data.models.GodInformation
import com.matrix.shared.data.network.model.GodApiResult
import com.matrix.shared.data.models.DescriptionValue as DomainDescriptionValue

expect class GodEntity(
  id: Int,
  patchVersion: String? = null,
  abilityDetails1: Ability,
  abilityDetails2: Ability,
  abilityDetails3: Ability,
  abilityDetails4: Ability,
  abilityDetails5: Ability,
  basicAttack: AbilityDescription,
  attackSpeed: Double,
  attackSpeedPerLevel: Double,
  autoBanned: Boolean,
  cons: String,
  hp5PerLevel: Double,
  health: Long,
  healthPerFive: Double,
  healthPerLevel: Double,
  lore: String,
  mp5PerLevel: Double,
  magicProtection: Double,
  magicProtectionPerLevel: Double,
  magicalPower: Long,
  magicalPowerPerLevel: Double,
  mana: Long,
  manaPerFive: Double,
  manaPerLevel: Double,
  name: String,
  onFreeRotation: Boolean,
  pantheon: String,
  physicalPower: Long,
  physicalPowerPerLevel: Double,
  physicalProtection: Double,
  physicalProtectionPerLevel: Double,
  pros: String,
  roles: String,
  speed: Long,
  title: String,
  type: String,
  godCardURL: String,
  godIconURL: String,
  latestGod: Boolean,
) {

  val id: Int
  val patchVersion: String?
  val abilityDetails1: Ability
  val abilityDetails2: Ability
  val abilityDetails3: Ability
  val abilityDetails4: Ability
  val abilityDetails5: Ability
  val basicAttack: AbilityDescription
  val attackSpeed: Double
  val attackSpeedPerLevel: Double
  val autoBanned: Boolean
  val cons: String
  val hp5PerLevel: Double
  val health: Long
  val healthPerFive: Double
  val healthPerLevel: Double
  val lore: String
  val mp5PerLevel: Double
  val magicProtection: Double
  val magicProtectionPerLevel: Double
  val magicalPower: Long
  val magicalPowerPerLevel: Double
  val mana: Long
  val manaPerFive: Double
  val manaPerLevel: Double
  val name: String
  val onFreeRotation: Boolean
  val pantheon: String
  val physicalPower: Long
  val physicalPowerPerLevel: Double
  val physicalProtection: Double
  val physicalProtectionPerLevel: Double
  val pros: String
  val roles: String
  val speed: Long
  val title: String
  val type: String
  val godCardURL: String
  val godIconURL: String
  val latestGod: Boolean

  //region conversions
  fun toDomain(): GodInformation

  companion object {
    fun fromApi(god: GodApiResult, patchVersion: String?): GodEntity
  }
  //endregion
}

expect class AbilityDescription(
  cooldown: String,
  cost: String,
  description: String,
  menuItems: List<DomainDescriptionValue>,
  rankItems: List<DomainDescriptionValue>,
) {
  val cooldown: String
  val cost: String
  val description: String
  val menuItems: List<DomainDescriptionValue>
  val rankItems: List<DomainDescriptionValue>
}

expect class Ability(
  id: Long,
  description: AbilityDescription,
  summary: String,
  url: String,
) {
  val id: Long
  val description: AbilityDescription
  val summary: String
  val url: String
}

