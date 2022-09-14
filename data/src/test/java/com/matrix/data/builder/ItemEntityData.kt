package com.matrix.data.builder

import com.matrix.data.local.db.entity.ItemDescription
import com.matrix.data.local.db.entity.ItemEntity
import com.matrix.data.local.db.model.DescriptionValue

fun getMockItemEntity(id: Int) = ItemEntity(
  id = id,
  patchVersion = "patchVersion test",
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