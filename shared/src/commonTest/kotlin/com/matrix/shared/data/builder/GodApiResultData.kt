package com.matrix.shared.data.builder

import com.matrix.shared.data.network.model.Ability
import com.matrix.shared.data.network.model.AbilityDescription
import com.matrix.shared.data.network.model.AbilityItemDescription
import com.matrix.shared.data.network.model.GodApiResult
import com.matrix.shared.data.network.model.LowerDescriptionValue

internal fun getMockGodApiResult(id: Long) = GodApiResult(
  id = id,
  ability1 = "ability1 test",
  ability2 = "ability2 test",
  ability3 = "ability3 test",
  ability4 = "ability4 test",
  ability5 = "ability5 test",
  abilityId1 = 1,
  abilityId2 = 1,
  abilityId3 = 1,
  abilityId4 = 1,
  abilityId5 = 1,
  abilityDetails1 = getMockAbility(),
  abilityDetails2 = getMockAbility(),
  abilityDetails3 = getMockAbility(),
  abilityDetails4 = getMockAbility(),
  abilityDetails5 = getMockAbility(),
  attackSpeed = 1.0,
  attackSpeedPerLevel = 1.0,
  autoBanned = "n",
  cons = "cons test",
  hp5PerLevel = 1.0,
  health = 1,
  healthPerFive = 1.0,
  healthPerLevel = 1.0,
  lore = "lore test",
  mp5PerLevel = 1.0,
  magicProtection = 1.0,
  magicProtectionPerLevel = 1.0,
  magicalPower = 1,
  magicalPowerPerLevel = 1.0,
  mana = 1,
  manaPerFive = 1.0,
  manaPerLevel = 1.0,
  name = "name test",
  onFreeRotation = "n",
  pantheon = "pantheon test",
  physicalPower = 1,
  physicalPowerPerLevel = 1.0,
  physicalProtection = 1.0,
  physicalProtectionPerLevel = 1.0,
  pros = "pros test",
  roles = "roles test",
  speed = 1,
  title = "title test",
  type = "god",
  abilityDescription1 = getMockAbilityDescription(),
  abilityDescription2 = getMockAbilityDescription(),
  abilityDescription3 = getMockAbilityDescription(),
  abilityDescription4 = getMockAbilityDescription(),
  abilityDescription5 = getMockAbilityDescription(),
  basicAttack = getMockAbilityDescription(),
  godAbility1URL = "godAbility1URL test",
  godAbility2URL = "godAbility2URL test",
  godAbility3URL = "godAbility3URL test",
  godAbility4URL = "godAbility4URL test",
  godAbility5URL = "godAbility5URL test",
  godCardURL = "godCardURL test",
  godIconURL = "godIconURL test",
  latestGod = "n",
  retMsg = "retMsg test"
)

private fun getMockAbilityDescription() = AbilityDescription(
  itemDescription = getMockAbilityItemDescription()
)

private fun getMockAbility() = Ability(
  description = getMockAbilityDescription(),
  id = 1,
  summary = "summary test",
  url = "url test"
)

private fun getMockAbilityItemDescription() = AbilityItemDescription(
  cooldown = "40",
  cost = "60",
  description = "description test",
  menuitems = listOf(getMockLowerDescriptionValue()),
  rankitems = listOf(getMockLowerDescriptionValue())
)

private fun getMockLowerDescriptionValue() = LowerDescriptionValue(
  description = "description test",
  value = "value test"
)