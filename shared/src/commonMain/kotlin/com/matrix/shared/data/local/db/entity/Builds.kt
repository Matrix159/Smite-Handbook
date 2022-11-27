package com.matrix.shared.data.local.db.entity

import com.matrix.shared.data.models.BuildInformation

expect class BuildEntity(
  id: Int? = null,
  name: String? = null,
  godId: Int
) {
  val id: Int?
  val name: String?
  val godId: Int
}

expect class BuildItemCrossRef(
  buildId: Int,
  itemId: Int
) {
  val buildId: Int
  val itemId: Int
}

expect class BuildDbResult(
  build: BuildEntity,
  god: GodEntity,
  items: List<ItemEntity>
) {
  val build: BuildEntity
  val god: GodEntity
  val items: List<ItemEntity>

  fun toDomain(): BuildInformation
}

