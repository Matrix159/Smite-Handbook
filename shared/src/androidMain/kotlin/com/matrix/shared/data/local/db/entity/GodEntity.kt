package com.matrix.shared.data.local.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.matrix.shared.data.model.shared.DescriptionValue
import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.network.model.GodApiResult
import com.matrix.shared.data.model.Ability as DomainAbility
import com.matrix.shared.data.model.AbilityDescription as DomainAbilityDescription
import com.matrix.shared.data.model.shared.DescriptionValue as DomainDescriptionValue

@Entity(tableName = "gods")
actual data class GodEntity actual constructor(
  @PrimaryKey
  actual val id: Int,
  actual val patchVersion: String?,
  @Embedded(prefix = "ability_details_1_")
  actual val abilityDetails1: Ability,
  @Embedded(prefix = "ability_details_2_")
  actual val abilityDetails2: Ability,
  @Embedded(prefix = "ability_details_3_")
  actual val abilityDetails3: Ability,
  @Embedded(prefix = "ability_details_4_")
  actual val abilityDetails4: Ability,
  @Embedded(prefix = "ability_details_5_")
  actual val abilityDetails5: Ability,
  @Embedded(prefix = "basic_attack_desc")
  actual val basicAttack: AbilityDescription,
  actual val attackSpeed: Double,
  actual val attackSpeedPerLevel: Double,
  actual val autoBanned: Boolean,
  actual val cons: String,
  actual val hp5PerLevel: Double,
  actual val health: Long,
  actual val healthPerFive: Double,
  actual val healthPerLevel: Double,
  actual val lore: String,
  actual val mp5PerLevel: Double,
  actual val magicProtection: Double,
  actual val magicProtectionPerLevel: Double,
  actual val magicalPower: Long,
  actual val magicalPowerPerLevel: Double,
  actual val mana: Long,
  actual val manaPerFive: Double,
  actual val manaPerLevel: Double,
  actual val name: String,
  actual val onFreeRotation: Boolean,
  actual val pantheon: String,
  actual val physicalPower: Long,
  actual val physicalPowerPerLevel: Double,
  actual val physicalProtection: Double,
  actual val physicalProtectionPerLevel: Double,
  actual val pros: String,
  actual val roles: String,
  actual val speed: Long,
  actual val title: String,
  actual val type: String,
  actual val godCardURL: String,
  actual val godIconURL: String,
  actual val latestGod: Boolean,
) {
  //region conversions
  actual fun toDomain(): GodInformation =
    GodInformation(
      id = this.id,
      abilityDetails1 = DomainAbility(
        id = this.abilityDetails1.id,
        description = DomainAbilityDescription(
          cooldown = this.abilityDetails1.description.cooldown,
          cost = this.abilityDetails1.description.cost,
          description = this.abilityDetails1.description.description,
          menuItems = this.abilityDetails1.description.menuItems.map {
            DomainDescriptionValue(
              it.description,
              it.value
            )
          },
          rankItems = this.abilityDetails1.description.rankItems.map {
            DomainDescriptionValue(
              it.description,
              it.value
            )
          },
        ),
        summary = this.abilityDetails1.summary,
        url = this.abilityDetails1.url
      ),
      abilityDetails2 = DomainAbility(
        id = this.abilityDetails2.id,
        description = DomainAbilityDescription(
          cooldown = this.abilityDetails2.description.cooldown,
          cost = this.abilityDetails2.description.cost,
          description = this.abilityDetails2.description.description,
          menuItems = this.abilityDetails2.description.menuItems.map {
            DomainDescriptionValue(
              it.description,
              it.value
            )
          },
          rankItems = this.abilityDetails2.description.rankItems.map {
            DomainDescriptionValue(
              it.description,
              it.value
            )
          },
        ),
        summary = this.abilityDetails2.summary,
        url = this.abilityDetails2.url
      ),
      abilityDetails3 = DomainAbility(
        id = this.abilityDetails3.id,
        description = DomainAbilityDescription(
          cooldown = this.abilityDetails3.description.cooldown,
          cost = this.abilityDetails3.description.cost,
          description = this.abilityDetails3.description.description,
          menuItems = this.abilityDetails3.description.menuItems.map {
            DomainDescriptionValue(
              it.description,
              it.value
            )
          },
          rankItems = this.abilityDetails3.description.rankItems.map {
            DomainDescriptionValue(
              it.description,
              it.value
            )
          },
        ),
        summary = this.abilityDetails3.summary,
        url = this.abilityDetails3.url
      ),
      abilityDetails4 = DomainAbility(
        id = this.abilityDetails4.id,
        description = DomainAbilityDescription(
          cooldown = this.abilityDetails4.description.cooldown,
          cost = this.abilityDetails4.description.cost,
          description = this.abilityDetails4.description.description,
          menuItems = this.abilityDetails4.description.menuItems.map {
            DomainDescriptionValue(
              it.description,
              it.value
            )
          },
          rankItems = this.abilityDetails4.description.rankItems.map {
            DomainDescriptionValue(
              it.description,
              it.value
            )
          },
        ),
        summary = this.abilityDetails4.summary,
        url = this.abilityDetails4.url
      ),
      abilityDetails5 = DomainAbility(
        id = this.abilityDetails5.id,
        description = DomainAbilityDescription(
          cooldown = this.abilityDetails5.description.cooldown,
          cost = this.abilityDetails5.description.cost,
          description = this.abilityDetails5.description.description,
          menuItems = this.abilityDetails5.description.menuItems.map {
            DomainDescriptionValue(
              it.description,
              it.value
            )
          },
          rankItems = this.abilityDetails5.description.rankItems.map {
            DomainDescriptionValue(
              it.description,
              it.value
            )
          },
        ),
        summary = this.abilityDetails5.summary,
        url = this.abilityDetails5.url
      ),
      basicAttack = DomainAbilityDescription(
        cooldown = this.basicAttack.cooldown,
        cost = this.basicAttack.cost,
        description = this.basicAttack.description,
        menuItems = this.basicAttack.menuItems.map {
          DomainDescriptionValue(
            it.description,
            it.value
          )
        },
        rankItems = this.basicAttack.rankItems.map {
          DomainDescriptionValue(
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

  actual companion object {
    actual fun fromApi(god: GodApiResult, patchVersion: String?): GodEntity =
      GodEntity(
        id = god.id,
        patchVersion = patchVersion,
        abilityDetails1 = Ability(
          id = god.abilityDetails1.id,
          description = AbilityDescription(
            cooldown = god.abilityDetails1.description.itemDescription.cooldown,
            cost = god.abilityDetails1.description.itemDescription.cost,
            description = god.abilityDetails1.description.itemDescription.description,
            menuItems = god.abilityDetails1.description.itemDescription.menuitems.map {
              DomainDescriptionValue(
                it.description,
                it.value
              )
            },
            rankItems = god.abilityDetails1.description.itemDescription.rankitems.map {
              DomainDescriptionValue(
                it.description,
                it.value
              )
            },
          ),
          summary = god.abilityDetails1.summary,
          url = god.abilityDetails1.url
        ),
        abilityDetails2 = Ability(
          id = god.abilityDetails2.id,
          description = AbilityDescription(
            cooldown = god.abilityDetails2.description.itemDescription.cooldown,
            cost = god.abilityDetails2.description.itemDescription.cost,
            description = god.abilityDetails2.description.itemDescription.description,
            menuItems = god.abilityDetails2.description.itemDescription.menuitems.map {
              DomainDescriptionValue(
                it.description,
                it.value
              )
            },
            rankItems = god.abilityDetails2.description.itemDescription.rankitems.map {
              DomainDescriptionValue(
                it.description,
                it.value
              )
            },
          ),
          summary = god.abilityDetails2.summary,
          url = god.abilityDetails2.url
        ),
        abilityDetails3 = Ability(
          id = god.abilityDetails3.id,
          description = AbilityDescription(
            cooldown = god.abilityDetails3.description.itemDescription.cooldown,
            cost = god.abilityDetails3.description.itemDescription.cost,
            description = god.abilityDetails3.description.itemDescription.description,
            menuItems = god.abilityDetails3.description.itemDescription.menuitems.map {
              DomainDescriptionValue(
                it.description,
                it.value
              )
            },
            rankItems = god.abilityDetails3.description.itemDescription.rankitems.map {
              DomainDescriptionValue(
                it.description,
                it.value
              )
            },
          ),
          summary = god.abilityDetails3.summary,
          url = god.abilityDetails3.url
        ),
        abilityDetails4 = Ability(
          id = god.abilityDetails4.id,
          description = AbilityDescription(
            cooldown = god.abilityDetails4.description.itemDescription.cooldown,
            cost = god.abilityDetails4.description.itemDescription.cost,
            description = god.abilityDetails4.description.itemDescription.description,
            menuItems = god.abilityDetails4.description.itemDescription.menuitems.map {
              DomainDescriptionValue(
                it.description,
                it.value
              )
            },
            rankItems = god.abilityDetails4.description.itemDescription.rankitems.map {
              DomainDescriptionValue(
                it.description,
                it.value
              )
            },
          ),
          summary = god.abilityDetails4.summary,
          url = god.abilityDetails4.url
        ),
        abilityDetails5 = Ability(
          id = god.abilityDetails5.id,
          description = AbilityDescription(
            cooldown = god.abilityDetails5.description.itemDescription.cooldown,
            cost = god.abilityDetails5.description.itemDescription.cost,
            description = god.abilityDetails5.description.itemDescription.description,
            menuItems = god.abilityDetails5.description.itemDescription.menuitems.map {
              DomainDescriptionValue(
                it.description,
                it.value
              )
            },
            rankItems = god.abilityDetails5.description.itemDescription.rankitems.map {
              DomainDescriptionValue(
                it.description,
                it.value
              )
            },
          ),
          summary = god.abilityDetails5.summary,
          url = god.abilityDetails5.url
        ),
        attackSpeed = god.attackSpeed,
        attackSpeedPerLevel = god.attackSpeedPerLevel,
        autoBanned = god.autoBanned == "y",
        cons = god.cons,
        hp5PerLevel = god.hp5PerLevel,
        health = god.health,
        healthPerFive = god.healthPerFive,
        healthPerLevel = god.healthPerLevel,
        lore = god.lore,
        mp5PerLevel = god.mp5PerLevel,
        magicProtection = god.magicProtection,
        magicProtectionPerLevel = god.magicProtectionPerLevel,
        magicalPower = god.magicalPower,
        magicalPowerPerLevel = god.magicalPowerPerLevel,
        mana = god.mana,
        manaPerFive = god.manaPerFive,
        manaPerLevel = god.manaPerLevel,
        name = god.name,
        onFreeRotation = god.onFreeRotation == "true",
        pantheon = god.pantheon,
        physicalPower = god.physicalPower,
        physicalPowerPerLevel = god.physicalPowerPerLevel,
        physicalProtection = god.physicalProtection,
        physicalProtectionPerLevel = god.physicalProtectionPerLevel,
        pros = god.pros,
        roles = god.roles,
        speed = god.speed,
        title = god.title,
        type = god.type,
        basicAttack = AbilityDescription(
          cooldown = god.basicAttack.itemDescription.cooldown,
          cost = god.basicAttack.itemDescription.cost,
          description = god.basicAttack.itemDescription.description,
          menuItems = god.basicAttack.itemDescription.menuitems.map {
            DomainDescriptionValue(
              it.description,
              it.value
            )
          },
          rankItems = god.basicAttack.itemDescription.rankitems.map {
            DomainDescriptionValue(
              it.description,
              it.value
            )
          },
        ),
        godCardURL = god.godCardURL,
        godIconURL = god.godIconURL,
        latestGod = god.latestGod == "y",
      )
  }
  //endregion
}

actual data class AbilityDescription actual constructor(
  actual val cooldown: String,
  actual val cost: String,
  actual val description: String,
  actual val menuItems: List<DescriptionValue>,
  actual val rankItems: List<DescriptionValue>
)

actual data class Ability actual constructor(
  actual val id: Long,
  @Embedded
  actual val description: AbilityDescription,
  actual val summary: String,
  actual val url: String
)

