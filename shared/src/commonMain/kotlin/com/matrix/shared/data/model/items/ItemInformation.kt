package com.matrix.shared.data.model.items

import com.matrix.shared.data.model.shared.DescriptionValue
import kotlinx.serialization.Serializable

data class ItemInformation(
  val itemID: Int,
  val activeFlag: Boolean,
  val childItemID: Int,
  val deviceName: String,
  val glyph: Boolean,
  val iconID: Int,
  val itemDescription: ItemDescription,
  val itemTier: Int,
  val price: Int,
  val restrictedRoles: String,
  val rootItemID: Int,
  val shortDesc: String,
  val startingItem: Boolean,
  val type: String,
  val itemIconURL: String,
)

@Serializable
data class ItemDescription(
  val description: String? = null,
  val menuItems: List<DescriptionValue>,
  val secondaryDescription: String? = null
)
