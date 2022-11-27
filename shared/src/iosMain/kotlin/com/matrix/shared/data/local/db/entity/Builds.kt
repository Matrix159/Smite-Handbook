package com.matrix.shared.data.local.db.entity

import com.matrix.shared.data.models.BuildInformation

actual data class BuildEntity actual constructor(
  actual val id: Int?,
  actual val name: String?,
  actual val godId: Int,
)

actual data class BuildItemCrossRef actual constructor(
  actual val buildId: Int,
  actual val itemId: Int,
)

actual data class BuildDbResult actual constructor(
  actual val build: BuildEntity,
  actual val god: GodEntity,
  actual val items: List<ItemEntity>,
) {
  actual fun toDomain(): BuildInformation = BuildInformation(
    id = build.id,
    name = build.name,
    god = god.toDomain(),
    items = items.map { it.toDomain() },
  )
}