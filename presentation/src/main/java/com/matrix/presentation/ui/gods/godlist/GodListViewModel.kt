package com.matrix.presentation.ui.gods.godlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.Result
import com.matrix.domain.models.asResult
import com.matrix.domain.usecases.GetLatestGodsUseCase
import com.matrix.presentation.models.filters.AppliedGodFilters
import com.matrix.presentation.models.filters.Pantheon
import com.matrix.presentation.models.filters.Role
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class GodListViewModel @Inject constructor(
  getLatestGodsUseCase: GetLatestGodsUseCase
) : ViewModel() {

  val godListUiState = godListUiStateStream(getLatestGodsUseCase).stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5000),
    initialValue = GodListUiState.Loading
  )

  var filters by mutableStateOf(
    AppliedGodFilters()
  )
    private set

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

  private fun godListUiStateStream(
    getLatestGodsUseCase: GetLatestGodsUseCase,
  ): Flow<GodListUiState> {

    return combine(
      getLatestGodsUseCase().asResult(),
      snapshotFlow { filters }
    ) { result: Result<List<GodInformation>>, filters: AppliedGodFilters ->
      when (result) {
        is Result.Success -> {
          when (result.data.isEmpty()) {
            true -> GodListUiState.Loading
            false -> GodListUiState.Success(
              result.data.filter { god ->
                filterGod(filters, god)
              }.sortedBy { it.name }
            )
          }
        }
        is Result.Loading -> {
          GodListUiState.Loading
        }
        is Result.Error -> {
          GodListUiState.Error(result.exception)
        }
      }
    }
  }
}

// Represents different states for the Builds screen
sealed interface GodListUiState {
  data class Success(val gods: List<GodInformation>) : GodListUiState
  data class Error(val exception: Throwable?) : GodListUiState
  object Loading : GodListUiState
}