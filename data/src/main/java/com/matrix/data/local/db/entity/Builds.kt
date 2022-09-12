package com.matrix.data.local.db.entity

import androidx.room.*
import com.matrix.domain.models.BuildInformation

@Entity(tableName = "builds")
data class BuildEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long? = null,
  val godId: Int
)

@Entity(primaryKeys = ["buildId", "itemId"], indices = [Index(value = ["itemId"])])
data class BuildItemCrossRef(
  val buildId: Long,
  val itemId: Int
)

data class BuildDbResult(
  @Embedded val build: BuildEntity,
  @Relation(
    parentColumn = "godId",
    entityColumn = "id"
  )
  val god: GodEntity,
  @Relation(
    parentColumn = "id",
    entityColumn = "id",
    associateBy = Junction(
      BuildItemCrossRef::class,
      parentColumn = "buildId",
      entityColumn = "itemId"
    )
  )
  val items: List<ItemEntity>
) {
  fun toDomain(): BuildInformation = BuildInformation(
    id = build.id,
    god = god.toDomain(),
    items = items.map { it.toDomain() }
  )
}
