package com.matrix.data.builder

import com.matrix.data.network.model.ItemApiResult
import com.matrix.data.network.model.ItemDescription
import com.matrix.data.network.model.UpperDescriptionValue

fun getMockItemApiResult(id: Int) = ItemApiResult(
  itemID = id,
  activeFlag = "activeFlag test",
  childItemID = 1,
  deviceName = "deviceName test",
  glyph = "glyph test",
  iconID = 1,
  itemDescription = getMockItemDescription(),
  itemTier = 1,
  price = 1,
  restrictedRoles = "restrictedRoles test",
  rootItemID = 1,
  shortDesc = "shortDesc test",
  startingItem = true,
  type = "type test",
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