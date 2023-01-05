package com.matrix.shared.testing.builder

import com.matrix.shared.data.model.skins.GodSkinInformation

fun getMockGodSkinInformation(godId: Int, skinName: String) = GodSkinInformation(
  godID = godId,
  godIconURL = "godIconURL test",
  godSkinURL = "godSkinURL test",
  godName = "godName test",
  obtainability = "obtainability test",
  priceFavor = 1,
  priceGems = 1,
  skinId1 = 1,
  skinId2 = 1,
  skinName = skinName
)