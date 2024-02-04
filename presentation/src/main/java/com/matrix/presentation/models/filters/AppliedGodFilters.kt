package com.matrix.presentation.models.filters

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

enum class Role(val roleName: String) {
  ASSASSIN("Assassin"),
  GUARDIAN("Guardian"),
  HUNTER("Hunter"),
  MAGE("Mage"),
  WARRIOR("Warrior"),
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

@Parcelize
data class AppliedGodFilters(
  val searchText: String = "",
  val roles: Set<Role> = emptySet(),
  val pantheons: Set<Pantheon> = emptySet()
) : Parcelable