package com.matrix.materializedsmite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.matrix.materializedsmite.ui.theme.MaterializedSmiteTheme
import com.matrix.materializedsmite.viewmodels.SmiteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val smiteViewModel: SmiteViewModel = viewModel()
      MaterializedSmiteTheme {
        // A surface container using the 'background' color from the theme
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colors.background
        ) {
          Column {
            val scope = rememberCoroutineScope()
            Button(onClick = {
              scope.launch {
                smiteViewModel.getGods()
              }
            }) {
              Text("Load Gods")
            }
            val gods = smiteViewModel.gods.value
            LazyColumn(modifier = Modifier.fillMaxSize()) {
              items(items = gods, itemContent = { item ->
                Card(modifier = Modifier.size(80.dp)) {
                  Image(
                    painter = rememberImagePainter(item.godCardURL),
                    contentDescription = "Smite god image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                  )
                }
//                Row(
//                  verticalAlignment = Alignment.CenterVertically,
//                  modifier = Modifier.fillMaxSize().padding(8.dp)
//                ) {
//                  Image(
//                    painter = rememberImagePainter(item.godIconURL),
//                    contentDescription = null,
//                    modifier = Modifier
//                      .size(64.dp)
//                      .clip(CircleShape)                       // clip to the circle shape
//                      .border(2.dp, Color.Gray, CircleShape)
//
//                  )
//                  Text(item.name, modifier = Modifier.padding(8.dp))
//                }
              })
            }
          }
        }
      }
    }
  }
}

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  MaterializedSmiteTheme {
    Greeting("Android")
  }
}