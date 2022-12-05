package com.matrix.shared.data.builder

import com.matrix.shared.data.network.model.ItemApiResult
import com.matrix.shared.data.network.model.ItemDescription
import com.matrix.shared.data.network.model.UpperDescriptionValue

internal fun getMockItemApiResult(id: Long) = ItemApiResult(
  itemID = id,
  activeFlag = "y",
  childItemID = 1,
  deviceName = "deviceName test",
  glyph = "y",
  iconID = 1,
  itemDescription = getMockItemDescription(),
  itemTier = 1,
  price = 1,
  restrictedRoles = "restrictedRoles test",
  rootItemID = 1,
  shortDesc = "shortDesc test",
  startingItem = true,
  type = "item",
  itemIconURL = "itemIconURL test",
  retMsg = "retMsg test"
)

private fun getMockItemDescription() = ItemDescription(
  description = "description test",
  menuItems = listOf(getMockUpperDescriptionValue()),
  secondaryDescription = "secondaryDescription test"
)

private fun getMockUpperDescriptionValue() = UpperDescriptionValue(
  description = "description test",
  value = "value test"
)