package com.matrix159.shared.data.models

data class BuildInformation(
  val god: GodInformation,
  val items: List<ItemInformation>,
  val id: Int? = null,
  val name: String? = null
)
