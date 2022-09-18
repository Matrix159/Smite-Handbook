package com.matrix.domain.models

data class BuildInformation(
  val god: GodInformation,
  val items: List<ItemInformation>,
  val id: Long? = null,
  val name: String? = null
)
