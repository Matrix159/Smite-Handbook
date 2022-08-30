package com.matrix.presentation.models.filters

enum class Role(val roleName: String) {
  WARRIOR("Warrior"),
  MAGE("Mage"),
  ASSASSIN("Assassin"),
  HUNTER("Hunter"),
  GUARDIAN("Guardian")
}

enum class Pantheon(val pantheonName: String) {
  ARTHURIAN("Arthurian"),
  BABYLONIAN("Babylonian"),
  CELTIC("Celtic"),
  CHINESE("Chinese"),
  EGYPTIAN("Egyptian"),
  GREAT_OLD_ONES("Great Old Ones"),
  GREEK("Greek"),
  HINDU("Hindu"),
  JAPANESE("Japanese"),
  MAYA("Maya"),
  NORSE("Norse"),
  POLYNESIAN("Polynesian"),
  ROMAN("Roman"),
  SLAVIC("Slavic"),
  VOODOO("Voodoo"),
  YORUBA("Yoruba")
}

data class AppliedGodFilters(
  val searchText: String = "",
  val role: Role? = null,
  val pantheon: Pantheon? = null
//  // Type
//  val type: ItemType? = null,
//  // Tier
//  val tier: ItemTier? = null,
//  // Offense
//  val magicalPower: Boolean = false,
//  val magicalLifeSteal: Boolean = false,
//  val magicalFlatPen: Boolean = false,
//  val magicalPercentPen: Boolean = false,
//  val physicalPower: Boolean = false,
//  val physicalLifeSteal: Boolean = false,
//  val physicalFlatPen: Boolean = false,
//  val physicalPercentPen: Boolean = false,
//  val attackSpeed: Boolean = false,
//  val critChance: Boolean = false,
//  val basicAttackDamage: Boolean = false,
//  // Defense
//  val magicalProtection: Boolean = false,
//  val physicalProtection: Boolean = false,
//  val health: Boolean = false,
//  val hp5: Boolean = false,
//  val ccr: Boolean = false,
//  // Utility
//  val cdr: Boolean = false,
//  val mana: Boolean = false,
//  val mp5: Boolean = false,
//  val movementSpeed: Boolean = false,
)