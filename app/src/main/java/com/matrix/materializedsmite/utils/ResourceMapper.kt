package com.matrix.materializedsmite.utils

import com.matrix.materializedsmite.R

/**
 * Returns the resource id associated with the input role, else 0 if none maps
 */
fun getRoleResourceId(role: String): Int {
  return when(role) {
    "Warrior" -> R.drawable.warrior
    "Guardian" -> R.drawable.guardian
    "Assassin" -> R.drawable.assassin
    "Hunter" -> R.drawable.hunter
    "Mage" -> R.drawable.mage
    else -> 0
  }
}

/**
 * Returns the resource id associated with the input pantheon, else 0 if none maps
 */
fun getPantheonResourceId(pantheon: String): Int {
  return when(pantheon) {
    "Arthurian" -> R.drawable.arthurian
    "Babylonian" -> R.drawable.babylonian
    "Celtic" -> R.drawable.celtic
    "Chinese" -> R.drawable.chinese
    "Egyptian" -> R.drawable.egyptian
    "Great Old Ones" -> R.drawable.great_old_ones
    "Greek" -> R.drawable.greek
    "Hindu" -> R.drawable.hindu
    "Japanese" -> R.drawable.japanese
    "Maya" -> R.drawable.maya
    "Norse" -> R.drawable.norse
    "Polynesian" -> R.drawable.polynesian
    "Roman" -> R.drawable.roman
    "Slavic" -> R.drawable.slavic
    "Voodoo" -> R.drawable.voodoo
    "Yoruba" -> R.drawable.yoruba
    else -> 0
  }
}
