package com.matrix.shared.data.model

import com.matrix.GodEntity
import com.matrix.ItemEntity
import com.matrix.shared.data.model.gods.Ability
import com.matrix.shared.data.model.gods.AbilityDescription
import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.model.items.ItemDescription
import com.matrix.shared.data.model.items.ItemInformation
import com.matrix.shared.data.model.shared.DescriptionValue
import com.matrix.shared.data.network.model.GodApiResult
import com.matrix.shared.data.network.model.ItemApiResult

internal fun GodEntity.toDomain(): GodInformation =
  GodInformation(
    id = this.id,
    abilityDetails1 = Ability(
      id = this.abilityDetails1.id,
      description = AbilityDescription(
        cooldown = this.abilityDetails1.description.cooldown,
        cost = this.abilityDetails1.description.cost,
        description = this.abilityDetails1.description.description,
        menuItems = this.abilityDetails1.description.menuItems.map {
          DescriptionValue(
            it.description,
            it.value
          )
        },
        rankItems = this.abilityDetails1.description.rankItems.map {
          DescriptionValue(
            it.description,
            it.value
          )
        },
      ),
      summary = this.abilityDetails1.summary,
      url = this.abilityDetails1.url
    ),
    abilityDetails2 = Ability(
      id = this.abilityDetails2.id,
      description = AbilityDescription(
        cooldown = this.abilityDetails2.description.cooldown,
        cost = this.abilityDetails2.description.cost,
        description = this.abilityDetails2.description.description,
        menuItems = this.abilityDetails2.description.menuItems.map {
          DescriptionValue(
            it.description,
            it.value
          )
        },
        rankItems = this.abilityDetails2.description.rankItems.map {
          DescriptionValue(
            it.description,
            it.value
          )
        },
      ),
      summary = this.abilityDetails2.summary,
      url = this.abilityDetails2.url
    ),
    abilityDetails3 = Ability(
      id = this.abilityDetails3.id,
      description = AbilityDescription(
        cooldown = this.abilityDetails3.description.cooldown,
        cost = this.abilityDetails3.description.cost,
        description = this.abilityDetails3.description.description,
        menuItems = this.abilityDetails3.description.menuItems.map {
          DescriptionValue(
            it.description,
            it.value
          )
        },
        rankItems = this.abilityDetails3.description.rankItems.map {
          DescriptionValue(
            it.description,
            it.value
          )
        },
      ),
      summary = this.abilityDetails3.summary,
      url = this.abilityDetails3.url
    ),
    abilityDetails4 = Ability(
      id = this.abilityDetails4.id,
      description = AbilityDescription(
        cooldown = this.abilityDetails4.description.cooldown,
        cost = this.abilityDetails4.description.cost,
        description = this.abilityDetails4.description.description,
        menuItems = this.abilityDetails4.description.menuItems.map {
          DescriptionValue(
            it.description,
            it.value
          )
        },
        rankItems = this.abilityDetails4.description.rankItems.map {
          DescriptionValue(
            it.description,
            it.value
          )
        },
      ),
      summary = this.abilityDetails4.summary,
      url = this.abilityDetails4.url
    ),
    abilityDetails5 = Ability(
      id = this.abilityDetails5.id,
      description = AbilityDescription(
        cooldown = this.abilityDetails5.description.cooldown,
        cost = this.abilityDetails5.description.cost,
        description = this.abilityDetails5.description.description,
        menuItems = this.abilityDetails5.description.menuItems.map {
          DescriptionValue(
            it.description,
            it.value
          )
        },
        rankItems = this.abilityDetails5.description.rankItems.map {
          DescriptionValue(
            it.description,
            it.value
          )
        },
      ),
      summary = this.abilityDetails5.summary,
      url = this.abilityDetails5.url
    ),
    basicAttack = AbilityDescription(
      cooldown = this.basicAttack.cooldown,
      cost = this.basicAttack.cost,
      description = this.basicAttack.description,
      menuItems = this.basicAttack.menuItems.map {
        DescriptionValue(
          it.description,
          it.value
        )
      },
      rankItems = this.basicAttack.rankItems.map {
        DescriptionValue(
          it.description,
          it.value
        )
      },
    ),
    attackSpeed = this.attackSpeed,
    attackSpeedPerLevel = this.attackSpeedPerLevel,
    autoBanned = this.autoBanned,
    cons = this.cons,
    hp5PerLevel = this.hp5PerLevel,
    health = this.health,
    healthPerFive = this.healthPerFive,
    healthPerLevel = this.healthPerLevel,
    lore = this.lore,
    mp5PerLevel = this.mp5PerLevel,
    magicProtection = this.magicProtection,
    magicProtectionPerLevel = this.magicProtectionPerLevel,
    magicalPower = this.magicalPower,
    magicalPowerPerLevel = this.magicalPowerPerLevel,
    mana = this.mana,
    manaPerFive = this.manaPerFive,
    manaPerLevel = this.manaPerLevel,
    name = this.name,
    onFreeRotation = this.onFreeRotation,
    pantheon = this.pantheon,
    physicalPower = this.physicalPower,
    physicalPowerPerLevel = this.physicalPowerPerLevel,
    physicalProtection = this.physicalProtection,
    physicalProtectionPerLevel = this.physicalProtectionPerLevel,
    pros = this.pros,
    roles = this.roles,
    speed = this.speed,
    title = this.title,
    type = this.type,
    godCardURL = this.godCardURL,
    godIconURL = this.godIconURL,
    latestGod = this.latestGod,
  )

internal fun GodApiResult.toEntity(): GodEntity =
  GodEntity(
    id = this.id,
    abilityDetails1 = Ability(
      id = this.abilityDetails1.id,
      description = AbilityDescription(
        cooldown = this.abilityDetails1.description.itemDescription.cooldown,
        cost = this.abilityDetails1.description.itemDescription.cost,
        description = this.abilityDetails1.description.itemDescription.description,
        menuItems = this.abilityDetails1.description.itemDescription.menuitems.map {
          DescriptionValue(
            it.description,
            it.value
          )
        },
        rankItems = this.abilityDetails1.description.itemDescription.rankitems.map {
          DescriptionValue(
            it.description,
            it.value
          )
        },
      ),
      summary = this.abilityDetails1.summary,
      url = this.abilityDetails1.url
    ),
    abilityDetails2 = Ability(
      id = this.abilityDetails2.id,
      description = AbilityDescription(
        cooldown = this.abilityDetails2.description.itemDescription.cooldown,
        cost = this.abilityDetails2.description.itemDescription.cost,
        description = this.abilityDetails2.description.itemDescription.description,
        menuItems = this.abilityDetails2.description.itemDescription.menuitems.map {
          DescriptionValue(
            it.description,
            it.value
          )
        },
        rankItems = this.abilityDetails2.description.itemDescription.rankitems.map {
          DescriptionValue(
            it.description,
            it.value
          )
        },
      ),
      summary = this.abilityDetails2.summary,
      url = this.abilityDetails2.url
    ),
    abilityDetails3 = Ability(
      id = this.abilityDetails3.id,
      description = AbilityDescription(
        cooldown = this.abilityDetails3.description.itemDescription.cooldown,
        cost = this.abilityDetails3.description.itemDescription.cost,
        description = this.abilityDetails3.description.itemDescription.description,
        menuItems = this.abilityDetails3.description.itemDescription.menuitems.map {
          DescriptionValue(
            it.description,
            it.value
          )
        },
        rankItems = this.abilityDetails3.description.itemDescription.rankitems.map {
          DescriptionValue(
            it.description,
            it.value
          )
        },
      ),
      summary = this.abilityDetails3.summary,
      url = this.abilityDetails3.url
    ),
    abilityDetails4 = Ability(
      id = this.abilityDetails4.id,
      description = AbilityDescription(
        cooldown = this.abilityDetails4.description.itemDescription.cooldown,
        cost = this.abilityDetails4.description.itemDescription.cost,
        description = this.abilityDetails4.description.itemDescription.description,
        menuItems = this.abilityDetails4.description.itemDescription.menuitems.map {
          DescriptionValue(
            it.description,
            it.value
          )
        },
        rankItems = this.abilityDetails4.description.itemDescription.rankitems.map {
          DescriptionValue(
            it.description,
            it.value
          )
        },
      ),
      summary = this.abilityDetails4.summary,
      url = this.abilityDetails4.url
    ),
    abilityDetails5 = Ability(
      id = this.abilityDetails5.id,
      description = AbilityDescription(
        cooldown = this.abilityDetails5.description.itemDescription.cooldown,
        cost = this.abilityDetails5.description.itemDescription.cost,
        description = this.abilityDetails5.description.itemDescription.description,
        menuItems = this.abilityDetails5.description.itemDescription.menuitems.map {
          DescriptionValue(
            it.description,
            it.value
          )
        },
        rankItems = this.abilityDetails5.description.itemDescription.rankitems.map {
          DescriptionValue(
            it.description,
            it.value
          )
        },
      ),
      summary = this.abilityDetails5.summary,
      url = this.abilityDetails5.url
    ),
    attackSpeed = this.attackSpeed,
    attackSpeedPerLevel = this.attackSpeedPerLevel,
    autoBanned = this.autoBanned == "y",
    cons = this.cons,
    hp5PerLevel = this.hp5PerLevel,
    health = this.health,
    healthPerFive = this.healthPerFive,
    healthPerLevel = this.healthPerLevel,
    lore = this.lore,
    mp5PerLevel = this.mp5PerLevel,
    magicProtection = this.magicProtection,
    magicProtectionPerLevel = this.magicProtectionPerLevel,
    magicalPower = this.magicalPower,
    magicalPowerPerLevel = this.magicalPowerPerLevel,
    mana = this.mana,
    manaPerFive = this.manaPerFive,
    manaPerLevel = this.manaPerLevel,
    name = this.name,
    onFreeRotation = this.onFreeRotation == "true",
    pantheon = this.pantheon,
    physicalPower = this.physicalPower,
    physicalPowerPerLevel = this.physicalPowerPerLevel,
    physicalProtection = this.physicalProtection,
    physicalProtectionPerLevel = this.physicalProtectionPerLevel,
    pros = this.pros,
    roles = this.roles,
    speed = this.speed,
    title = this.title,
    type = this.type,
    basicAttack = AbilityDescription(
      cooldown = this.basicAttack.itemDescription.cooldown,
      cost = this.basicAttack.itemDescription.cost,
      description = this.basicAttack.itemDescription.description,
      menuItems = this.basicAttack.itemDescription.menuitems.map {
        DescriptionValue(
          it.description,
          it.value
        )
      },
      rankItems = this.basicAttack.itemDescription.rankitems.map {
        DescriptionValue(
          it.description,
          it.value
        )
      },
    ),
    godCardURL = this.godCardURL,
    godIconURL = this.godIconURL,
    latestGod = this.latestGod == "y",
  )

internal fun ItemEntity.toDomain(): ItemInformation =
  ItemInformation(
    itemID = this.id,
    activeFlag = this.activeFlag,
    childItemID = this.childItemID,
    deviceName = this.deviceName,
    glyph = this.glyph,
    iconID = this.iconID,
    itemDescription = ItemDescription(
      description = this.itemDescription.description,
      menuItems = this.itemDescription.menuItems.map {
        DescriptionValue(
          description = it.description,
          value = it.value
        )
      }
    ),
    itemTier = this.itemTier,
    price = this.price,
    restrictedRoles = this.restrictedRoles,
    rootItemID = this.rootItemID,
    shortDesc = this.shortDesc,
    startingItem = this.startingItem,
    type = this.type,
    itemIconURL = this.itemIconURL
  )

internal fun ItemApiResult.toEntity(): ItemEntity =
  ItemEntity(
      id = this.itemID,
      activeFlag = this.activeFlag == "y",
      childItemID = this.childItemID,
      deviceName = this.deviceName,
      glyph = this.glyph == "y",
      iconID = this.iconID,
      itemDescription = ItemDescription(
        description = this.itemDescription.description,
        menuItems = this.itemDescription.menuItems.map {
          DescriptionValue(
            description = it.description,
            value = it.value
          )
        }
      ),
      itemTier = this.itemTier,
      price = this.price,
      restrictedRoles = this.restrictedRoles,
      rootItemID = this.rootItemID,
      shortDesc = this.shortDesc,
      startingItem = this.startingItem,
      type = this.type,
      itemIconURL = this.itemIconURL
    )


//fun BuildEntity.toDomain(): BuildInformation =
//  BuildInformation(
//    id = this.id,
//    name = this.name,
//    god = god.toDomain(),
//    items = items.map { it.toDomain() },
//  )