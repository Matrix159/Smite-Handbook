package com.matrix.presentation.utils

import com.matrix.presentation.R

/**
 * Returns the drawable resource id associated with the input role, else 0 if none maps
 */
fun getRoleDrawableResourceId(role: String): Int {
  return when (role) {
    "Warrior" -> R.drawable.warrior
    "Guardian" -> R.drawable.guardian
    "Assassin" -> R.drawable.assassin
    "Hunter" -> R.drawable.hunter
    "Mage" -> R.drawable.mage
    else -> 0
  }
}

/**
 * Returns the string resource id associated with the input role, else 0 if none maps
 */
fun getRoleStringResourceId(role: String): Int {
  return when (role) {
    "Warrior" -> R.string.warrior
    "Guardian" -> R.string.guardian
    "Assassin" -> R.string.assassin
    "Hunter" -> R.string.hunter
    "Mage" -> R.string.mage
    else -> 0
  }
}

/**
 * Returns the drawable resource id associated with the input pantheon, else 0 if none maps
 */
fun getPantheonDrawableResourceId(pantheon: String): Int {
  return when (pantheon) {
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

/**
 * Returns the string resource id associated with the input pantheon, else 0 if none maps
 */
fun getPantheonStringResourceId(pantheon: String): Int {
  return when (pantheon) {
    "Arthurian" -> R.string.arthurian
    "Babylonian" -> R.string.babylonian
    "Celtic" -> R.string.celtic
    "Chinese" -> R.string.chinese
    "Egyptian" -> R.string.egyptian
    "Great Old Ones" -> R.string.great_old_ones
    "Greek" -> R.string.greek
    "Hindu" -> R.string.hindu
    "Japanese" -> R.string.japanese
    "Maya" -> R.string.maya
    "Norse" -> R.string.norse
    "Polynesian" -> R.string.polynesian
    "Roman" -> R.string.roman
    "Slavic" -> R.string.slavic
    "Voodoo" -> R.string.voodoo
    "Yoruba" -> R.string.yoruba
    else -> 0
  }
}