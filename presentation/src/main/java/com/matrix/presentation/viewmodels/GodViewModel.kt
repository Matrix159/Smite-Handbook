package com.matrix.presentation.viewmodels

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkinInformation
import com.matrix.domain.usecases.GetGodSkinsUseCase
import com.matrix.domain.usecases.GetLatestGodsUseCase
import com.matrix.presentation.models.LoadingState
import com.matrix.presentation.models.filters.AppliedGodFilters
import com.matrix.presentation.models.filters.Pantheon
import com.matrix.presentation.models.filters.Role
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

data class GodListUiState(
  val loadingState: LoadingState = LoadingState.LOADING,
  val gods: List<GodInformation> = listOf(),
  val errors: List<String> = listOf(),
)

data class GodDetailsUiState(
  val selectedGod: GodInformation? = null,
  val godSkinInformations: List<GodSkinInformation> = listOf(),
  val errors: List<String> = listOf(),
)

@HiltViewModel
class GodViewModel @Inject constructor(
  private val getLatestGodsUseCase: GetLatestGodsUseCase,
  private val getGodSkinsUseCase: GetGodSkinsUseCase
) : ViewModel() {

  var godListUiState by mutableStateOf(GodListUiState())
    private set

  var filters by mutableStateOf(
    AppliedGodFilters()
  )
    private set

  val visibleItems by derivedStateOf {
    godListUiState.gods.filter { god ->
      filterGod(filters, god)
    }.sortedBy { it.name }
  }

  var godDetailsUiState by mutableStateOf(GodDetailsUiState())
    private set

  init {
    viewModelScope.launch {
      Timber.d("loadState")
      godListUiState = godListUiState.copy(loadingState = LoadingState.LOADING)
      try {
        var gods: List<GodInformation>
        withContext(Dispatchers.IO) {
          gods = getLatestGodsUseCase()
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
    }
  }

  fun setGod(godInformation: GodInformation?) {
    viewModelScope.launch {
      try {
        when (godInformation) {
          null -> {
            godDetailsUiState = GodDetailsUiState()
          }
          else -> {
            godDetailsUiState = godDetailsUiState.copy(
              selectedGod = godInformation,
              godSkinInformations = listOf()
            )
            var godSkinInformations: List<GodSkinInformation>
            // Load the skins asynchronously
            withContext(Dispatchers.IO) {
              godSkinInformations = getGodSkinsUseCase(godInformation.id)
              godDetailsUiState =
                godDetailsUiState.copy(godSkinInformations = godSkinInformations)
            }
          }
        }
      } catch (ex: Exception) {
        godDetailsUiState = godDetailsUiState.copy(errors = listOf(ex.toString()))
        Timber.e(ex.toString())
        throw ex
      }
    }
  }

  fun updateAppliedFilters(newFilters: AppliedGodFilters) {
    filters = newFilters.copy()
  }

  fun updateSearchText(text: String) {
    filters = filters.copy(searchText = text)
  }

  private fun filterGod(filters: AppliedGodFilters, god: GodInformation): Boolean {
    return (if (filters.searchText.isNotBlank())
      god.name.contains(filters.searchText, true)
    else true) &&
      // Role
      (if (filters.roles.isNotEmpty()) filters.roles.contains(
        Role.values().first { it.roleName == god.roles }) else true) &&
      // Pantheon
      (if (filters.pantheons.isNotEmpty()) filters.pantheons.contains(
        Pantheon.values().first { it.pantheonName == god.pantheon }) else true)
  }
}