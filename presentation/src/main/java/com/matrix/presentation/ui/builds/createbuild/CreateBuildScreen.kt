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
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.matrix.presentation.ui.components.ErrorText
import com.matrix.presentation.ui.components.GodTitleCard
import com.matrix.presentation.ui.components.Loader
import kotlinx.coroutines.launch

@Composable
fun CreateBuildScreen(
  createBuildViewModel: CreateBuildViewModel,
  navigateToGodList: () -> Unit,
  navigateToItemList: () -> Unit,
  done: () -> Unit,
  modifier: Modifier = Modifier,
) {

  val _buildUiState by createBuildViewModel.uiState.collectAsStateWithLifecycle()
  val focusManager = LocalFocusManager.current

  BackHandler {
    done()
  }

  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = modifier
  ) {
    // Creating local variables to get around the open getter error

    when (val buildUiState = _buildUiState) {
      is CreateBuildUiState.Error -> ErrorText(buildUiState.exception)
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
              if (buildUiState.selectedGod != null) {
                GodTitleCard(
                  godImageUrl = buildUiState.selectedGod.godIconURL,
                  godName = buildUiState.selectedGod.name,
                  godTitle = buildUiState.selectedGod.title,
                  onClick = { navigateToGodList() },
                  modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                )
              } else {
                Button(onClick = { navigateToGodList() }, modifier = Modifier.padding(paddingValues)) {
                  Text(text = "Add god")
                }
              }
            }
            Step(
              title = "Pick the Items",
              stepNumber = 2,
              modifier = Modifier.fillMaxWidth()
            ) {paddingValues ->
              if (buildUiState.selectedItems.isNotEmpty()) {
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
                    .clickable { navigateToItemList() }
                ) {
                  for (item in buildUiState.selectedItems) {
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
                Button(onClick = { navigateToItemList() }, modifier = Modifier.padding(paddingValues)) {
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
                value = buildUiState.buildName,
                onValueChange = createBuildViewModel::updateBuildName,
                singleLine = true,
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
          if (buildUiState.selectedGod != null && buildUiState.selectedItems.isNotEmpty()) {
            val coroutineScope = rememberCoroutineScope()
            FloatingActionButton(
              onClick = {
                  coroutineScope.launch {
                    createBuildViewModel.createBuild()
                    done()
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