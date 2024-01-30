package com.matrix.presentation.ui.items.itemdetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.presentation.ui.items.navigation.ItemsNavigation
import com.matrix.presentation.utils.ItemNode
import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.model.Result
import com.matrix.shared.data.model.asResult
import com.matrix.shared.data.model.items.ItemInformation
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber

//@HiltViewModel
class ItemDetailsViewModel /*@Inject*/ constructor(
  smiteRepository: SmiteRepository,
  savedStateHandle: SavedStateHandle,
) : ViewModel() {

  private val selectedItemId: Long =
    (checkNotNull(savedStateHandle[ItemsNavigation.ItemDetails.itemIdArg]) as String).toLong()

  val uiState: StateFlow<ItemDetailUiState> =
    combine(
      smiteRepository.getItem(selectedItemId),
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
            Timber.e(result.exception)
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
    smiteRepository.getItems()
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
    val itemsGroupedByTier = itemInformationList.groupBy { it.itemTier.toLong() }
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


