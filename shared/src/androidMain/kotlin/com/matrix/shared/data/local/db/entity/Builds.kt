package com.matrix.shared.data.local.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.matrix.shared.data.model.builds.BuildInformation

@Entity(tableName = "builds")
actual data class BuildEntity actual constructor(
  @PrimaryKey(autoGenerate = true)
  actual val id: Int?,
  actual val name: String?,
  actual val godId: Int
)

@Entity(
  primaryKeys = ["buildId", "itemId"],
  foreignKeys = [
    ForeignKey(
      entity = BuildEntity::class,
      parentColumns = arrayOf("id"),
      childColumns = arrayOf("buildId"),
      onDelete = ForeignKey.CASCADE
    )
  ],
  indices = [Index(value = ["itemId"])]
)
actual data class BuildItemCrossRef actual constructor(
  actual val buildId: Int,
  actual val itemId: Int
)

actual data class BuildDbResult actual constructor(
  @Embedded actual val build: BuildEntity,
  @Relation(
    parentColumn = "godId",
    entityColumn = "id"
  )
  actual val god: GodEntity,
  @Relation(
    parentColumn = "id",
    entityColumn = "id",
    associateBy = Junction(
      BuildItemCrossRef::class,
      parentColumn = "buildId",
      entityColumn = "itemId"
    )
  )
  actual val items: List<ItemEntity>
) {
  actual fun toDomain(): BuildInformation = BuildInformation(
    id = build.id,
    name = build.name,
    god = god.toDomain(),
    items = items.map { it.toDomain() },
  )
}
