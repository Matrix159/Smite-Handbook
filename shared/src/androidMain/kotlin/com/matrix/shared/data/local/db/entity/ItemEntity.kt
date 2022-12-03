//package com.matrix.shared.data.local.db.entity
//
//import androidx.room.Embedded
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import com.matrix.shared.data.model.items.ItemInformation
//import com.matrix.shared.data.network.model.ItemApiResult
//import com.matrix.shared.data.model.shared.DescriptionValue as DomainDescriptionValue
//import com.matrix.shared.data.model.items.ItemDescription as DomainItemDescription
//
//@Entity(tableName = "items")
//actual data class ItemEntity actual constructor(
//  @PrimaryKey
//  actual val id: Int,
//  actual val patchVersion: String?,
//  actual val activeFlag: Boolean,
//  actual val childItemID: Int,
//  actual val deviceName: String,
//  actual val glyph: Boolean,
//  actual val iconID: Int,
//  @Embedded
//  actual val itemDescription: ItemDescription,
//  actual val itemTier: Int,
//  actual val price: Int,
//  actual val restrictedRoles: String,
//  actual val rootItemID: Int,
//  actual val shortDesc: String,
//  actual val startingItem: Boolean,
//  actual val type: String,
//  actual val itemIconURL: String,
//) {
//  actual fun com.matrix.shared.data.model.toDomain(): ItemInformation = ItemInformation(
//    itemID = this.id,
//    activeFlag = this.activeFlag,
//    childItemID = this.childItemID,
//    deviceName = this.deviceName,
//    glyph = this.glyph,
//    iconID = this.iconID,
//    itemDescription = DomainItemDescription(
//      description = this.itemDescription.description,
//      menuItems = this.itemDescription.menuItems.map {
//        DomainDescriptionValue(
//          description = it.description,
//          value = it.value
//        )
//      }
//    ),
//    itemTier = this.itemTier,
//    price = this.price,
//    restrictedRoles = this.restrictedRoles,
//    rootItemID = this.rootItemID,
//    shortDesc = this.shortDesc,
//    startingItem = this.startingItem,
//    type = this.type,
//    itemIconURL = this.itemIconURL
//  )
//
//  actual companion object {
//    actual fun fromApi(itemApiResult: ItemApiResult, patchVersion: String?): ItemEntity = ItemEntity(
//      id = itemApiResult.itemID,
//      patchVersion = patchVersion,
//      activeFlag = itemApiResult.activeFlag == "y",
//      childItemID = itemApiResult.childItemID,
//      deviceName = itemApiResult.deviceName,
//      glyph = itemApiResult.glyph == "y",
//      iconID = itemApiResult.iconID,
//      itemDescription = ItemDescription(
//        description = itemApiResult.itemDescription.description,
//        menuItems = itemApiResult.itemDescription.menuItems.map {
//          DomainDescriptionValue(
//            description = it.description,
//            value = it.value
//          )
//        }
//      ),
//      itemTier = itemApiResult.itemTier,
//      price = itemApiResult.price,
//      restrictedRoles = itemApiResult.restrictedRoles,
//      rootItemID = itemApiResult.rootItemID,
//      shortDesc = itemApiResult.shortDesc,
//      startingItem = itemApiResult.startingItem,
//      type = itemApiResult.type,
//      itemIconURL = itemApiResult.itemIconURL
//    )
//  }
//}
//
//actual data class ItemDescription actual constructor(
//  actual val description: String?,
//  actual val menuItems: List<DomainDescriptionValue>,
//  actual val secondaryDescription: String?
//)
