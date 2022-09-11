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
import com.matrix.presentation.utils.getPantheonDrawableResourceId
import com.matrix.presentation.utils.getPantheonStringResourceId
import com.matrix.presentation.utils.getRoleDrawableResourceId
import com.matrix.presentation.utils.getRoleStringResourceId

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GodFilters(
  appliedFilters: AppliedGodFilters,
  filtersChanged: (filters: AppliedGodFilters) -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(modifier = modifier.verticalScroll(rememberScrollState())) {
    val localTextStyleOverride = MaterialTheme.typography.bodySmall
    FilterGroup(title = stringResource(R.string.role)) {
      for (role in Role.values()) {
        FilterChip(
          selected = appliedFilters.roles.contains(role),
          onClick = {
            filtersChanged(
              getNewAppliedFiltersForRole(appliedFilters, role)
            )
          },
          leadingIcon = getLeadingIcon(
            appliedFilters.roles.contains(role),
            getRoleDrawableResourceId(role.roleName),
            stringResource(getRoleStringResourceId(role.roleName))
          ),
          selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
        ) {
          Text(
            stringResource(getRoleStringResourceId(role.roleName)),
            style = localTextStyleOverride
          )
        }
      }

    }
    FilterGroup(title = stringResource(R.string.pantheon)) {
      for (pantheon in Pantheon.values()) {
        FilterChip(
          selected = appliedFilters.pantheons.contains(pantheon),
          onClick = {
            filtersChanged(
              getNewAppliedFiltersForPantheon(appliedFilters, pantheon)
            )
          },

          leadingIcon = getLeadingIcon(
            appliedFilters.pantheons.contains(pantheon),
            getPantheonDrawableResourceId(pantheon.pantheonName),
            stringResource(getPantheonStringResourceId(pantheon.pantheonName))
          ),
          selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
        ) {
          Text(
            stringResource(getPantheonStringResourceId(pantheon.pantheonName)),
            style = localTextStyleOverride
          )
        }
      }
    }
  }
}

/**
 * Gets the new [AppliedGodFilters] object based on the given role
 */
private fun getNewAppliedFiltersForRole(
  appliedFilters: AppliedGodFilters,
  role: Role
): AppliedGodFilters {
  return appliedFilters.copy(
    roles = if (appliedFilters.roles.contains(role)) appliedFilters.roles.minus(
      role
    ) else appliedFilters.roles.plus(role)
  )
}

/**
 * Gets the new [AppliedGodFilters] object based on the given pantheon
 */
private fun getNewAppliedFiltersForPantheon(
  appliedFilters: AppliedGodFilters,
  pantheon: Pantheon
): AppliedGodFilters {
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