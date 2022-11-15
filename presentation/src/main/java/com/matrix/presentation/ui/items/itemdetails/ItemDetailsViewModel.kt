package com.matrix.presentation.ui.items.itemdetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.domain.models.ItemInformation
import com.matrix.domain.models.Result
import com.matrix.domain.models.asResult
import com.matrix.domain.usecases.GetItemUseCase
import com.matrix.domain.usecases.GetLatestItemsUseCase
import com.matrix.presentation.ui.items.navigation.ItemsNavigation
import com.matrix.presentation.utils.ItemNode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ItemDetailsViewModel @Inject constructor(
  getLatestItemsUseCase: GetLatestItemsUseCase,
  getItemUseCase: GetItemUseCase,
  savedStateHandle: SavedStateHandle,
) : ViewModel() {

  private val selectedItemId: Int =
    (checkNotNull(savedStateHandle[ItemsNavigation.ItemDetails.itemIdArg]) as String).toInt()

  val uiState: StateFlow<ItemDetailUiState> =
    combine(
      getItemUseCase(selectedItemId),
      snapshotFlow { baseNodeState },
      ::Pair
    )
      .asResult()
      .map { result ->
        when (result) {
          is Result.Success -> {
            ItemDetailUiState.Success(
              item = result.data.first,
              itemTreeNodes = result.data.second
            )
          }
          is Result.Loading -> {
            ItemDetailUiState.Loading
          }
          is Result.Error -> {
            ItemDetailUiState.Error(result.exception)
          }
        }
      }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ItemDetailUiState.Loading
      )

  private var baseNodeState by mutableStateOf<List<ItemNode>>(emptyList())

  init {
    getLatestItemsUseCase()
      .onEach {
        Timber.d("calculated base nodes")
        baseNodeState = createBaseNodes(it)
      }.launchIn(viewModelScope)
  }

  private fun createBaseNodes(itemInformationList: List<ItemInformation>): List<ItemNode> {
    // Set up the item tree's via a node structure
    val baseNodes = mutableListOf<ItemNode>()
    itemInformationList.filter { item -> item.itemTier == 1 }.forEach {
      baseNodes.add(ItemNode(it))
    }
    val itemsGroupedByTier = itemInformationList.groupBy { it.itemTier }
    baseNodes.forEach { node ->
      node.findChildren(itemsGroupedByTier)
    }
    return baseNodes.toList()
  }
}

sealed interface ItemDetailUiState {
  data class Success(
    val item: ItemInformation,
    val itemTreeNodes: List<ItemNode>
  ) : ItemDetailUiState

  data class Error(val exception: Throwable?) : ItemDetailUiState
  object Loading : ItemDetailUiState
}


