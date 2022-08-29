package com.matrix.presentation.ui.itemdetails.filters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.matrix.presentation.R
import com.matrix.presentation.models.filters.AppliedFilters
import com.matrix.presentation.models.filters.ItemTier
import com.matrix.presentation.models.filters.ItemType


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemFilterModal(
  modifier: Modifier = Modifier,
  filters: AppliedFilters,
  filtersChanged: (filters: AppliedFilters) -> Unit,
  content: @Composable (bottomSheetState: ModalBottomSheetState) -> Unit,
) {
  val bottomSheetState: ModalBottomSheetState =
    rememberModalBottomSheetState(
      initialValue = ModalBottomSheetValue.Hidden,
      skipHalfExpanded = true
    )
  ModalBottomSheetLayout(
    sheetState = bottomSheetState,
    sheetBackgroundColor = MaterialTheme.colorScheme.surface,
    sheetContentColor = MaterialTheme.colorScheme.onSurface,
    modifier = modifier,
    sheetContent = {
      ItemFilters(
        appliedFilters = filters,
        filtersChanged = filtersChanged,
        modifier = Modifier.padding(16.dp),
      )
    }) {
    content(bottomSheetState)
  }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemFilters(
  modifier: Modifier = Modifier,
  appliedFilters: AppliedFilters,
  filtersChanged: (filters: AppliedFilters) -> Unit,
) {
  Column(modifier = modifier.verticalScroll(rememberScrollState())) {
    val localTextStyleOverride = MaterialTheme.typography.bodySmall
    FilterGroup(title = "Type") {
      FilterChip(
        selected = appliedFilters.type == ItemType.Consumable,
        onClick = {
          filtersChanged(
            appliedFilters.copy(type = if (appliedFilters.type != ItemType.Consumable) ItemType.Consumable else null)
          )
        },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Consumable")
      }
      FilterChip(
        selected = appliedFilters.type == ItemType.Item,
        onClick = {
          filtersChanged(
            appliedFilters.copy(type = if (appliedFilters.type != ItemType.Item) ItemType.Item else null)
          )
        },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Item")
      }
      FilterChip(
        selected = appliedFilters.type == ItemType.Active,
        onClick = {
          filtersChanged(
            appliedFilters.copy(type = if (appliedFilters.type != ItemType.Active) ItemType.Active else null)
          )
        },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Active")
      }
    }
    FilterGroup(title = "Tier") {
      FilterChip(
        selected = appliedFilters.tier == ItemTier.One,
        onClick = {
          filtersChanged(
            appliedFilters.copy(tier = if (appliedFilters.tier != ItemTier.One) ItemTier.One else null)
          )
        },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Tier 1")
      }
      FilterChip(
        selected = appliedFilters.tier == ItemTier.Two,
        onClick = {
          filtersChanged(
            appliedFilters.copy(tier = if (appliedFilters.tier != ItemTier.Two) ItemTier.Two else null)
          )
        },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Tier 2")
      }
      FilterChip(
        selected = appliedFilters.tier == ItemTier.Three,
        onClick = {
          filtersChanged(
            appliedFilters.copy(tier = if (appliedFilters.tier != ItemTier.Three) ItemTier.Three else null)
          )
        },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Tier 3")
      }
      FilterChip(
        selected = appliedFilters.tier == ItemTier.Four,
        onClick = {
          filtersChanged(
            appliedFilters.copy(tier = if (appliedFilters.tier != ItemTier.Four) ItemTier.Four else null)
          )
        },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Tier 4")
      }
    }
    FilterGroup(title = "Offense") {
      FilterChip(
        selected = appliedFilters.magicalPower,
        onClick = { filtersChanged(appliedFilters.copy(magicalPower = !appliedFilters.magicalPower)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Magical Power", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedFilters.magicalLifeSteal,
        onClick = { filtersChanged(appliedFilters.copy(magicalLifeSteal = !appliedFilters.magicalLifeSteal)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Magical Lifesteal", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedFilters.magicalFlatPen,
        onClick = { filtersChanged(appliedFilters.copy(magicalFlatPen = !appliedFilters.magicalFlatPen)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Magical Flat Penetration", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedFilters.magicalPercentPen,
        onClick = { filtersChanged(appliedFilters.copy(magicalPercentPen = !appliedFilters.magicalPercentPen)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Magical Percent Penetration", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedFilters.physicalPower,
        onClick = { filtersChanged(appliedFilters.copy(physicalPower = !appliedFilters.physicalPower)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Physical Power", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedFilters.physicalLifeSteal,
        onClick = { filtersChanged(appliedFilters.copy(physicalLifeSteal = !appliedFilters.physicalLifeSteal)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Physical Lifesteal", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedFilters.physicalFlatPen,
        onClick = { filtersChanged(appliedFilters.copy(physicalFlatPen = !appliedFilters.physicalFlatPen)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Physical Flat Penetration", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedFilters.physicalPercentPen,
        onClick = { filtersChanged(appliedFilters.copy(physicalPercentPen = !appliedFilters.physicalPercentPen)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Physical Percent Penetration", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedFilters.attackSpeed,
        onClick = { filtersChanged(appliedFilters.copy(attackSpeed = !appliedFilters.attackSpeed)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Attack Speed", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedFilters.critChance,
        onClick = { filtersChanged(appliedFilters.copy(critChance = !appliedFilters.critChance)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Critical Strike Chance", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedFilters.basicAttackDamage,
        onClick = { filtersChanged(appliedFilters.copy(basicAttackDamage = !appliedFilters.basicAttackDamage)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Basic Attack Damage", style = localTextStyleOverride)
      }
    }
    FilterGroup(title = "Defense") {
      FilterChip(
        selected = appliedFilters.magicalProtection,
        onClick = { filtersChanged(appliedFilters.copy(magicalProtection = !appliedFilters.magicalProtection)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Magical Protection", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedFilters.physicalProtection,
        onClick = { filtersChanged(appliedFilters.copy(physicalProtection = !appliedFilters.physicalProtection)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Physical Protection", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedFilters.health,
        onClick = { filtersChanged(appliedFilters.copy(health = !appliedFilters.health)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Health", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedFilters.hp5,
        onClick = { filtersChanged(appliedFilters.copy(hp5 = !appliedFilters.hp5)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("HP5", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedFilters.ccr,
        onClick = { filtersChanged(appliedFilters.copy(ccr = !appliedFilters.ccr)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Crowd Control Reduction", style = localTextStyleOverride)
      }
    }
    FilterGroup(title = "Utility") {
      FilterChip(
        selected = appliedFilters.cdr,
        onClick = { filtersChanged(appliedFilters.copy(cdr = !appliedFilters.cdr)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Cooldown Reduction", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedFilters.mana,
        onClick = { filtersChanged(appliedFilters.copy(mana = !appliedFilters.mana)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Mana", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedFilters.mp5,
        onClick = { filtersChanged(appliedFilters.copy(mp5 = !appliedFilters.mp5)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("MP5", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedFilters.movementSpeed,
        onClick = { filtersChanged(appliedFilters.copy(movementSpeed = !appliedFilters.movementSpeed)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Movement Speed", style = localTextStyleOverride)
      }
    }
  }
}

@Composable
fun FilterGroup(
  title: String,
  content: @Composable () -> Unit
) {
  Text(style = MaterialTheme.typography.titleMedium, text = title)
  Divider(color = MaterialTheme.colorScheme.onSurface)
  FlowRow(mainAxisSpacing = 8.dp) {
    content()
  }
}
