package com.matrix.presentation.viewmodels

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkin
import com.matrix.domain.usecases.GetGodSkinsUseCase
import com.matrix.domain.usecases.GetLatestGodsUseCase
import com.matrix.presentation.models.LoadingState
import com.matrix.presentation.models.filters.AppliedGodFilters
import com.matrix.presentation.models.filters.AppliedItemFilters
import com.matrix.presentation.models.filters.Role
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

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
              godSkins = listOf()
            )
            var godSkins: List<GodSkin>
            // Load the skins asynchronously
            withContext(Dispatchers.IO) {
              godSkins = getGodSkinsUseCase(godInformation.id)
              godDetailsUiState =
                godDetailsUiState.copy(godSkins = godSkins)
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
      (if (filters.role != null) filters.role.roleName == god.roles else true) &&
      // Pantheon
      (if (filters.pantheon != null) filters.pantheon.pantheonName == god.pantheon else true)

//      // Type
//      (if (filters.type != null)
//        filters.type == ItemType.Consumable && item.type == "Consumable" ||
//          filters.type == ItemType.Item && item.type == "Item" ||
//          filters.type == ItemType.Active && item.type == "Active"
//      else true) &&
//      // Tier
//      (if (filters.tier != null)
//        filters.tier == ItemTier.One && item.itemTier == 1 ||
//          filters.tier == ItemTier.Two && item.itemTier == 2 ||
//          filters.tier == ItemTier.Three && item.itemTier == 3 ||
//          filters.tier == ItemTier.Four && item.itemTier == 4
//      else true) &&
//      // Offense
//      (if (filters.magicalPower)
//        item.itemDescription.menuItems.any { it.description == "Magical Power" }
//      else true) &&
//      (if (filters.magicalLifeSteal)
//        item.itemDescription.menuItems.any { it.description == "Magical Lifesteal" }
//      else true) &&
//      (if (filters.magicalFlatPen)
//        item.itemDescription.menuItems.any {
//          it.description == "Magical Penetration" && !it.value.contains(
//            "%"
//          )
//        }
//      else true) &&
//      (if (filters.magicalPercentPen)
//        item.itemDescription.menuItems.any {
//          it.description == "Magical Penetration" && it.value.contains(
//            "%"
//          )
//        }
//      else true) &&
//      (if (filters.physicalPower)
//        item.itemDescription.menuItems.any { it.description == "Physical Power" }
//      else true) &&
//      (if (filters.physicalLifeSteal)
//        item.itemDescription.menuItems.any { it.description == "Physical Lifesteal" }
//      else true) &&
//      (if (filters.physicalFlatPen)
//        item.itemDescription.menuItems.any {
//          it.description == "Physical Penetration" && !it.value.contains(
//            "%"
//          )
//        }
//      else true) &&
//      (if (filters.physicalPercentPen)
//        item.itemDescription.menuItems.any {
//          it.description == "Physical Penetration" && it.value.contains(
//            "%"
//          )
//        }
//      else true) &&
//      (if (filters.attackSpeed)
//        item.itemDescription.menuItems.any { it.description == "Attack Speed" }
//      else true) &&
//      (if (filters.critChance)
//        item.itemDescription.menuItems.any { it.description == "Critical Strike Chance" }
//      else true) &&
//      (if (filters.basicAttackDamage)
//        item.itemDescription.menuItems.any { it.description == "Basic Attack Damage" }
//      else true) &&
//      // Defense
//      (if (filters.magicalProtection)
//        item.itemDescription.menuItems.any { it.description == "Magical Protection" }
//      else true) &&
//      (if (filters.physicalProtection)
//        item.itemDescription.menuItems.any { it.description == "Physical Protection" }
//      else true) &&
//      (if (filters.health)
//        item.itemDescription.menuItems.any { it.description == "Health" }
//      else true) &&
//      (if (filters.hp5)
//        item.itemDescription.menuItems.any { it.description == "HP5" }
//      else true) &&
//      (if (filters.ccr)
//        item.itemDescription.menuItems.any { it.description == "Crowd Control Reduction" }
//      else true) &&
//      // Utility
//      (if (filters.cdr)
//        item.itemDescription.menuItems.any { it.description == "Cooldown Reduction" }
//      else true) &&
//      (if (filters.mana)
//        item.itemDescription.menuItems.any { it.description == "Mana" }
//      else true) &&
//      (if (filters.mp5)
//        item.itemDescription.menuItems.any { it.description == "MP5" }
//      else true) &&
//      (if (filters.movementSpeed)
//        item.itemDescription.menuItems.any { it.description == "Movement Speed" }
//      else true)
  }
}