package com.matrix.shared.data.builder

import com.matrix.shared.data.model.gods.Ability
import com.matrix.shared.data.model.gods.AbilityDescription
import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.model.shared.DescriptionValue

internal fun getMockGodInformation(id: Long) = GodInformation(
  id = id,
  abilityDetails1 = getMockAbility(),
  abilityDetails2 = getMockAbility(),
  abilityDetails3 = getMockAbility(),
  abilityDetails4 = getMockAbility(),
  abilityDetails5 = getMockAbility(),
  basicAttack = getMockAbilityDescription(),
  attackSpeed = 1.0,
  attackSpeedPerLevel = 1.0,
  autoBanned = true,
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
  onFreeRotation = true,
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
  godCardURL = "godCardURL test",
  godIconURL = "godIconURL test",
  latestGod = true
)

private fun getMockAbilityDescription() = AbilityDescription(
  cooldown = "60",
  cost = "60",
  description = "description test",
  menuItems = listOf(getMockDescriptionValue()),
  rankItems = listOf(getMockDescriptionValue())
)

private fun getMockAbility() = Ability(
  id = 1,
  description = getMockAbilityDescription(),
  summary = "summary test",
  url = "url test"
)

private fun getMockDescriptionValue() = DescriptionValue(
  description = "test description",
  value = "test value"
)