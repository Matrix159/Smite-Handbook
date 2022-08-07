package com.matrix.presentation.models.filters

data class AppliedFilters(
  val searchText: String = "",
  // Type
  val consumable: Boolean = false,
  val item: Boolean = false,
  val active: Boolean = false,
  // Tier
  val tier1: Boolean = false,
  val tier2: Boolean = false,
  val tier3: Boolean = false,
  val tier4: Boolean = false,
  // Offense
  val magicalPower: Boolean = false,
  val magicalLifeSteal: Boolean = false,
  val magicalFlatPen: Boolean = false,
  val magicalPercentPen: Boolean = false,
  val physicalPower: Boolean = false,
  val physicalLifeSteal: Boolean = false,
  val physicalFlatPen: Boolean = false,
  val physicalPercentPen: Boolean = false,
  val attackSpeed: Boolean = false,
  val critChance: Boolean = false,
  val basicAttackDamage: Boolean = false,
  // Defense
  val magicalProtection: Boolean = false,
  val physicalProtection: Boolean = false,
  val health: Boolean = false,
  val hp5: Boolean = false,
  val ccr: Boolean = false,
  // Utility
  val cdr: Boolean = false,
  val mana: Boolean = false,
  val mp5: Boolean = false,
  val movementSpeed: Boolean = false,
)