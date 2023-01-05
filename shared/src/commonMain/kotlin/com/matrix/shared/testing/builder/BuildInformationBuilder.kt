package com.matrix.shared.testing.builder

import com.matrix.shared.data.model.builds.BuildInformation

fun getMockBuildInformation(buildId: Long, godId: Long, itemIds: List<Long>) = BuildInformation(
  id = buildId,
  god = getMockGodInformation(godId),
  items = itemIds.map {
    getMockItemInformation(it)
  },
  name = "name test"
)