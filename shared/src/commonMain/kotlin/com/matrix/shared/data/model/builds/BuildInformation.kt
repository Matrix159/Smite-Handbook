package com.matrix.shared.data.model.builds

import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.model.items.ItemInformation

data class BuildInformation(
  val id: Long? = null,
  val god: GodInformation,
  val items: List<ItemInformation>,
  val name: String? = null
)
