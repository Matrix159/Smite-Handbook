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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.matrix.materializedsmite.ui.goddetails.AbilityImage
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
          Text(selectedGod.name, style = MaterialTheme.typography.displaySmall)
//          // Passive
//          AbilityImage(
//            selectedGod.abilityDetails5,
//            selected = selectedAbility == 5,
//            modifier = Modifier.padding(8.dp),
//            clicked = { selectedAbility = 5 }
//          )
//          AbilityImage(
//            selectedGod.abilityDetails1,
//            selected = selectedAbility == 1,
//            modifier = Modifier.padding(8.dp),
//            clicked = { selectedAbility = 1 }
//          )
//          AbilityImage(
//            selectedGod.abilityDetails2,
//            selected = selectedAbility == 2,
//            modifier = Modifier.padding(8.dp),
//            clicked = { selectedAbility = 2 }
//          )
//          AbilityImage(
//            selectedGod.abilityDetails3,
//            selected = selectedAbility == 3,
//            modifier = Modifier.padding(8.dp),
//            clicked = { selectedAbility = 3 }
//          )
//          AbilityImage(
//            selectedGod.abilityDetails4,
//            selected = selectedAbility == 4,
//            modifier = Modifier.padding(8.dp),
//            clicked = { selectedAbility = 4 }
//          )
//        }
//        val abilityToDisplay =
//          when (selectedAbility) {
//            1 -> selectedGod.abilityDetails1
//            2 -> selectedGod.abilityDetails2
//            3 -> selectedGod.abilityDetails3
//            4 -> selectedGod.abilityDetails4
//            5 -> selectedGod.abilityDetails5
//            else -> null
//          }
//
//        if (abilityToDisplay != null) {
//          AbilityCard(abilityDetails = abilityToDisplay)
//        }

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