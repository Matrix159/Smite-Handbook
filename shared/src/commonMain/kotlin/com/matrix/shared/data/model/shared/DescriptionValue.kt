package com.matrix.shared.data.model.shared

import kotlinx.serialization.Serializable

@Serializable
data class DescriptionValue(
  val description: String,
  val value: String
)
