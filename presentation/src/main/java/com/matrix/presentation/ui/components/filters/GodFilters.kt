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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.matrix.presentation.R
import com.matrix.presentation.models.filters.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.dp

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
        selected = appliedFilters.roles.contains(Role.ASSASSIN),
        onClick = {
          filtersChanged(
            appliedFilters.copy(
              roles = if (appliedFilters.roles.contains(Role.ASSASSIN)) appliedFilters.roles.minus(
                Role.ASSASSIN
              ) else appliedFilters.roles.plus(Role.ASSASSIN)
            )
          )
        },
        leadingIcon = getLeadingIcon(
          appliedFilters.roles.contains(Role.ASSASSIN),
          R.drawable.assassin,
          stringResource(R.string.assassin)
        ),
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text(stringResource(R.string.assassin), style = localTextStyleOverride)
      }
//      FilterChip(
//        selected = appliedFilters.role == Role.GUARDIAN,
//        onClick = {
//          filtersChanged(
//            appliedFilters.copy(role = if (appliedFilters.role != Role.GUARDIAN) Role.GUARDIAN else null)
//          )
//        },
//        leadingIcon = getLeadingIcon(
//          appliedFilters.role == Role.GUARDIAN,
//          R.drawable.guardian,
//          stringResource(R.string.guardian)
//        ),
//        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
//      ) {
//        Text(stringResource(R.string.guardian), style = localTextStyleOverride)
//      }
//      FilterChip(
//        selected = appliedFilters.role == Role.HUNTER,
//        onClick = {
//          filtersChanged(
//            appliedFilters.copy(role = if (appliedFilters.role != Role.HUNTER) Role.HUNTER else null)
//          )
//        },
//        leadingIcon = getLeadingIcon(
//          appliedFilters.role == Role.HUNTER,
//          R.drawable.hunter,
//          stringResource(R.string.hunter)
//        ),
//        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
//      ) {
//        Text(stringResource(R.string.hunter), style = localTextStyleOverride)
//      }
//      FilterChip(
//        selected = appliedFilters.role == Role.MAGE,
//        onClick = {
//          filtersChanged(
//            appliedFilters.copy(role = if (appliedFilters.role != Role.MAGE) Role.MAGE else null)
//          )
//        },
//        leadingIcon = getLeadingIcon(
//          appliedFilters.role == Role.MAGE,
//          R.drawable.mage,
//          stringResource(R.string.mage)
//        ),
//        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
//      ) {
//        Text(stringResource(R.string.mage), style = localTextStyleOverride)
//      }
//      FilterChip(
//        selected = appliedFilters.role == Role.WARRIOR,
//        onClick = {
//          filtersChanged(
//            appliedFilters.copy(role = if (appliedFilters.role != Role.WARRIOR) Role.WARRIOR else null)
//          )
//        },
//        leadingIcon = getLeadingIcon(
//          appliedFilters.role == Role.WARRIOR,
//          R.drawable.warrior,
//          stringResource(R.string.warrior)
//        ),
//        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
//      ) {
//        Text(stringResource(R.string.warrior), style = localTextStyleOverride)
//      }
//    }
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

/**
 * Function to switch out leadingIcon because if we don't, then the selected icon tries to "overlay" it
 * and looks terrible.
 */
private fun getLeadingIcon(
  condition: Boolean,
  resourceId: Int,
  contentDescription: String
): @Composable (() -> Unit)? =
  when (condition) {
    true -> null
    false -> {
      {
        Image(
          painterResource(resourceId),
          contentDescription,
          modifier = Modifier.size(24.dp)
        )
      }
    }
  }