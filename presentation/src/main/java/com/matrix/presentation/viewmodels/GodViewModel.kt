package com.matrix.presentation.viewmodels

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.domain.contracts.SmiteRepository
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkin
import com.matrix.presentation.cache.Cache
import com.matrix.presentation.models.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import timber.log.Timber
import javax.inject.Inject

class GodListCache(private val sharedPreferences: SharedPreferences) :
  Cache<String, List<GodInformation>> {
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
  val loadingState: LoadingState = LoadingState.LOADING,
  val gods: List<GodInformation> = listOf(),
  val errors: List<String> = listOf(),
)

data class GodDetailsUiState(
  val selectedGod: GodInformation? = null,
  val godSkins: List<GodSkin> = listOf(),
  val errors: List<String> = listOf(),
)

const val GOD_LIST_CACHE_KEY = "god_list_cache"

@HiltViewModel
class GodViewModel @Inject constructor(
  private val smiteRepo: SmiteRepository,
) : ViewModel() {

  //private val godListCache = GodListCache(appContext.getSharedPreferences(GOD_LIST_CACHE_KEY, Context.MODE_PRIVATE))
  var godListUiState by mutableStateOf(GodListUiState())
    private set

  var godDetailsUiState by mutableStateOf(GodDetailsUiState())
    private set

  //private val _gods = MutableStateFlow<List<GodInformation>>(listOf())
  //val gods: StateFlow<List<GodInformation>> = _gods.asStateFlow()

  private val _selectedGod = MutableStateFlow<GodInformation?>(null)
  //val selectedGod: StateFlow<GodInformation?> = _selectedGod.asStateFlow()

  init {
    viewModelScope.launch {
      Timber.d("loadState")
      godListUiState = godListUiState.copy(loadingState = LoadingState.LOADING)
      try {
        var gods: List<GodInformation>
        withContext(Dispatchers.IO) {
          gods = smiteRepo.getGods()
        }

        godListUiState = godListUiState.copy(gods = gods, loadingState = LoadingState.DONE)
      } catch (ex: Exception) {
        godListUiState =
          godListUiState.copy(
            errors = listOf(ex.toString()),
            loadingState = LoadingState.ERROR
          )
        Timber.e(ex.toString())
      }

//            _selectedGod.onEach { godInformation ->
//
//            }.launchIn(this)
    }
  }

  suspend fun setGod(godInformation: GodInformation?) {
    try {
      when (godInformation) {
        null -> {
          godDetailsUiState = GodDetailsUiState()
        }
        else -> {
          godDetailsUiState = godDetailsUiState.copy(
            selectedGod = godInformation,
            godSkins = listOf()
          )
          var godSkins: List<GodSkin>
          // Load the skins asynchronously
          withContext(Dispatchers.IO) {
            godSkins = smiteRepo.getGodSkins(godInformation.id)
            godDetailsUiState =
              godDetailsUiState.copy(godSkins = godSkins)
          }
        }
      }
    } catch (ex: Exception) {
      godDetailsUiState = godDetailsUiState.copy(errors = listOf(ex.toString()))
      Timber.e(ex.toString())
    }
  }
}