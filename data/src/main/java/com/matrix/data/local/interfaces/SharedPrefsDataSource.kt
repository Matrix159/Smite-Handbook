package com.matrix.data.local.interfaces

interface SharedPrefsDataSource {
  fun setPatchVersion(patchVersion: String)
  fun getPatchVersion(): String?
}