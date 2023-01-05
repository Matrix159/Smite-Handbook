package com.matrix.shared.testing.builder

import com.matrix.shared.data.network.model.GodSkinApiResult

internal fun getMockGodSkinApiResult(godId: Int, skinName: String) = GodSkinApiResult(
  godIconURL = "godIconURL test",
  godSkinURL = "godSkinURL test",
  godID = godId,
  godName = "godName test",
  obtainability = "obtainability test",
  priceFavor = 1,
  priceGems = 1,
  retMsg = "retMsg test",
  skinId1 = 1,
  skinId2 = 1,
  skinName = skinName
)