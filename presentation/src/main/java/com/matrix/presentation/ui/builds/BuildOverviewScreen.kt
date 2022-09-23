package com.matrix.presentation.ui.builds

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matrix.presentation.ui.components.Loader
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun BuildOverviewScreen(
  viewModel: BuildViewModel,
  createBuild: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = modifier
  ) {
    val buildState: BuildsUiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = buildState) {
      is BuildsUiState.Error -> {
        Text(state.exception.toString())
      }
      is BuildsUiState.Loading -> {
        Timber.d(buildState.toString())
        Loader()
      }
      is BuildsUiState.Success -> {
        val coroutineScope = rememberCoroutineScope()
        Scaffold(
          floatingActionButton = {
            FloatingActionButton(
              onClick = {
                // TODO: TESTING
                createBuild()
                coroutineScope.launch {
                  viewModel.createBuild()
                }
              },
            ) {
              Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
          },
          modifier = Modifier.fillMaxSize()
        ) {
          LazyColumn {
            items(state.builds) { build ->
              Column {
                BuildOverviewCard(
                  buildInformation = build,
                  modifier = Modifier.fillMaxWidth(),
                  onDelete = { coroutineScope.launch { viewModel.deleteBuild(build) } }
                )
              }
            }
          }
        }
      }
    }
  }


}