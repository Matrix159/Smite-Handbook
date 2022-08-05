package com.matrix.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.matrix.presentation.ui.components.ErrorText
import com.matrix.presentation.ui.components.Loader
import com.matrix.presentation.ui.itemdetails.ItemDetails
import com.matrix.presentation.ui.itemdetails.ItemFilterModal
import com.matrix.presentation.viewmodels.ItemViewModel
import com.matrix.presentation.viewmodels.LoadingState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ItemList(
    viewModel: ItemViewModel,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(Unit) {
        viewModel.loadState()
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        when (viewModel.uiState.loadingState) {
            LoadingState.LOADING -> Loader()
            LoadingState.ERROR -> ErrorText(viewModel.uiState.errorMessages.joinToString(", "))
            LoadingState.DONE -> {
                // Wrapped in ItemFilterModal for a bottom sheet modal for filters
                ItemFilterModal(onFilterApplied = {}) { bottomSheetState ->
                    val selectedItem = viewModel.uiState.selectedItem
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val items = viewModel.uiState.items
                        var searchValue by rememberSaveable { mutableStateOf("") }
                        var selectedTier by rememberSaveable { mutableStateOf<Int?>(null) }
                        val filteredItems = remember(items, searchValue, selectedTier) {
                            items
                                .filter { item ->
                                    if (searchValue.isNotBlank()) {
                                        item.deviceName.contains(
                                            searchValue,
                                            true
                                        ) && item.activeFlag == "y"
                                    } else {
                                        item.activeFlag == "y"
                                    }
                                }.filter { item ->
                                    selectedTier?.let { item.itemTier.toInt() == it } ?: true
                                }
                        }
                        val focusManager = LocalFocusManager.current
                        // Search field and filter action
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        ) {
                            TextField(
                                value = searchValue,
                                onValueChange = { searchValue = it },
                                label = {
                                    Text("Search for an item")
                                },
                                singleLine = false,
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
                                        bottomSheetState.show()
                                    }
                                },
                            ) {
                                Icon(
                                    Icons.Default.List,
                                    "Filter items",
                                    modifier = Modifier.size(48.dp)
                                )
                            }
                        }

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(items = filteredItems) { item ->
                                Box(
                                    contentAlignment = Alignment.BottomCenter,
                                    modifier = Modifier
                                        //.height(128.dp)
                                        //.fillMaxWidth()
                                        .padding(8.dp)
                                        .clip(MaterialTheme.shapes.extraLarge)
                                        .border(
                                            1.dp,
                                            MaterialTheme.colorScheme.outline,
                                            MaterialTheme.shapes.extraLarge
                                        )
                                        .clickable {
                                            viewModel.setItem(item)
                                        }
                                ) {
                                    AsyncImage(
                                        model = item.itemIconURL,
                                        contentDescription = item.deviceName,
                                        contentScale = ContentScale.FillWidth,
                                        alignment = Alignment.Center,
                                        modifier = Modifier
                                            //.height(80.dp)
                                            .fillMaxWidth()
                                    )
                                    Box(
                                        modifier = Modifier
                                            .matchParentSize()
                                            .background(
                                                brush = Brush.verticalGradient(
                                                    0F to Color.Transparent,
                                                    .5F to Color(0x40000000),
                                                    .75f to Color(0x80000000),
                                                    1f to Color(0xFF000000)
                                                )
                                            )
                                    )
                                    Text(
                                        text = item.deviceName,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }
                        }
                    }
                    AnimatedVisibility(selectedItem != null) {
                        selectedItem?.let {
                            ItemDetails(
                                selectedItem,
                                viewModel.uiState.baseItemTreeNodes,
                                Modifier
                                    .background(MaterialTheme.colorScheme.background)
                                    .fillMaxSize()
                                    .clickable { viewModel.setItem(null) }
                                    .padding(16.dp)
                            ) {
                                viewModel.setItem(it)
                            }
                        }
                    }
                }
            }
        }
    }
}
