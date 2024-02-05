package com.matrix.presentation.ui.builds.buildlist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matrix.presentation.ui.components.Loader
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(
  ExperimentalFoundationApi::class
)
@Composable
fun BuildListScreen(
  viewModel: BuildListViewModel,
  createBuild: () -> Unit,
  goToBuildDetails: (buildId: Long) -> Unit,
  modifier: Modifier = Modifier,
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
        val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(
          floatingActionButton = {
            FloatingActionButton(
              onClick = {
                createBuild()
              },
            ) {
              Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
          },
          snackbarHost = { SnackbarHost(snackbarHostState) },
          contentWindowInsets = WindowInsets(0, 0, 0, 0),
          modifier = Modifier.fillMaxSize()
        ) { paddingValues ->
          if (state.builds.isEmpty()) {
            Box(modifier = Modifier
              .fillMaxSize()
              .padding(paddingValues)) {
              Text(
                text = "No builds available",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.align(Alignment.Center)
              )
            }
          } else {
            LazyColumn(
              contentPadding = PaddingValues(16.dp),
              verticalArrangement = Arrangement.spacedBy(16.dp),
              modifier = Modifier.padding(paddingValues)
            ) {
              items(state.builds, key = { it.id!! }) { build ->
                var visible by remember { mutableStateOf(true) }
                AnimatedVisibility(
                  visible = visible,
                  exit = scaleOut(
                    animationSpec = tween(
                      durationMillis = 300,
                    )
                  )
                ) {
                  BuildOverviewCard(
                    buildInformation = build,
                    modifier = Modifier
                      .fillMaxWidth()
                      .animateItemPlacement(),
                    onDelete = {
                      visible = false
                      coroutineScope.launch {
                        // Give it time to animate out of view before deleting
                        delay(300)
                        viewModel.deleteBuild(build)
                        val snackbarResult = snackbarHostState.showSnackbar(
                          message = "Deleted build",
                          actionLabel = "Undo",
                          withDismissAction = true,
                          duration = SnackbarDuration.Short
                        )
                        if (snackbarResult == SnackbarResult.ActionPerformed) {
                          viewModel.addBuild(build)
                        }
                      }
                    },
                    goToBuildDetails = goToBuildDetails
                  )
                }
              }
            }
          }
        }
      }
    }
  }
}