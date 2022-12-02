package com.matrix.shared.data.model.gods

import com.matrix.shared.data.model.shared.DescriptionValue

data class AbilityDescription(
  val cooldown: String,
  val cost: String,
  val description: String,
  val menuItems: List<DescriptionValue>,
  val rankItems: List<DescriptionValue>
)