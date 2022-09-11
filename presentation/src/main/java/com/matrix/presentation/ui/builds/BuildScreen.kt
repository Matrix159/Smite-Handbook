package com.matrix.presentation.ui.builds

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuildScreen(modifier: Modifier = Modifier) {
  Scaffold(
    floatingActionButton = {
      FloatingActionButton(
        onClick = { },
        //shape = MaterialTheme.shapes.small
      ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
      }
    },
    modifier = modifier
  ) {
    LazyColumn(content = {
//      items(1000) {
//        Text("Hello world, it updates in realtime")
//      }
    })
  }
}