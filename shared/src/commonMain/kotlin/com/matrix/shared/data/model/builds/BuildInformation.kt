package com.matrix.shared.data.model.builds

import com.matrix.shared.data.model.gods.Ability
import com.matrix.shared.data.model.gods.AbilityDescription
import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.model.items.ItemDescription
import com.matrix.shared.data.model.items.ItemInformation

data class BuildInformation(
  val id: Long? = null,
  val god: GodInformation,
  val items: List<ItemInformation>,
  val name: String? = null,
) {
  companion object {
    fun default(): BuildInformation {
      return BuildInformation(
        id = 123,
        god = GodInformation(
          id = 4376,
          abilityDetails1 = Ability(
            id = 6193,
            description = AbilityDescription(
              cooldown = "minim",
              cost = "sonet",
              description = "eruditi",
              menuItems = listOf(),
              rankItems = listOf()
            ),
            summary = "placerat",
            url = "https://search.yahoo.com/search?p=doctus"
          ),
          abilityDetails2 = Ability(
            id = 6319,
            description = AbilityDescription(
              cooldown = "cetero",
              cost = "voluptatum",
              description = "nonumes",
              menuItems = listOf(),
              rankItems = listOf()
            ),
            summary = "vehicula",
            url = "http://www.bing.com/search?q=eruditi"
          ),
          abilityDetails3 = Ability(
            id = 4815,
            description = AbilityDescription(
              cooldown = "iisque",
              cost = "vel",
              description = "regione",
              menuItems = listOf(),
              rankItems = listOf()
            ),
            summary = "ornatus",
            url = "https://duckduckgo.com/?q=graeci"
          ),
          abilityDetails4 = Ability(
            id = 7759,
            description = AbilityDescription(
              cooldown = "cu",
              cost = "menandri",
              description = "doctus",
              menuItems = listOf(),
              rankItems = listOf()
            ),
            summary = "sollicitudin",
            url = "https://duckduckgo.com/?q=primis"
          ),
          abilityDetails5 = Ability(
            id = 4254,
            description = AbilityDescription(
              cooldown = "duis",
              cost = "voluptatum",
              description = "sed",
              menuItems = listOf(),
              rankItems = listOf()
            ),
            summary = "turpis",
            url = "https://www.google.com/#q=mnesarchum"
          ),
          basicAttack = AbilityDescription(
            cooldown = "ornatus",
            cost = "habitasse",
            description = "mollis",
            menuItems = listOf(),
            rankItems = listOf()
          ),
          attackSpeed = 84.85,
          attackSpeedPerLevel = 86.87,
          autoBanned = false,
          cons = "corrumpit",
          hp5PerLevel = 88.89,
          health = 2560,
          healthPerFive = 90.91,
          healthPerLevel = 92.93,
          lore = "constituam",
          mp5PerLevel = 94.95,
          magicProtection = 96.97,
          magicProtectionPerLevel = 98.99,
          magicalPower = 3287,
          magicalPowerPerLevel = 100.101,
          mana = 3023,
          manaPerFive = 102.103,
          manaPerLevel = 104.105,
          name = "Fidel Gonzalez",
          onFreeRotation = false,
          pantheon = "vestibulum",
          physicalPower = 1243,
          physicalPowerPerLevel = 106.107,
          physicalProtection = 108.109,
          physicalProtectionPerLevel = 110.111,
          pros = "interesset",
          roles = "pretium",
          speed = 7633,
          title = "hac",
          type = "inimicus",
          godCardURL = "https://www.google.com/#q=arcu",
          godIconURL = "http://www.bing.com/search?q=adipisci",
          latestGod = false
        ),
        items = listOf(
          ItemInformation(
            itemID = 6281,
            activeFlag = false,
            childItemID = 1492,
            deviceName = "Antonio Chan",
            glyph = false,
            iconID = 4447,
            itemDescription = ItemDescription(
              description = null,
              menuItems = listOf(),
              secondaryDescription = null
            ),
            itemTier = 5479,
            price = 1651,
            restrictedRoles = "mauris",
            rootItemID = 2436,
            shortDesc = "penatibus",
            startingItem = false,
            type = "maiorum",
            itemIconURL = "http://www.bing.com/search?q=sem"
          )
        ),
        name = "The build of all builds"
      )
    }
  }
}




