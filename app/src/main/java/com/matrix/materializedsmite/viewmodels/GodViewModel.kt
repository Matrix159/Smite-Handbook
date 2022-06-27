package com.matrix.materializedsmite.viewmodels

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.api.models.GodInformation
import com.matrix.api.models.GodSkin
import com.matrix.materializedsmite.SmiteApplication
import com.matrix.materializedsmite.cache.Cache
import com.matrix.materializedsmite.repositories.smite.SmiteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import timber.log.Timber
import javax.inject.Inject

class GodListCache(private val sharedPreferences: SharedPreferences) : Cache<String, List<GodInformation>> {
  override suspend fun getAsync(key: String): List<GodInformation> {
    return withContext(Dispatchers.IO) {
      sharedPreferences.getString(key, null)?.let {
        Json.decodeFromString<List<GodInformation>>(it)
      } ?: listOf()
    }
  }

  override suspend fun setAsync(key: String, value: List<GodInformation>) {
    return withContext(Dispatchers.IO) {
      sharedPreferences.edit().let {
        it.putString(key, Json.encodeToString(value))
        it.apply()
      }
    }
  }
}

data class GodListUiState(
  val gods: List<GodInformation> = listOf()
)

data class GodDetailsUiState(
  val selectedGod: GodInformation? = null,
  val godSkins: List<GodSkin> = listOf()
)

const val GOD_LIST_CACHE_KEY = "god_list_cache"

@HiltViewModel
class GodViewModel @Inject constructor(
  private val smiteRepo: SmiteRepository,
) : ViewModel(), CanError {

  private val godListCache = GodListCache(SmiteApplication.instance.getSharedPreferences(GOD_LIST_CACHE_KEY, Context.MODE_PRIVATE))
  private val _godListUiState = mutableStateOf(GodListUiState())
  val godListUiState: State<GodListUiState> = _godListUiState

  private val _godDetailsUiState = mutableStateOf(GodDetailsUiState())
  val godDetailsUiState: State<GodDetailsUiState> = _godDetailsUiState

  //private val _gods = MutableStateFlow<List<GodInformation>>(listOf())
  //val gods: StateFlow<List<GodInformation>> = _gods.asStateFlow()

  private val _selectedGod = MutableStateFlow<GodInformation?>(null)
  //val selectedGod: StateFlow<GodInformation?> = _selectedGod.asStateFlow()

  init {
    viewModelScope.launch(Dispatchers.IO) {
      try {
        val godsApiResult = godListCache.getAsync(GOD_LIST_CACHE_KEY).ifEmpty {
          val newResults = smiteRepo.getGods()
          godListCache.setAsync(GOD_LIST_CACHE_KEY, newResults)
          newResults
        }
        _godListUiState.value = GodListUiState(gods = godsApiResult)
        error = null
      } catch (ex: Exception) {
        error = ex
        Timber.e(ex.toString())
      }

    }
    Timber.d("init")
    _selectedGod.onEach { godInformation ->
      try {
        when (godInformation) {
          null -> {
            _godDetailsUiState.value = GodDetailsUiState(selectedGod = null, godSkins = listOf())
          }
          else -> {
            _godDetailsUiState.value =
              GodDetailsUiState(selectedGod = godInformation, godSkins = listOf())
            var godSkins: List<GodSkin>
            withContext(Dispatchers.IO) {
              godSkins = smiteRepo.getGodSkins(godInformation.id)
              _godDetailsUiState.value = _godDetailsUiState.value.copy(godSkins = godSkins)
            }
          }
        }
        error = null
      } catch (ex: Exception) {
        error = ex
        Timber.e(ex.toString())
      }
    }.launchIn(viewModelScope)
  }

  fun setGod(godInformation: GodInformation) {
    _selectedGod.update { godInformation }
  }

  fun clearSelectedGod() {
    _selectedGod.update { null }
  }

  override var error: Exception? = null
}