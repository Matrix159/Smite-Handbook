package com.matrix.shared.data.model

import com.matrix.GodEntity
import com.matrix.ItemEntity
import com.matrix.SelectAllBuilds
import com.matrix.shared.data.model.builds.BuildInformation
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

internal fun GodApiResult.toDomain(): GodInformation =
  GodInformation(
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
      },
      secondaryDescription = this.itemDescription.secondaryDescription
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

internal fun ItemApiResult.toDomain(): ItemInformation =
  ItemInformation(
      itemID = this.itemID,
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
        },
        secondaryDescription = this.itemDescription.secondaryDescription
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

internal fun List<SelectAllBuilds>.toDomain(): List<BuildInformation> {
  return this.groupBy { it.id }.map { (buildId, results) ->
    val firstResult = results.first()
    BuildInformation(
      id = buildId,
      name = firstResult.name,
      god = GodInformation(
        id = firstResult.id_,
        abilityDetails1 = Ability(
          id = firstResult.abilityDetails1.id,
          description = AbilityDescription(
            cooldown = firstResult.abilityDetails1.description.cooldown,
            cost = firstResult.abilityDetails1.description.cost,
            description = firstResult.abilityDetails1.description.description,
            menuItems = firstResult.abilityDetails1.description.menuItems.map {
              DescriptionValue(
                it.description,
                it.value
              )
            },
            rankItems = firstResult.abilityDetails1.description.rankItems.map {
              DescriptionValue(
                it.description,
                it.value
              )
            },
          ),
          summary = firstResult.abilityDetails1.summary,
          url = firstResult.abilityDetails1.url
        ),
        abilityDetails2 = Ability(
          id = firstResult.abilityDetails2.id,
          description = AbilityDescription(
            cooldown = firstResult.abilityDetails2.description.cooldown,
            cost = firstResult.abilityDetails2.description.cost,
            description = firstResult.abilityDetails2.description.description,
            menuItems = firstResult.abilityDetails2.description.menuItems.map {
              DescriptionValue(
                it.description,
                it.value
              )
            },
            rankItems = firstResult.abilityDetails2.description.rankItems.map {
              DescriptionValue(
                it.description,
                it.value
              )
            },
          ),
          summary = firstResult.abilityDetails2.summary,
          url = firstResult.abilityDetails2.url
        ),
        abilityDetails3 = Ability(
          id = firstResult.abilityDetails3.id,
          description = AbilityDescription(
            cooldown = firstResult.abilityDetails3.description.cooldown,
            cost = firstResult.abilityDetails3.description.cost,
            description = firstResult.abilityDetails3.description.description,
            menuItems = firstResult.abilityDetails3.description.menuItems.map {
              DescriptionValue(
                it.description,
                it.value
              )
            },
            rankItems = firstResult.abilityDetails3.description.rankItems.map {
              DescriptionValue(
                it.description,
                it.value
              )
            },
          ),
          summary = firstResult.abilityDetails3.summary,
          url = firstResult.abilityDetails3.url
        ),
        abilityDetails4 = Ability(
          id = firstResult.abilityDetails4.id,
          description = AbilityDescription(
            cooldown = firstResult.abilityDetails4.description.cooldown,
            cost = firstResult.abilityDetails4.description.cost,
            description = firstResult.abilityDetails4.description.description,
            menuItems = firstResult.abilityDetails4.description.menuItems.map {
              DescriptionValue(
                it.description,
                it.value
              )
            },
            rankItems = firstResult.abilityDetails4.description.rankItems.map {
              DescriptionValue(
                it.description,
                it.value
              )
            },
          ),
          summary = firstResult.abilityDetails4.summary,
          url = firstResult.abilityDetails4.url
        ),
        abilityDetails5 = Ability(
          id = firstResult.abilityDetails5.id,
          description = AbilityDescription(
            cooldown = firstResult.abilityDetails5.description.cooldown,
            cost = firstResult.abilityDetails5.description.cost,
            description = firstResult.abilityDetails5.description.description,
            menuItems = firstResult.abilityDetails5.description.menuItems.map {
              DescriptionValue(
                it.description,
                it.value
              )
            },
            rankItems = firstResult.abilityDetails5.description.rankItems.map {
              DescriptionValue(
                it.description,
                it.value
              )
            },
          ),
          summary = firstResult.abilityDetails5.summary,
          url = firstResult.abilityDetails5.url
        ),
        basicAttack = AbilityDescription(
          cooldown = firstResult.basicAttack.cooldown,
          cost = firstResult.basicAttack.cost,
          description = firstResult.basicAttack.description,
          menuItems = firstResult.basicAttack.menuItems.map {
            DescriptionValue(
              it.description,
              it.value
            )
          },
          rankItems = firstResult.basicAttack.rankItems.map {
            DescriptionValue(
              it.description,
              it.value
            )
          },
        ),
        attackSpeed = firstResult.attackSpeed,
        attackSpeedPerLevel = firstResult.attackSpeedPerLevel,
        autoBanned = firstResult.autoBanned,
        cons = firstResult.cons,
        hp5PerLevel = firstResult.hp5PerLevel,
        health = firstResult.health,
        healthPerFive = firstResult.healthPerFive,
        healthPerLevel = firstResult.healthPerLevel,
        lore = firstResult.lore,
        mp5PerLevel = firstResult.mp5PerLevel,
        magicProtection = firstResult.magicProtection,
        magicProtectionPerLevel = firstResult.magicProtectionPerLevel,
        magicalPower = firstResult.magicalPower,
        magicalPowerPerLevel = firstResult.magicalPowerPerLevel,
        mana = firstResult.mana,
        manaPerFive = firstResult.manaPerFive,
        manaPerLevel = firstResult.manaPerLevel,
        name = firstResult.name_,
        onFreeRotation = firstResult.onFreeRotation,
        pantheon = firstResult.pantheon,
        physicalPower = firstResult.physicalPower,
        physicalPowerPerLevel = firstResult.physicalPowerPerLevel,
        physicalProtection = firstResult.physicalProtection,
        physicalProtectionPerLevel = firstResult.physicalProtectionPerLevel,
        pros = firstResult.pros,
        roles = firstResult.roles,
        speed = firstResult.speed,
        title = firstResult.title,
        type = firstResult.type,
        godCardURL = firstResult.godCardURL,
        godIconURL = firstResult.godIconURL,
        latestGod = firstResult.latestGod,
      ),
      items = results.map {
        ItemInformation(
          itemID = it.id__,
          activeFlag = it.activeFlag,
          childItemID = it.childItemID,
          deviceName = it.deviceName,
          glyph = it.glyph,
          iconID = it.iconID,
          itemTier = it.itemTier,
          price = it.price,
          restrictedRoles = it.restrictedRoles,
          rootItemID = it.rootItemID,
          shortDesc = it.shortDesc,
          startingItem = it.startingItem,
          type = it.type_,
          itemIconURL = it.itemIconURL,
          itemDescription = it.itemDescription
        )
      }
    )
  }
}
