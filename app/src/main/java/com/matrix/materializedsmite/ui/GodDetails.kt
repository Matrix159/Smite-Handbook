package com.matrix.materializedsmite.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.matrix.materializedsmite.viewmodels.SmiteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GodDetails(smiteAppViewModel: SmiteViewModel, modifier: Modifier = Modifier) {
  val selectedGod = smiteAppViewModel.selectedGod.value
  if (selectedGod != null) {
    Column(
      modifier = modifier
        .fillMaxSize()
        .padding(8.dp)
        .verticalScroll(rememberScrollState())
    ) {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .height(300.dp)
          .padding(bottom = 16.dp)
      ) {
        Image(
          painter = rememberImagePainter(selectedGod.godCardURL),
          contentDescription = selectedGod.name,
          contentScale = ContentScale.Crop,
          alignment = Alignment.TopCenter,
          modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
        )
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Start,
          modifier = Modifier.fillMaxSize()
        ) {
          Text(selectedGod.name)
        }
      }
      AbilityCard(selectedGod.abilityDetails1)
      AbilityCard(selectedGod.abilityDetails2)
      AbilityCard(selectedGod.abilityDetails3)
      AbilityCard(selectedGod.abilityDetails4)
      AbilityCard(selectedGod.abilityDetails5)
    }
  }
}

//@Preview
//@Composable
//fun Preview() {
//  val viewModel = SmiteAppViewModel()
//  viewModel.setGod(GodInformation())
//  GodDetails(smiteAppViewModel = SmiteAppViewModel())
//}