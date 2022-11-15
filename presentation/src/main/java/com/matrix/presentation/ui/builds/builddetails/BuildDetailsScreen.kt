package com.matrix.presentation.ui.builds.builddetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
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

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)
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
                AlertDialog(
                  onDismissRequest = { showDeleteDialog = false },
                  confirmButton = {
                    TextButton(onClick = {
                      onDeleteBuild(uiState.buildInformation)
                      showDeleteDialog = false
                    }) {
                      Text("Delete")
                    }
                  },
                  dismissButton = {
                    TextButton(onClick = { showDeleteDialog = false }) {
                      Text("Cancel")
                    }
                  },
                  text = {
                    Text("Delete build?")
                  }
                )
              }

            }

            when (inEditMode) {
              true -> TextField(
                value = buildNameTextFieldValue,
                onValueChange = { buildNameTextFieldValue = it },
                label = { Text("Build Name") },
                modifier = Modifier.fillMaxWidth()
              )

              false -> Text(
                text = uiState.buildInformation.name ?: "",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Visible,
                modifier = Modifier.fillMaxWidth()
              )
            }
            Divider(modifier = Modifier.padding(bottom = 16.dp))

            Row(
              verticalAlignment = Alignment.Top,
              modifier = Modifier.fillMaxWidth()
            ) {
              Box {
                GodTitleCard(
                  godImageUrl = uiState.buildInformation.god.godIconURL,
                  godName = uiState.buildInformation.god.name,
                  godTitle = uiState.buildInformation.god.title,
                  modifier = Modifier.fillMaxWidth()
                )
                if (inEditMode) {
                  Box(
                    modifier = Modifier
                      .border(1.dp, MaterialTheme.colorScheme.secondary, MaterialTheme.shapes.medium)
                      .matchParentSize()
                  ) {
                    Box(
                      modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(0.dp, 12.dp, 0.dp, 12.dp))
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
            Text(
              text = stringResource(R.string.build_details_items),
              style = MaterialTheme.typography.titleLarge,
              textAlign = TextAlign.Center,
              modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
            )
            Divider(modifier = Modifier.padding(bottom = 16.dp))

            Box {
              Row(
                modifier = Modifier.fillMaxWidth()
              ) {
                for (item in uiState.buildInformation.items) {
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
              if (inEditMode) {
                Box(
                  modifier = Modifier
                    .border(
                      1.dp,
                      MaterialTheme.colorScheme.secondary,
                      MaterialTheme.shapes.medium
                    )
                    .matchParentSize()
                ) {
                  Box(
                    modifier = Modifier
                      .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(0.dp, 12.dp, 0.dp, 12.dp))
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

          // Edit/Save actions
          Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
              .fillMaxWidth()
              .align(Alignment.BottomEnd)
          ) {
            FloatingActionButton(
              onClick = {
                inEditMode = !inEditMode
              },
            ) {
              when (inEditMode) {
                true -> Icon(Icons.Default.Save, contentDescription = "Save build")
                false -> Icon(Icons.Default.Edit, contentDescription = "Edit build")
              }
            }
          }
        }
      }
    }
  }
}