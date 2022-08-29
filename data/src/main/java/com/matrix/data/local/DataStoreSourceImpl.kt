package com.matrix.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.matrix.data.local.interfaces.DataStoreSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "smite-handbook-store")

class DataStoreSourceImpl @Inject constructor(@ApplicationContext val context: Context) :
  DataStoreSource {
  val PATCH_VERSION_KEY = stringPreferencesKey("PATCH_VERSION_KEY")

  override suspend fun setPatchVersion(patchVersion: String) {
//    context
//      .getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE).edit()
//      .putString(PATCH_VERSION_KEY, patchVersion)
//      .apply()

    context.dataStore.edit { store ->
      store[PATCH_VERSION_KEY] = patchVersion
    }

  }

  override fun getPatchVersion(): Flow<String?> {
//    return context
//      .getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
//      .getString(PATCH_VERSION_KEY, null)

    return context.dataStore.data.map { preferences ->
      // No type safety.
      preferences[PATCH_VERSION_KEY]
    }
  }
}