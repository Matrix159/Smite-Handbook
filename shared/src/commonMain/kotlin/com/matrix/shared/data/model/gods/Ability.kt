package com.matrix.shared.data.model.gods

import kotlinx.serialization.Serializable


@Serializable
data class Ability(
  val id: Long,
  val description: AbilityDescription,
  val summary: String,
  val url: String
)