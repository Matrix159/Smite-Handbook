package com.matrix.domain.models

data class GodInformation(
  val id: Int,
  val abilityDetails1: Ability,
  val abilityDetails2: Ability,
  val abilityDetails3: Ability,
  val abilityDetails4: Ability,
  val abilityDetails5: Ability,
  val basicAttack: AbilityDescription,
  val attackSpeed: Double,
  val attackSpeedPerLevel: Double,
  val autoBanned: Boolean,
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
  val onFreeRotation: Boolean,
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
  var godCardURL: String,
  val godIconURL: String,
  val latestGod: Boolean,
) {
  companion object {
    fun build(): GodInformation =
      GodInformation(
        abilityDetails1 = Ability(
          id = 0,
          summary = "",
          url = "",
          description = AbilityDescription(
            cooldown = "",
            cost = "",
            description = "",
            menuItems = listOf(),
            rankItems = listOf(),
          ),
        ),
        abilityDetails2 = Ability(
          id = 0,
          summary = "",
          url = "",
          description = AbilityDescription(
            cooldown = "",
            cost = "",
            description = "",
            menuItems = listOf(),
            rankItems = listOf(),
          ),
        ),
        abilityDetails3 = Ability(
          id = 0,
          summary = "",
          url = "",
          description = AbilityDescription(
            cooldown = "",
            cost = "",
            description = "",
            menuItems = listOf(),
            rankItems = listOf(),
          ),
        ),
        abilityDetails4 = Ability(
          id = 0,
          summary = "",
          url = "",
          description = AbilityDescription(
            cooldown = "",
            cost = "",
            description = "",
            menuItems = listOf(),
            rankItems = listOf(),
          ),
        ),
        abilityDetails5 = Ability(
          id = 0,
          summary = "",
          url = "",
          description = AbilityDescription(
            cooldown = "",
            cost = "",
            description = "",
            menuItems = listOf(),
            rankItems = listOf(),
          ),
        ),
        attackSpeed = 0.0,
        attackSpeedPerLevel = 0.0,
        autoBanned = false,
        cons = "",
        hp5PerLevel = 0.0,
        health = 0,
        healthPerFive = 0.0,
        healthPerLevel = 0.0,
        lore = "",
        mp5PerLevel = 0.0,
        magicProtection = 0.0,
        magicProtectionPerLevel = 0.0,
        magicalPower = 0,
        magicalPowerPerLevel = 0.0,
        mana = 0,
        manaPerFive = 0.0,
        manaPerLevel = 0.0,
        name = "",
        onFreeRotation = false,
        pantheon = "",
        physicalPower = 0,
        physicalPowerPerLevel = 0.0,
        physicalProtection = 0.0,
        physicalProtectionPerLevel = 0.0,
        pros = "",
        roles = "",
        speed = 0,
        title = "",
        type = "",
        basicAttack = AbilityDescription(
          cooldown = "",
          cost = "",
          description = "",
          menuItems = listOf(),
          rankItems = listOf()
        ),
        godCardURL = "",
        godIconURL = "",
        id = 0,
        latestGod = false
      )
  }
}


data class AbilityDescription(
  val cooldown: String,
  val cost: String,
  val description: String,
  val menuItems: List<DescriptionValue>,
  val rankItems: List<DescriptionValue>
)

data class Ability(
  val id: Long,
  val description: AbilityDescription,
  val summary: String,
  val url: String
)


