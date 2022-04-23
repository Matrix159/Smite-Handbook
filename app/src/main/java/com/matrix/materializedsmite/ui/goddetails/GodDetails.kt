package com.matrix.materializedsmite.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.matrix.materializedsmite.ui.goddetails.AbilityCard
import com.matrix.materializedsmite.viewmodels.SmiteViewModel

@Composable
fun GodDetails(
  smiteAppViewModel: SmiteViewModel,
  scrollState: ScrollState,
  modifier: Modifier = Modifier
) {
//  val systemUiController = rememberSystemUiController()
//  systemUiController.setSystemBarsColor(
//    color = Color.Transparent
//  )
  val selectedGod = smiteAppViewModel.selectedGod.value
  Surface(modifier) {
    Column(modifier = Modifier.verticalScroll(scrollState)) {
      var selectedAbility: Int by remember { mutableStateOf(0) }
      selectedGod?.run {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//          Row {
//            AsyncImage(
//              model = selectedGod.
//            )
//            Text(selectedGod.name, style = MaterialTheme.typography.displaySmall)
//            AsyncImage()
//          }

          // Passive
          AbilityCard(
            abilityDetails5, modifier = Modifier
              .padding(8.dp)
              .fillMaxWidth()
          )
          AbilityCard(
            abilityDetails1, modifier = Modifier
              .padding(8.dp)
              .fillMaxWidth()
          )
          AbilityCard(
            abilityDetails2, modifier = Modifier
              .padding(8.dp)
              .fillMaxWidth()
          )
          AbilityCard(
            abilityDetails3, modifier = Modifier
              .padding(8.dp)
              .fillMaxWidth()
          )
          AbilityCard(
            abilityDetails4, modifier = Modifier
              .padding(8.dp)
              .fillMaxWidth()
          )

        }
      }
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