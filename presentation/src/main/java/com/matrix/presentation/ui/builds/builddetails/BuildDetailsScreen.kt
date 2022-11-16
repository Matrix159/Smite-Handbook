package com.matrix.presentation.ui.builds.builddetails

import androidx.compose.animation.Crossfade
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.matrix.domain.models.BuildInformation
import com.matrix.presentation.R
import com.matrix.presentation.ui.components.ErrorText
import com.matrix.presentation.ui.components.GodTitleCard
import com.matrix.presentation.ui.components.Loader
import com.matrix.presentation.ui.items.itemdetails.ItemDetailUiState
import com.matrix.presentation.ui.items.itemdetails.ItemDetails
import java.lang.Integer.max

@OptIn(
  ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class,
  ExperimentalMaterialApi::class
)
@Composable
fun BuildDetailsScreen(
  buildDetailsViewModel: BuildDetailsViewModel,
  onDeleteBuild: (buildInfo: BuildInformation) -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
  ) {
    val collectedUiState by buildDetailsViewModel.uiState.collectAsStateWithLifecycle()
    when (val uiState = collectedUiState) {
      is BuildDetailsUiState.Error -> ErrorText(uiState.exception)
      BuildDetailsUiState.Loading -> Loader()
      is BuildDetailsUiState.Success -> {
        var inEditMode by remember { mutableStateOf(false) }
        var buildNameTextFieldValue by remember {
          mutableStateOf(
            uiState.buildInformation.name ?: ""
          )
        }
        var selectedItem by remember { mutableStateOf(uiState.buildInformation.items[0]) }

        Box(
          modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
        ) {
          Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
              .matchParentSize()
              .verticalScroll(rememberScrollState())
          ) {
            Row(
              horizontalArrangement = Arrangement.End,
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.fillMaxWidth()
            ) {
              var showDeleteDialog by remember { mutableStateOf(false) }
              IconButton(
                onClick = { showDeleteDialog = true },
              ) {
                Icon(Icons.Default.Delete, contentDescription = "Delete build")
              }
              if (showDeleteDialog) {
                // Delete Dialog
                AlertDialog(onDismissRequest = { showDeleteDialog = false }, confirmButton = {
                  TextButton(onClick = {
                    onDeleteBuild(uiState.buildInformation)
                    showDeleteDialog = false
                  }) {
                    Text("Delete")
                  }
                }, dismissButton = {
                  TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancel")
                  }
                }, text = {
                  Text("Delete build?")
                })
              }
            }

            Crossfade(
              targetState = inEditMode,
            ) { inEditMode ->
              Layout(modifier = Modifier.fillMaxWidth(), content = {
                TextField(
                  value = buildNameTextFieldValue,
                  onValueChange = { buildNameTextFieldValue = it },
                  label = { Text("Build Name") },
                )
                Text(
                  text = uiState.buildInformation.name ?: "",
                  style = MaterialTheme.typography.headlineMedium,
                  textAlign = TextAlign.Center,
                  overflow = TextOverflow.Visible,
                )
              }) { measurables, constraints ->
                val textFieldPlaceable = measurables[0].measure(constraints)
                val textPlaceable = measurables[1].measure(constraints)
                val requiredWidth = max(textFieldPlaceable.height, textPlaceable.width)
                val requiredHeight = max(textFieldPlaceable.height, textPlaceable.height)
                layout(requiredWidth, requiredHeight) {
                  when (inEditMode) {
                    // Center the placeables
                    true -> textFieldPlaceable.place(
                      x = (requiredWidth - textFieldPlaceable.width) / 2,
                      y = (requiredHeight - textFieldPlaceable.height) / 2,
                    )

                    false -> textPlaceable.place(
                      x = (requiredWidth - textPlaceable.width) / 2,
                      y = (requiredHeight - textPlaceable.height) / 2,
                    )
                  }
                }
              }
            }

            Divider(modifier = Modifier.padding(bottom = 16.dp))

            // ---- GOD ----
            Row(
              verticalAlignment = Alignment.Top, modifier = Modifier.fillMaxWidth()
            ) {
              Box {
                GodTitleCard(
                  godImageUrl = uiState.buildInformation.god.godIconURL,
                  godName = uiState.buildInformation.god.name,
                  godTitle = uiState.buildInformation.god.title,
                  modifier = Modifier.fillMaxWidth()
                )
                androidx.compose.animation.AnimatedVisibility(
                  visible = inEditMode,
                  enter = fadeIn(),
                  exit = fadeOut(),
                  modifier = Modifier.matchParentSize()
                ) {
                  Box(
                    modifier = Modifier.border(
                      1.dp,
                      MaterialTheme.colorScheme.secondary,
                      MaterialTheme.shapes.medium
                    )
                  ) {
                    Box(
                      modifier = Modifier
                        .background(
                          MaterialTheme.colorScheme.secondary,
                          RoundedCornerShape(0.dp, 12.dp, 0.dp, 12.dp)
                        )
                        .align(Alignment.TopEnd)
                    ) {
                      Icon(
                        Icons.Default.Edit,
                        contentDescription = "Change god",
                        tint = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier
                          .align(Alignment.TopEnd)
                          .padding(8.dp)
                      )
                    }
                  }
                }
              }
            }

            // ---- ITEMS ----
            Text(
              text = stringResource(R.string.build_details_items),
              style = MaterialTheme.typography.titleLarge,
              textAlign = TextAlign.Center,
              modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
            )
            Divider(modifier = Modifier.padding(bottom = 16.dp))

            Box(
              modifier = Modifier.let {
                when(inEditMode) {
                  true -> it.clickable {  }
                  else -> it
                }
              }
            ) {
              Column {
                Row(
                  modifier = Modifier.fillMaxWidth()
                ) {
                  for (item in uiState.buildInformation.items) {
                    AsyncImage(
                      model = item.itemIconURL,
                      contentDescription = item.deviceName,
                      modifier = Modifier
                        .weight(1f, fill = false)
                        .let {
                          if (selectedItem == item) {
                            it.background(
                              MaterialTheme.colorScheme.secondaryContainer,
                              RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp)
                            )
                          } else {
                            it
                          }
                        }
                        .size(64.dp)
                        .padding(8.dp)
                        .clip(MaterialTheme.shapes.small)
                        .clickable {
                          selectedItem = item
                        }
                    )
                  }
                }
                ItemDetails(
                  uiState = ItemDetailUiState.Success(selectedItem, emptyList()),
                  itemClicked = {
                    selectedItem = it
                  },
                  modifier = Modifier
                    .let {
                      when (uiState.buildInformation.items.indexOf(selectedItem)) {
                        0 -> it.background(
                          MaterialTheme.colorScheme.secondaryContainer,
                          RoundedCornerShape(0.dp, 12.dp, 12.dp, 12.dp)
                        )

                        uiState.buildInformation.items.lastIndex -> it.background(
                          MaterialTheme.colorScheme.secondaryContainer,
                          RoundedCornerShape(12.dp, 0.dp, 12.dp, 12.dp)
                        )

                        else -> it.background(
                          MaterialTheme.colorScheme.secondaryContainer,
                          MaterialTheme.shapes.medium
                        )
                      }
                    }
                    .padding(16.dp)
                )
              }

              androidx.compose.animation.AnimatedVisibility(
                visible = inEditMode,
                enter = fadeIn(),
                exit = fadeOut(),
                modifier = Modifier.matchParentSize()
              ) {
                Box(
                  modifier = Modifier
                    .border(
                      1.dp, MaterialTheme.colorScheme.secondary, MaterialTheme.shapes.medium
                    )
                ) {
                  Box(
                    modifier = Modifier
                      .background(
                        MaterialTheme.colorScheme.secondary,
                        RoundedCornerShape(0.dp, 12.dp, 0.dp, 12.dp)
                      )
                      .align(Alignment.TopEnd)
                  ) {
                    Icon(
                      Icons.Default.Edit,
                      contentDescription = "Change items",
                      tint = MaterialTheme.colorScheme.onSecondary,
                      modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                    )
                  }
                }
              }
            }
          }

          // FAB
          FloatingActionButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = {
              inEditMode = !inEditMode
            },
          ) {
            when (inEditMode) {
              true -> Icon(Icons.Default.Check, contentDescription = "Save build")
              false -> Icon(Icons.Default.Edit, contentDescription = "Edit build")
            }
          }
        }
      }
    }
  }
}