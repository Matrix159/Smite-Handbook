package com.matrix.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemApiResult(
  @SerialName("ActiveFlag")
  val activeFlag: String,

  @SerialName("ChildItemId")
  val childItemID: Int,

  @SerialName("DeviceName")
  val deviceName: String,

  /**
   * "y" or "n"
   */
  @SerialName("Glyph")
  val glyph: String,

  @SerialName("IconId")
  val iconID: Int,

  @SerialName("ItemDescription")
  val itemDescription: ItemDescription,

  @SerialName("ItemId")
  val itemID: Int,

  @SerialName("ItemTier")
  val itemTier: Int,

  @SerialName("Price")
  val price: Int,

  @SerialName("RestrictedRoles")
  val restrictedRoles: String,

  @SerialName("RootItemId")
  val rootItemID: Int,

  @SerialName("ShortDesc")
  val shortDesc: String,

  @SerialName("StartingItem")
  val startingItem: Boolean,

  @SerialName("Type")
  val type: String,

  @SerialName("itemIcon_URL")
  val itemIconURL: String,

  @SerialName("ret_msg")
  val retMsg: String? = null
)

@Serializable
data class ItemDescription(
  @SerialName("Description")
  val description: String? = null,

  @SerialName("Menuitems")
  val menuItems: List<UpperDescriptionValue>,

  @SerialName("SecondaryDescription")
  val secondaryDescription: String? = null
)

@Serializable
data class UpperDescriptionValue(
  @SerialName("Description")
  val description: String,
  @SerialName("Value")
  val value: String
)