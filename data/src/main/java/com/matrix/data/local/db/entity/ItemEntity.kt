package com.matrix.data.local.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.matrix.data.local.db.model.DescriptionValue
import com.matrix.data.network.model.ItemApiResult
import com.matrix.domain.models.ItemInformation
import com.matrix.domain.models.DescriptionValue as DomainDescriptionValue
import com.matrix.domain.models.ItemDescription as DomainItemDescription

@Entity(tableName = "items")
data class ItemEntity(
  @PrimaryKey
  val id: Int,
  val patchVersion: String? = null,
  val activeFlag: Boolean,
  val childItemID: Int,
  val deviceName: String,
  val glyph: Boolean,
  val iconID: Int,
  @Embedded
  val itemDescription: ItemDescription,
  val itemTier: Int,
  val price: Int,
  val restrictedRoles: String,
  val rootItemID: Int,
  val shortDesc: String,
  val startingItem: Boolean,
  val type: String,
  val itemIconURL: String,
) {
  fun toDomain(): ItemInformation = ItemInformation(
    itemID = this.id,
    activeFlag = this.activeFlag,
    childItemID = this.childItemID,
    deviceName = this.deviceName,
    glyph = this.glyph,
    iconID = this.iconID,
    itemDescription = DomainItemDescription(
      description = this.itemDescription.description,
      menuItems = this.itemDescription.menuItems.map {
        DomainDescriptionValue(
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

  companion object {
    fun fromApi(itemApiResult: ItemApiResult, patchVersion: String?): ItemEntity = ItemEntity(
      id = itemApiResult.itemID,
      patchVersion = patchVersion,
      activeFlag = itemApiResult.activeFlag == "y",
      childItemID = itemApiResult.childItemID,
      deviceName = itemApiResult.deviceName,
      glyph = itemApiResult.glyph == "y",
      iconID = itemApiResult.iconID,
      itemDescription = ItemDescription(
        description = itemApiResult.itemDescription.description,
        menuItems = itemApiResult.itemDescription.menuItems.map {
          DescriptionValue(
            description = it.description,
            value = it.value
          )
        }
      ),
      itemTier = itemApiResult.itemTier,
      price = itemApiResult.price,
      restrictedRoles = itemApiResult.restrictedRoles,
      rootItemID = itemApiResult.rootItemID,
      shortDesc = itemApiResult.shortDesc,
      startingItem = itemApiResult.startingItem,
      type = itemApiResult.type,
      itemIconURL = itemApiResult.itemIconURL
    )
  }
}

data class ItemDescription(
  val description: String? = null,
  val menuItems: List<DescriptionValue>,
  val secondaryDescription: String? = null
)
