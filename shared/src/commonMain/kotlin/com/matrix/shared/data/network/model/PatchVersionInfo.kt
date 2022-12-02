package com.matrix.shared.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PatchVersionInfo(
  @SerialName("version_string")
  val version: String
)