package com.matrix.presentation.ui.components.filters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.matrix.presentation.R
import com.matrix.presentation.models.filters.AppliedItemFilters
import com.matrix.presentation.models.filters.ItemTier
import com.matrix.presentation.models.filters.ItemType

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemFilters(
  appliedItemFilters: AppliedItemFilters,
  filtersChanged: (filters: AppliedItemFilters) -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(modifier = modifier.verticalScroll(rememberScrollState())) {
    val localTextStyleOverride = MaterialTheme.typography.bodySmall
    FilterGroup(title = "Type") {
      FilterChip(
        selected = appliedItemFilters.type == ItemType.Consumable,
        onClick = {
          filtersChanged(
            appliedItemFilters.copy(type = if (appliedItemFilters.type != ItemType.Consumable) ItemType.Consumable else null)
          )
        },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Consumable", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedItemFilters.type == ItemType.Item,
        onClick = {
          filtersChanged(
            appliedItemFilters.copy(type = if (appliedItemFilters.type != ItemType.Item) ItemType.Item else null)
          )
        },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Item", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedItemFilters.type == ItemType.Active,
        onClick = {
          filtersChanged(
            appliedItemFilters.copy(type = if (appliedItemFilters.type != ItemType.Active) ItemType.Active else null)
          )
        },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Active", style = localTextStyleOverride)
      }
    }
    FilterGroup(title = "Tier") {
      FilterChip(
        selected = appliedItemFilters.tier == ItemTier.One,
        onClick = {
          filtersChanged(
            appliedItemFilters.copy(tier = if (appliedItemFilters.tier != ItemTier.One) ItemTier.One else null)
          )
        },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Tier 1", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedItemFilters.tier == ItemTier.Two,
        onClick = {
          filtersChanged(
            appliedItemFilters.copy(tier = if (appliedItemFilters.tier != ItemTier.Two) ItemTier.Two else null)
          )
        },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Tier 2", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedItemFilters.tier == ItemTier.Three,
        onClick = {
          filtersChanged(
            appliedItemFilters.copy(tier = if (appliedItemFilters.tier != ItemTier.Three) ItemTier.Three else null)
          )
        },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Tier 3", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedItemFilters.tier == ItemTier.Four,
        onClick = {
          filtersChanged(
            appliedItemFilters.copy(tier = if (appliedItemFilters.tier != ItemTier.Four) ItemTier.Four else null)
          )
        },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Tier 4", style = localTextStyleOverride)
      }
    }
    FilterGroup(title = "Offense") {
      FilterChip(
        selected = appliedItemFilters.magicalPower,
        onClick = { filtersChanged(appliedItemFilters.copy(magicalPower = !appliedItemFilters.magicalPower)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Magical Power", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedItemFilters.magicalLifeSteal,
        onClick = { filtersChanged(appliedItemFilters.copy(magicalLifeSteal = !appliedItemFilters.magicalLifeSteal)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Magical Lifesteal", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedItemFilters.magicalFlatPen,
        onClick = { filtersChanged(appliedItemFilters.copy(magicalFlatPen = !appliedItemFilters.magicalFlatPen)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Magical Flat Penetration", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedItemFilters.magicalPercentPen,
        onClick = { filtersChanged(appliedItemFilters.copy(magicalPercentPen = !appliedItemFilters.magicalPercentPen)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Magical Percent Penetration", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedItemFilters.physicalPower,
        onClick = { filtersChanged(appliedItemFilters.copy(physicalPower = !appliedItemFilters.physicalPower)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Physical Power", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedItemFilters.physicalLifeSteal,
        onClick = { filtersChanged(appliedItemFilters.copy(physicalLifeSteal = !appliedItemFilters.physicalLifeSteal)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Physical Lifesteal", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedItemFilters.physicalFlatPen,
        onClick = { filtersChanged(appliedItemFilters.copy(physicalFlatPen = !appliedItemFilters.physicalFlatPen)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Physical Flat Penetration", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedItemFilters.physicalPercentPen,
        onClick = { filtersChanged(appliedItemFilters.copy(physicalPercentPen = !appliedItemFilters.physicalPercentPen)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Physical Percent Penetration", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedItemFilters.attackSpeed,
        onClick = { filtersChanged(appliedItemFilters.copy(attackSpeed = !appliedItemFilters.attackSpeed)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Attack Speed", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedItemFilters.critChance,
        onClick = { filtersChanged(appliedItemFilters.copy(critChance = !appliedItemFilters.critChance)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Critical Strike Chance", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedItemFilters.basicAttackDamage,
        onClick = { filtersChanged(appliedItemFilters.copy(basicAttackDamage = !appliedItemFilters.basicAttackDamage)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Basic Attack Damage", style = localTextStyleOverride)
      }
    }
    FilterGroup(title = "Defense") {
      FilterChip(
        selected = appliedItemFilters.magicalProtection,
        onClick = { filtersChanged(appliedItemFilters.copy(magicalProtection = !appliedItemFilters.magicalProtection)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Magical Protection", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedItemFilters.physicalProtection,
        onClick = { filtersChanged(appliedItemFilters.copy(physicalProtection = !appliedItemFilters.physicalProtection)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Physical Protection", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedItemFilters.health,
        onClick = { filtersChanged(appliedItemFilters.copy(health = !appliedItemFilters.health)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Health", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedItemFilters.hp5,
        onClick = { filtersChanged(appliedItemFilters.copy(hp5 = !appliedItemFilters.hp5)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("HP5", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedItemFilters.ccr,
        onClick = { filtersChanged(appliedItemFilters.copy(ccr = !appliedItemFilters.ccr)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Crowd Control Reduction", style = localTextStyleOverride)
      }
    }
    FilterGroup(title = "Utility") {
      FilterChip(
        selected = appliedItemFilters.cdr,
        onClick = { filtersChanged(appliedItemFilters.copy(cdr = !appliedItemFilters.cdr)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Cooldown Reduction", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedItemFilters.mana,
        onClick = { filtersChanged(appliedItemFilters.copy(mana = !appliedItemFilters.mana)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Mana", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedItemFilters.mp5,
        onClick = { filtersChanged(appliedItemFilters.copy(mp5 = !appliedItemFilters.mp5)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("MP5", style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedItemFilters.movementSpeed,
        onClick = { filtersChanged(appliedItemFilters.copy(movementSpeed = !appliedItemFilters.movementSpeed)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Movement Speed", style = localTextStyleOverride)
      }
    }
  }
}