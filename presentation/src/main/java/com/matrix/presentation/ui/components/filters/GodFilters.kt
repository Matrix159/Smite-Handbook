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
import com.matrix.presentation.models.filters.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GodFilters(
  appliedFilters: AppliedGodFilters,
  filtersChanged: (filters: AppliedGodFilters) -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(modifier = modifier.verticalScroll(rememberScrollState())) {
    val localTextStyleOverride = MaterialTheme.typography.bodySmall
    FilterGroup(title = "Role") {
      FilterChip(
        selected = appliedFilters.role == Role.WARRIOR,
        onClick = {
          filtersChanged(
            appliedFilters.copy(role = if (appliedFilters.role != Role.WARRIOR) Role.WARRIOR else null)
          )
        },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Warrior", style = localTextStyleOverride)
      }
    }
    FilterGroup(title = "Pantheon") {
//      FilterChip(
//        selected = appliedFilters.tier == ItemTier.One,
//        onClick = {
//          filtersChanged(
//            appliedFilters.copy(tier = if (appliedFilters.tier != ItemTier.One) ItemTier.One else null)
//          )
//        },
//        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
//      ) {
//        Text("Tier 1")
//      }
    }
  }
}