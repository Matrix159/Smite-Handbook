package com.matrix.shared.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.matrix.shared.KmmAppContext
import com.matrix.shared.data.local.interfaces.PatchVersionDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "smite-handbook-store")

actual class PatchVersionDataSourceImpl : PatchVersionDataSource {
  private val PATCH_VERSION_KEY = stringPreferencesKey("PATCH_VERSION_KEY")

  override suspend fun setPatchVersion(patchVersion: String) {
    kmmAppContext.androidContext.dataStore.edit { store ->
      store[PATCH_VERSION_KEY] = patchVersion
    }

  }

  override fun getPatchVersion(): Flow<String?> {
    return kmmAppContext.androidContext.dataStore.data.map { preferences ->
      preferences[PATCH_VERSION_KEY]
    }
  }

  actual val kmmAppContext: KmmAppContext = KmmAppContext.appContext
}