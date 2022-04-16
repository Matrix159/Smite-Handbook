package com.matrix.materializedsmite.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.matrix.materializedsmite.viewmodels.SmiteAppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GodDetails(smiteAppViewModel: SmiteAppViewModel, modifier: Modifier = Modifier) {
  val selectedGod = smiteAppViewModel.selectedGod.value
  if (selectedGod != null) {
    Column(
      modifier = modifier
        .fillMaxSize()
        .padding(8.dp)
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
      AbilityCard(abilityUrl = selectedGod.godAbility1URL, abilityName = selectedGod.ability1)
      AbilityCard(abilityUrl = selectedGod.godAbility2URL, abilityName = selectedGod.ability2)
      AbilityCard(abilityUrl = selectedGod.godAbility3URL, abilityName = selectedGod.ability3)
      AbilityCard(abilityUrl = selectedGod.godAbility4URL, abilityName = selectedGod.ability4)
      AbilityCard(abilityUrl = selectedGod.godAbility5URL, abilityName = selectedGod.ability5)
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