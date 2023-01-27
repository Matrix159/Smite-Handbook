package com.matrix.presentation.ui.builds.builddetails

import GodSelectionView
import ItemSelectionView
import androidx.compose.animation.Crossfade
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.matrix.presentation.R
import com.matrix.presentation.ui.components.ErrorText
import com.matrix.presentation.ui.components.GodTitleCard
import com.matrix.presentation.ui.components.Loader
import com.matrix.presentation.ui.extension.conditional
import com.matrix.presentation.ui.items.itemdetails.ItemDetailUiState
import com.matrix.presentation.ui.items.itemdetails.ItemDetails
import com.matrix.shared.data.model.builds.BuildInformation
import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.model.items.ItemInformation
import java.lang.Integer.max

@OptIn(
  ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class,
)
@Composable
fun BuildDetailsScreen(
  buildDetailsViewModel: BuildDetailsViewModel,
  onDeleteBuild: (buildInfo: BuildInformation) -> Unit,
  modifier: Modifier = Modifier,
) {
  val collectedUiState by buildDetailsViewModel.uiState.collectAsStateWithLifecycle()
  val focusManger = LocalFocusManager.current

  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
  ) {

    when (val uiState = collectedUiState) {
      is BuildDetailsUiState.Error -> ErrorText(uiState.exception)
      BuildDetailsUiState.Loading -> Loader()
      is BuildDetailsUiState.Success -> {
        var editDetailState: EditDetailsState by remember(uiState) {
          val buildInfo = uiState.buildInformation
          mutableStateOf(
            EditDetailsState(
              name = buildInfo.name ?: "",
              godInformation = buildInfo.god,
              itemInformationList = buildInfo.items
            )
          )
        }
        var selectedItem by remember(editDetailState.itemInformationList) {
          mutableStateOf(if (editDetailState.itemInformationList.isNotEmpty()) editDetailState.itemInformationList[0] else null)
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
            BadgedBox(badge = ) {
              
            }
            Crossfade(
              targetState = editDetailState.inEditMode,
            ) { inEditMode ->
              Layout(
                modifier = Modifier.fillMaxWidth(),
                content = {
                  TextField(
                    value = editDetailState.name,
                    onValueChange = { editDetailState = editDetailState.copy(name = it) },
                    label = { Text("Build Name") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                      focusManger.clearFocus()
                    }),
                  )
                  Text(
                    text = editDetailState.name,
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
                .conditional(editDetailState.inEditMode) {
                  clickable {
                    editDetailState = editDetailState.copy(currentlyEditing = CurrentlyEditing.GODS)
                  }
                }
            ) {
              GodTitleCard(
                godImageUrl = editDetailState.godInformation.godIconURL,
                godName = editDetailState.godInformation.name,
                godTitle = editDetailState.godInformation.title,
                modifier = Modifier.fillMaxWidth()
              )

              androidx.compose.animation.AnimatedVisibility(
                visible = editDetailState.inEditMode,
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
                .conditional(editDetailState.inEditMode) {
                  clickable {
                    editDetailState =
                      editDetailState.copy(currentlyEditing = CurrentlyEditing.ITEMS)
                  }
                }
            ) {
              Column {
                Row(
                  modifier = Modifier.fillMaxWidth()
                ) {
                  for (item in editDetailState.itemInformationList) {
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
                        .conditional(!editDetailState.inEditMode) {
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
                      .let {
                        when (editDetailState.itemInformationList.indexOf(selectedItem)) {
                          0 -> it.background(
                            MaterialTheme.colorScheme.secondaryContainer,
                            RoundedCornerShape(0.dp, 12.dp, 12.dp, 12.dp)
                          )

                          editDetailState.itemInformationList.lastIndex -> it.background(
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
              }

              androidx.compose.animation.AnimatedVisibility(
                visible = editDetailState.inEditMode,
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

          when (editDetailState.currentlyEditing) {
            CurrentlyEditing.GODS -> GodSelectionView(
              gods = uiState.allGods,
              godSelected = { selectedGod ->
                editDetailState = editDetailState.copy(
                  godInformation = selectedGod,
                  currentlyEditing = CurrentlyEditing.NONE
                )
              },
              modifier = Modifier.fillMaxSize()
            )

            CurrentlyEditing.ITEMS -> ItemSelectionView(
              items = uiState.allItems,
              selectedItems = editDetailState.itemInformationList,
              addSelectedItem = {
                editDetailState =
                  editDetailState.copy(itemInformationList = editDetailState.itemInformationList + it)
              },
              removeSelectedItem = {
                editDetailState =
                  editDetailState.copy(itemInformationList = editDetailState.itemInformationList - it)
              },
              modifier = Modifier.fillMaxSize()
            )

            else -> {}
          }
          // FAB
          if (!(editDetailState.currentlyEditing == CurrentlyEditing.ITEMS && editDetailState.itemInformationList.isEmpty())) {
            FloatingActionButton(
              modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
              onClick = {
                when (editDetailState.inEditMode) {
                  true -> {
                    // Don't perform a save action when using the fab on the item screen, just go back
                    // to previous view and stay in edit mode
                    if (editDetailState.currentlyEditing != CurrentlyEditing.ITEMS) {
                      buildDetailsViewModel.saveBuild(
                        BuildInformation(
                          id = uiState.buildInformation.id,
                          name = editDetailState.name,
                          god = editDetailState.godInformation,
                          items = editDetailState.itemInformationList,
                        )
                      )
                      editDetailState = editDetailState.copy(
                        inEditMode = false,
                        currentlyEditing = CurrentlyEditing.NONE,
                      )
                    } else {
                      editDetailState =
                        editDetailState.copy(currentlyEditing = CurrentlyEditing.NONE)
                    }
                  }

                  false -> {
                    editDetailState = editDetailState.copy(inEditMode = true)
                  }
                }
              },
            ) {
              when (editDetailState.inEditMode) {
                true -> Icon(Icons.Default.Check, contentDescription = "Save build")
                false -> Icon(Icons.Default.Edit, contentDescription = "Edit build")
              }
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
