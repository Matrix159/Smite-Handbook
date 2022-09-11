package com.matrix.presentation.ui.components.filters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPanel(
  searchText: String,
  searchTextChanged: (searchText: String) -> Unit,
  searchLabel: String,
  modifier: Modifier = Modifier,
  filterIconTap: () -> Unit,
) {
  val focusManager = LocalFocusManager.current
  // Search field and filter action
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceAround,
    modifier = modifier
  ) {
    TextField(
      value = searchText,
      onValueChange = searchTextChanged,
      label = {
        Text(searchLabel)
      },
      singleLine = true,
      keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
      keyboardActions = KeyboardActions(onDone = {
        focusManager.clearFocus()
      }),
      modifier = Modifier.weight(1f)
    )
    val coroutineScope = rememberCoroutineScope()
    IconButton(
      onClick = {
        coroutineScope.launch {
          focusManager.clearFocus()
          filterIconTap()
        }
      },
      modifier = Modifier.padding(start = 16.dp)
    ) {
      Icon(
        Icons.Default.FilterList,
        "Filter list",
        modifier = Modifier.size(36.dp)
      )
    }
  }
}