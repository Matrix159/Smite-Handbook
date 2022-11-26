package com.matrix.data.local.db.model

import kotlinx.serialization.Serializable

@Serializable
data class DescriptionValue(
  val description: String,
  val value: String
)