package com.matrix.domain.models

data class BuildInformation(
  val god: GodInformation,
  val items: List<ItemInformation>,
  val id: Int? = null,
  val name: String? = null
)
