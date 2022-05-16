package com.matrix.api.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LowerDescriptionValue(
  val description: String,
  val value: String
)

@Parcelize
@Serializable
data class UpperDescriptionValue(
  @SerialName("Description")
  val description: String,
  @SerialName("Value")
  val value: String
) : Parcelable