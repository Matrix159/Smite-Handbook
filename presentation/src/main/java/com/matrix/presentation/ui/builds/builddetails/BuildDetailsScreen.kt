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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.matrix.presentation.R
import com.matrix.presentation.ui.components.ErrorText
import com.matrix.presentation.ui.components.GodTitleCard
import com.matrix.presentation.ui.components.Loader
import com.matrix.presentation.ui.extension.conditional
import com.matrix.presentation.ui.items.itemdetails.ItemDetailUiState
import com.matrix.presentation.ui.items.itemdetails.ItemDetails
import com.matrix.presentation.ui.preview.SmiteHandbookPreviews
import com.matrix.presentation.ui.theme.SmiteHandbookTheme
import com.matrix.shared.data.model.builds.BuildInformation
import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.model.items.ItemInformation
import java.lang.Integer.max

@Composable
fun BuildDetailsScreen(
  buildDetailsViewModel: BuildDetailsViewModel,
  navigateToGodList: () -> Unit,
  navigateToItemList: (Array<Long>) -> Unit,
  onDeleteBuild: (buildInfo: BuildInformation) -> Unit,
  modifier: Modifier = Modifier,
) {
  val collectedUiState by buildDetailsViewModel.uiState.collectAsStateWithLifecycle()

  BuildDetailsScreen(
    collectedUiState,
    navigateToGodList = navigateToGodList,
    navigateToItemList = navigateToItemList,
    onDeleteBuild = {
      buildDetailsViewModel.deleteBuild(it)
      onDeleteBuild(it)
    },
    onSaveBuild = {
      buildDetailsViewModel.saveBuild(it)
    },
    modifier = modifier
  )
}

@Composable
private fun BuildDetailsScreen(
  buildDetailsUiState: BuildDetailsUiState,
  navigateToGodList: () -> Unit,
  navigateToItemList: (Array<Long>) -> Unit,
  onDeleteBuild: (buildInfo: BuildInformation) -> Unit,
  onSaveBuild: (buildInfo: BuildInformation) -> Unit,
  modifier: Modifier = Modifier,
) {

  val focusManger = LocalFocusManager.current

  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
  ) {

    when (val uiState = buildDetailsUiState) {
      is BuildDetailsUiState.Error -> ErrorText(uiState.exception)
      BuildDetailsUiState.Loading -> Loader()
      is BuildDetailsUiState.Success -> {
        var isEditing by rememberSaveable { mutableStateOf(false) }
//        var editDetailState: EditDetailsState by remember(uiState) {
//          val buildInfo = uiState.buildInformation
//          mutableStateOf(
//            EditDetailsState(
//              name = buildInfo.name ?: "",
//              godInformation = buildInfo.god,
//              itemInformationList = buildInfo.items
//            )
//          )
//        }
        var selectedItem by remember(uiState.buildInformation.items) {
          mutableStateOf(if (uiState.buildInformation.items.isNotEmpty()) uiState.buildInformation.items[0] else null)
        }

        Box(
          modifier = Modifier
            .fillMaxSize()
        ) {
          Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
              .padding(16.dp)
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
                    showDeleteDialog = false
                    onDeleteBuild(uiState.buildInformation)
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
              targetState = isEditing,
              label = "Build details build name input"
            ) { inEditMode ->
              Layout(
                modifier = Modifier.fillMaxWidth(),
                content = {
                  TextField(
                    value = uiState.buildInformation.name ?: "",
                    onValueChange = { onSaveBuild(uiState.buildInformation.copy(name = it)) },
                    label = { Text(stringResource(R.string.build_name)) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                      focusManger.clearFocus()
                    }),
                  )
                  Text(
                    text = uiState.buildInformation.name ?: "",
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Visible,
                  )
                }
              ) { measurables, constraints ->
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
            Box(
              modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .conditional(isEditing) {
                  clickable {
                    navigateToGodList()
//                    editDetailState = editDetailState.copy(currentlyEditing = CurrentlyEditing.GODS)
                  }
                }
            ) {
              GodTitleCard(
                godImageUrl = uiState.buildInformation.god.godIconURL,
                godName = uiState.buildInformation.god.name,
                godTitle = uiState.buildInformation.god.title,
                modifier = Modifier.fillMaxWidth()
              )

              androidx.compose.animation.AnimatedVisibility(
                visible = isEditing,
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
              modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .conditional(isEditing) {
                  clickable {
                    navigateToItemList(uiState.buildInformation.items.map { it.itemID }
                      .toTypedArray())
//                    editDetailState =
//                      editDetailState.copy(currentlyEditing = CurrentlyEditing.ITEMS)
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
                        .conditional(selectedItem == item) {
                          background(
                            MaterialTheme.colorScheme.secondaryContainer,
                            RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp)
                          )
                        }
                        .size(64.dp)
                        .padding(8.dp)
                        .clip(MaterialTheme.shapes.small)
                        .conditional(!isEditing) {
                          clickable {
                            selectedItem = item
                          }
                        }
                    )
                  }
                }
                selectedItem?.let {
                  ItemDetails(
                    uiState = ItemDetailUiState.Success(it, emptyList()),
                    itemClicked = {
                      selectedItem = it
                    },
                    modifier = Modifier
                      .let { modifier ->
                        when (uiState.buildInformation.items.indexOf(selectedItem)) {
                          0 -> modifier.background(
                            MaterialTheme.colorScheme.secondaryContainer,
                            RoundedCornerShape(0.dp, 12.dp, 12.dp, 12.dp)
                          )

                          uiState.buildInformation.items.lastIndex -> modifier.background(
                            MaterialTheme.colorScheme.secondaryContainer,
                            RoundedCornerShape(12.dp, 0.dp, 12.dp, 12.dp)
                          )

                          else -> modifier.background(
                            MaterialTheme.colorScheme.secondaryContainer,
                            MaterialTheme.shapes.medium
                          )
                        }
                      }
                      .padding(16.dp)
                  )
                }
              }

              androidx.compose.animation.AnimatedVisibility(
                visible = isEditing,
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

//          when (editDetailState.currentlyEditing) {
//            CurrentlyEditing.GODS -> GodSelectionView(
//              gods = uiState.allGods,
//              godSelected = { selectedGod ->
//                editDetailState = editDetailState.copy(
//                  godInformation = selectedGod,
//                  currentlyEditing = CurrentlyEditing.NONE
//                )
//              },
//              // TODO
//              appliedGodFilters = AppliedGodFilters(),
//              updateAppliedGodFilters = {},
//              modifier = Modifier.fillMaxSize()
//            )
//
//            CurrentlyEditing.ITEMS -> ItemSelectionView(
//              items = uiState.allItems,
//              selectedItems = editDetailState.itemInformationList,
//              addSelectedItem = {
//                editDetailState =
//                  editDetailState.copy(itemInformationList = editDetailState.itemInformationList + it)
//              },
//              removeSelectedItem = {
//                editDetailState =
//                  editDetailState.copy(itemInformationList = editDetailState.itemInformationList - it)
//              },
//              modifier = Modifier.fillMaxSize()
//            )
//
//            else -> {}
//          }
          // FAB
          FloatingActionButton(
            modifier = Modifier
              .align(Alignment.BottomEnd)
              .padding(16.dp),
            onClick = {
              when (isEditing) {
                true -> {
                  isEditing = false
                  // Don't perform a save action when using the fab on the item screen, just go back
                  // to previous view and stay in edit mode
//                  onSaveBuild(
//                    BuildInformation(
//                      id = uiState.buildInformation.id,
//                      name = uiState.buildInformation.name,
//                      god = uiState.buildInformation.god,
//                      items = uiState.buildInformation.items,
//                    )
//                  )
                }
                false -> {
                  isEditing = true
                }
              }
            },
          ) {
            when (isEditing) {
              true -> Icon(Icons.Default.Check, contentDescription = "Save build")
              false -> Icon(Icons.Default.Edit, contentDescription = "Edit build")
            }
          }
        }
      }
    }
  }
}

private data class EditDetailsState(
  val inEditMode: Boolean = false,
  val currentlyEditing: CurrentlyEditing = CurrentlyEditing.NONE,
  val name: String,
  val godInformation: GodInformation,
  val itemInformationList: List<ItemInformation>,
)

private enum class CurrentlyEditing {
  NONE,
  GODS,
  ITEMS,
}

@SmiteHandbookPreviews
@Composable
fun BuildDetailsScreenPreview() {
  SmiteHandbookTheme {
    BuildDetailsScreen(
      buildDetailsUiState = BuildDetailsUiState.Success(
        buildInformation = BuildInformation.default(),
        allGods = emptyList(),
        allItems = emptyList(),
      ),
      navigateToGodList = {},
      navigateToItemList = {},
      onDeleteBuild = {},
      onSaveBuild = {},
    )
  }
}