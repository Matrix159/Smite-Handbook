package com.matrix.presentation.ui.builds.createbuild

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.ItemInformation
import com.matrix.presentation.ui.components.ErrorText
import com.matrix.presentation.ui.components.GodTitleCard
import com.matrix.presentation.ui.components.Loader
import com.matrix.presentation.ui.gods.godlist.GodList
import com.matrix.presentation.ui.items.itemlist.ItemList
import kotlinx.coroutines.launch

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CreateBuildScreen(
  createBuildViewModel: CreateBuildViewModel,
  done: () -> Unit,
  modifier: Modifier = Modifier,
) {

  var showGodList by rememberSaveable {
    mutableStateOf(false)
  }
  var showItemList by rememberSaveable {
    mutableStateOf(false)
  }

  BackHandler {
    if (showGodList) {
      showGodList = false
    } else if (showItemList) {
      createBuildViewModel.clearSelectedItems()
      showItemList = false
    } else {
      done()
    }
  }

  val uiState by createBuildViewModel.uiState.collectAsStateWithLifecycle()
  val focusManager = LocalFocusManager.current

  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = modifier
  ) {
    when (val createBuildUiState = uiState) {
      is CreateBuildUiState.Error -> ErrorText(createBuildUiState.exception)
      is CreateBuildUiState.Loading -> Loader()
      is CreateBuildUiState.Success -> {
        Box(
          modifier = Modifier
            .fillMaxSize()
            .clickable(
              indication = null,
              interactionSource = remember { MutableInteractionSource() }
            ) {
              focusManager.clearFocus()
            }
        ) {
          Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Step(
              title = "Pick a God",
              stepNumber = 1,
              modifier = Modifier.fillMaxWidth()
            ) { paddingValues ->
              if (createBuildUiState.selectedGod != null) {
                GodTitleCard(
                  godImageUrl = createBuildUiState.selectedGod.godIconURL,
                  godName = createBuildUiState.selectedGod.name,
                  godTitle = createBuildUiState.selectedGod.title,
                  onClick = { showGodList = true },
                  modifier = Modifier.fillMaxWidth().padding(paddingValues)
                )
              } else {
                Button(onClick = { showGodList = true }, modifier = Modifier.padding(paddingValues)) {
                  Text(text = "Add god")
                }
              }
            }
            Step(
              title = "Pick the Items",
              stepNumber = 2,
              modifier = Modifier.fillMaxWidth()
            ) {paddingValues ->
              if (createBuildUiState.selectedItems.isNotEmpty()) {
                Row(
                  horizontalArrangement = Arrangement.Start,
                  verticalAlignment = Alignment.CenterVertically,
                  modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth()
                    .background(
                      color = MaterialTheme.colorScheme.secondaryContainer,
                      MaterialTheme.shapes.medium
                    )
                    .clip(MaterialTheme.shapes.medium)
                    .clickable { showItemList = true }
                ) {
                  for (item in createBuildUiState.selectedItems) {
                    AsyncImage(
                      model = item.itemIconURL,
                      contentDescription = item.deviceName,
                      modifier = Modifier
                        .weight(1f, fill = false)
                        .size(64.dp)
                        .padding(8.dp)
                        .clip(MaterialTheme.shapes.small)
                    )
                  }
                }
              } else {
                Button(onClick = { showItemList = true }, modifier = Modifier.padding(paddingValues)) {
                  Text(text = "Add items")
                }
              }
            }
            Step(
              title = "Give It a Name",
              stepNumber = 3,
              isLast = true,
              modifier = Modifier.fillMaxWidth()
            ) {paddingValues ->
              TextField(
                value = createBuildUiState.buildName,
                onValueChange = createBuildViewModel::updateBuildName,
                modifier = Modifier
                  .padding(paddingValues)
                  .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                  focusManager.clearFocus()
                }),
              )
            }
          }
          if (showGodList) {
            GodSelectionView(
              availableGods = createBuildUiState.gods,
              godSelected = {
                showGodList = false
                createBuildViewModel.setGod(it)
              }
            )
          }
          if (showItemList) {
            ItemSelectionView(
              availableItems = createBuildUiState.items,
              selectedItems = createBuildUiState.selectedItems,
              addSelectedItem = createBuildViewModel::addSelectedItem,
              removeSelectedItem = createBuildViewModel::removeSelectedItem
            )
          }
          // The FAB shouldn't show on the god list view, but do show as long as we have a
          // selected god/items or are selecting items
          if (
            !showGodList &&
            ((showItemList && createBuildUiState.selectedItems.isNotEmpty()) ||
              (createBuildUiState.selectedGod != null && createBuildUiState.selectedItems.isNotEmpty()))
          ) {
            val coroutineScope = rememberCoroutineScope()
            FloatingActionButton(
              onClick = {
                if (showItemList) {
                  showItemList = false
                } else {
                  coroutineScope.launch {
                    createBuildViewModel.createBuild()
                    done()
                  }
                }
              },
              modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
            ) {
              Icon(Icons.Default.Done, contentDescription = null)
            }
          }
        }
      }
    }
  }
}

@Composable
fun Step(
  title: String,
  stepNumber: Int,
  isLast: Boolean? = false,
  modifier: Modifier = Modifier,
  content: @Composable (paddingValues: PaddingValues) -> Unit,
) {
  Column(modifier = modifier.padding(horizontal = 8.dp)) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
          .padding(8.dp)
          .background(
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = CircleShape
          )
          .size(36.dp)
      ) {
        Text(
          text = stepNumber.toString(),
          textAlign = TextAlign.Center,
        )
      }
      // Step title
      Text(text = title, style = MaterialTheme.typography.titleLarge)
    }
    Row(
      modifier = Modifier
        .padding(start = 26.dp)
        .height(IntrinsicSize.Min)
    ) {
      Divider(
        color = if (isLast == false) MaterialTheme.colorScheme.outline else Color.Transparent,
        modifier = Modifier
          .fillMaxHeight()
          .width(1.dp)
      )
      content(PaddingValues(horizontal = 24.dp, vertical = 8.dp))
    }
  }
}

@Composable
fun GodSelectionView(
  availableGods: List<GodInformation>,
  godSelected: (god: GodInformation) -> Unit,
  modifier: Modifier = Modifier,
) {
  Surface(modifier = modifier) {
    GodList(
      gods = availableGods,
      godClicked = godSelected,
      modifier = Modifier.fillMaxSize()
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemSelectionView(
  availableItems: List<ItemInformation>,
  selectedItems: List<ItemInformation>,
  addSelectedItem: (item: ItemInformation) -> Unit,
  removeSelectedItem: (item: ItemInformation) -> Unit,
  modifier: Modifier = Modifier,
) {
  Surface(modifier = modifier) {
    Column {
      if (selectedItems.isNotEmpty()) {
        Row(
          horizontalArrangement = Arrangement.Start,
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
            //.height(88.dp)
            .padding(4.dp)
            .fillMaxWidth()
        ) {
          for (item in selectedItems) {
            Box(modifier = Modifier) {
              AsyncImage(
                model = item.itemIconURL,
                contentDescription = item.deviceName,
                modifier = Modifier
                  .padding(8.dp)
                  .size(48.dp)
              )
              Badge(modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable { removeSelectedItem(item) }
              ) {
                Icon(Icons.Default.Close, contentDescription = "remove")
              }
            }
          }
        }
      }
      ItemList(
        items = availableItems,
        itemClicked = addSelectedItem,
        modifier = Modifier.weight(1f)
      )
    }
  }
}