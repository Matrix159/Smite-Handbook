package com.matrix.shared.data.local

import com.matrix.shared.KmmAppContext
import com.matrix.shared.data.local.interfaces.PatchVersionDataSource

expect class PatchVersionDataSourceImpl() : PatchVersionDataSource {
  val kmmAppContext: KmmAppContext
}