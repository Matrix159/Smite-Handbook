package com.matrix.materializedsmite.ui.goddetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.matrix.materializedsmite.R
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
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
          ) {
            Image(
              painterResource(R.drawable.arthurian_logo),
              "Pantheon",
              modifier = Modifier.size(42.dp)
            )
//            AsyncImage(
//              model = selectedGod.
//            )
            Text(selectedGod.name, style = MaterialTheme.typography.titleLarge)
            Image(
              painterResource(R.drawable.assassin),
              "Assassin",
              modifier = Modifier.size(42.dp)
            )
//            AsyncImage()
          }

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