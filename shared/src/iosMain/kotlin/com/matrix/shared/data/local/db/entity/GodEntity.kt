package com.matrix.shared.data.local.db.entity

import com.matrix.shared.data.model.shared.DescriptionValue
import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.network.model.GodApiResult

actual data class GodEntity actual constructor(
  actual val id: Int,
  actual val patchVersion: String?,
  actual val abilityDetails1: Ability,
  actual val abilityDetails2: Ability,
  actual val abilityDetails3: Ability,
  actual val abilityDetails4: Ability,
  actual val abilityDetails5: Ability,
  actual val basicAttack: AbilityDescription,
  actual val attackSpeed: Double,
  actual val attackSpeedPerLevel: Double,
  actual val autoBanned: Boolean,
  actual val cons: String,
  actual val hp5PerLevel: Double,
  actual val health: Long,
  actual val healthPerFive: Double,
  actual val healthPerLevel: Double,
  actual val lore: String,
  actual val mp5PerLevel: Double,
  actual val magicProtection: Double,
  actual val magicProtectionPerLevel: Double,
  actual val magicalPower: Long,
  actual val magicalPowerPerLevel: Double,
  actual val mana: Long,
  actual val manaPerFive: Double,
  actual val manaPerLevel: Double,
  actual val name: String,
  actual val onFreeRotation: Boolean,
  actual val pantheon: String,
  actual val physicalPower: Long,
  actual val physicalPowerPerLevel: Double,
  actual val physicalProtection: Double,
  actual val physicalProtectionPerLevel: Double,
  actual val pros: String,
  actual val roles: String,
  actual val speed: Long,
  actual val title: String,
  actual val type: String,
  actual val godCardURL: String,
  actual val godIconURL: String,
  actual val latestGod: Boolean,
) {

  //region conversions
  actual fun toDomain(): GodInformation {
    TODO()
  }

  actual companion object {
    actual fun fromApi(god: GodApiResult, patchVersion: String?): GodEntity {
      TODO()
    }
  }
  //endregion
}

actual data class AbilityDescription actual constructor(
  actual val cooldown: String,
  actual val cost: String,
  actual val description: String,
  actual val menuItems: List<DescriptionValue>,
  actual val rankItems: List<DescriptionValue>,
)

actual data class Ability actual constructor(
  actual val id: Long,
  actual val description: AbilityDescription,
  actual val summary: String,
  actual val url: String
)