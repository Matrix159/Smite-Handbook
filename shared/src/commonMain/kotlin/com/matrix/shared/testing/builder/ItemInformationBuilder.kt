package com.matrix.shared.testing.builder

import com.matrix.shared.data.model.items.ItemDescription
import com.matrix.shared.data.model.items.ItemInformation
import com.matrix.shared.data.model.shared.DescriptionValue

internal fun getMockItemInformation(id: Long) = ItemInformation(
  itemID = id,
  activeFlag = true,
  childItemID = 1,
  deviceName = "deviceName test",
  glyph = true,
  iconID = 1,
  itemDescription = getMockItemDescription(),
  itemTier = 1,
  price = 1,
  restrictedRoles = "restrictedRoles test",
  rootItemID = 1,
  shortDesc = "shortDesc test",
  startingItem = true,
  type = "type test",
  itemIconURL = "itemIconURL test"
)

private fun getMockItemDescription() = ItemDescription(
  description = "description test",
  menuItems = listOf(getMockDescriptionValue()),
  secondaryDescription = "secondaryDescription test"
)


private fun getMockDescriptionValue() = DescriptionValue(
  description = "description test",
  value = "value test"
)
