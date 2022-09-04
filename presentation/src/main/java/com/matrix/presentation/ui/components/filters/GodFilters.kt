package com.matrix.presentation.ui.components.filters

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import com.matrix.presentation.R
import com.matrix.presentation.models.filters.AppliedGodFilters
import com.matrix.presentation.models.filters.Pantheon
import com.matrix.presentation.models.filters.Role

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
            appliedFiltersForRole(appliedFilters, Role.ASSASSIN)
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
      FilterChip(
        selected = appliedFilters.roles.contains(Role.GUARDIAN),
        onClick = {
          filtersChanged(
            appliedFiltersForRole(appliedFilters, Role.GUARDIAN)
          )
        },
        leadingIcon = getLeadingIcon(
          appliedFilters.roles.contains(Role.GUARDIAN),
          R.drawable.guardian,
          stringResource(R.string.guardian)
        ),
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text(stringResource(R.string.guardian), style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedFilters.roles.contains(Role.HUNTER),
        onClick = {
          filtersChanged(
            appliedFiltersForRole(appliedFilters, Role.HUNTER)
          )
        },
        leadingIcon = getLeadingIcon(
          appliedFilters.roles.contains(Role.HUNTER),
          R.drawable.hunter,
          stringResource(R.string.hunter)
        ),
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text(stringResource(R.string.hunter), style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedFilters.roles.contains(Role.MAGE),
        onClick = {
          filtersChanged(
            appliedFiltersForRole(appliedFilters, Role.MAGE)
          )
        },
        leadingIcon = getLeadingIcon(
          appliedFilters.roles.contains(Role.MAGE),
          R.drawable.mage,
          stringResource(R.string.mage)
        ),
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text(stringResource(R.string.mage), style = localTextStyleOverride)
      }
      FilterChip(
        selected = appliedFilters.roles.contains(Role.WARRIOR),
        onClick = {
          filtersChanged(
            appliedFiltersForRole(appliedFilters, Role.WARRIOR)
          )
        },
        leadingIcon = getLeadingIcon(
          appliedFilters.roles.contains(Role.WARRIOR),
          R.drawable.warrior,
          stringResource(R.string.warrior)
        ),
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text(stringResource(R.string.warrior), style = localTextStyleOverride)
      }
    }
    FilterGroup(title = "Pantheon") {
      FilterChip(
        selected = appliedFilters.pantheons.contains(Pantheon.ARTHURIAN),
        onClick = {
          filtersChanged(
            appliedFiltersForPantheon(appliedFilters, Pantheon.ARTHURIAN)
          )
        },

        leadingIcon = getLeadingIcon(
          appliedFilters.pantheons.contains(Pantheon.ARTHURIAN),
          R.drawable.arthurian,
          stringResource(R.string.arthurian)
        ),
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text(stringResource(R.string.arthurian), style = localTextStyleOverride)
      }
    }
  }
}

/**
 * Gets the new AppliedFilter object based on the given role
 */
private fun appliedFiltersForRole(appliedFilters: AppliedGodFilters, role: Role): AppliedGodFilters {
  return appliedFilters.copy(
    roles = if (appliedFilters.roles.contains(role)) appliedFilters.roles.minus(
      role
    ) else appliedFilters.roles.plus(role)
  )
}

/**
 * Gets the new AppliedFilter object based on the given pantheon
 */
private fun appliedFiltersForPantheon(appliedFilters: AppliedGodFilters, pantheon: Pantheon): AppliedGodFilters {
  return appliedFilters.copy(
    pantheons = if (appliedFilters.pantheons.contains(pantheon)) appliedFilters.pantheons.minus(
      pantheon
    ) else appliedFilters.pantheons.plus(pantheon)
  )
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