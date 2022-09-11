package com.matrix.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PatchVersionInfo(
  @SerialName("version_string")
  val version: String
)