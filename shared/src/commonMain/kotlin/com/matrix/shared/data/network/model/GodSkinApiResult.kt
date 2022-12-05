package com.matrix.shared.data.network.model

import com.matrix.shared.data.model.skins.GodSkinInformation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GodSkinApiResult(
  @SerialName("godIcon_URL")
  val godIconURL: String,

  @SerialName("godSkin_URL")
  val godSkinURL: String,

  @SerialName("god_id")
  val godID: Int,

  @SerialName("god_name")
  val godName: String,

  val obtainability: String,

  @SerialName("price_favor")
  val priceFavor: Int,

  @SerialName("price_gems")
  val priceGems: Int,

  @SerialName("ret_msg")
  val retMsg: String?,

  @SerialName("skin_id1")
  val skinId1: Int,

  @SerialName("skin_id2")
  val skinId2: Int,

  @SerialName("skin_name")
  val skinName: String
) {
  fun toDomain(): GodSkinInformation = GodSkinInformation(
    godIconURL = this.godIconURL,
    godSkinURL = this.godSkinURL,
    godID = this.godID,
    godName = this.godName,
    obtainability = this.obtainability,
    priceFavor = this.priceFavor,
    priceGems = this.priceGems,
    skinId1 = this.skinId1,
    skinId2 = this.skinId2,
    skinName = this.skinName
  )
}