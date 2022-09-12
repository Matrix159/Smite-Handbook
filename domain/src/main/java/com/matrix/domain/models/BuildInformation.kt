package com.matrix.domain.models

data class BuildInformation(
  val id: Long? = null,
  val god: GodInformation,
  val items: List<ItemInformation>
)
