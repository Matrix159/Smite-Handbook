package com.matrix.data.local

import android.content.Context
import com.matrix.data.local.interfaces.SharedPrefsDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPrefsDataSourceImpl @Inject constructor(@ApplicationContext val context: Context) :
  SharedPrefsDataSource {
  val SHARED_PREFS_NAME = "smite-handbook-shared-prefs"
  val PATCH_VERSION_KEY = "patch-version"

  override fun setPatchVersion(patchVersion: String) {
    context
      .getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE).edit()
      .putString(PATCH_VERSION_KEY, patchVersion)
      .apply()
  }

  override fun getPatchVersion(): String? {
    return context
      .getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
      .getString(PATCH_VERSION_KEY, null)
  }
}