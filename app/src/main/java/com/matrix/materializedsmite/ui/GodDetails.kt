package com.matrix.materializedsmite.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.matrix.materializedsmite.viewmodels.SmiteViewModel

@Composable
fun GodDetails(smiteAppViewModel: SmiteViewModel,
               scrollState: ScrollState,
               modifier: Modifier = Modifier) {
  val selectedGod = smiteAppViewModel.selectedGod.value
  Surface(modifier) {
    Column(modifier = Modifier.verticalScroll(scrollState)) {
      selectedGod?.run {
        AbilityCard(abilityDetails1, modifier = Modifier
          .padding(bottom = 8.dp)
          .fillMaxWidth())
        AbilityCard(abilityDetails2, modifier = Modifier
          .padding(bottom = 8.dp)
          .fillMaxWidth())
        AbilityCard(abilityDetails3, modifier = Modifier
          .padding(bottom = 8.dp)
          .fillMaxWidth())
        AbilityCard(abilityDetails4, modifier = Modifier
          .padding(bottom = 8.dp)
          .fillMaxWidth())
        AbilityCard(abilityDetails5, modifier = Modifier
          .padding(bottom = 8.dp)
          .fillMaxWidth())
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