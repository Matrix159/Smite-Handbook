package com.matrix.presentation.ui.goddetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.matrix.presentation.ui.components.ChipRow
import com.matrix.presentation.utils.getPantheonResourceId
import com.matrix.presentation.utils.getRoleResourceId
import com.matrix.presentation.viewmodels.GodDetailsUiState

@Composable
fun GodDetails(
  godDetailsUiState: GodDetailsUiState,
  scrollState: ScrollState,
  modifier: Modifier = Modifier
) {
  Surface(modifier) {
    Column(modifier = Modifier.verticalScroll(scrollState)) {
      godDetailsUiState.selectedGod?.let { selectedGod ->
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp, 8.dp)
          ) {
            Column(
              horizontalAlignment = Alignment.CenterHorizontally,
              modifier = Modifier.weight(1f)
            ) {
              Image(
                painterResource(getRoleResourceId(selectedGod.roles)),
                selectedGod.roles,
                modifier = Modifier.size(42.dp)
              )
              Text(selectedGod.roles)
            }
            Text(
              selectedGod.name,
              textAlign = TextAlign.Center,
              style = MaterialTheme.typography.titleLarge,
              modifier = Modifier.weight(1f, true)
            )
            Column(
              horizontalAlignment = Alignment.CenterHorizontally,
              modifier = Modifier.weight(1f)
            ) {
              Image(
                painterResource(getPantheonResourceId(selectedGod.pantheon)),
                "Pantheon",
                modifier = Modifier.size(42.dp)
              )
              Text(selectedGod.pantheon)
            }

          }

          var selectedChip by rememberSaveable { mutableStateOf(0) }
          ChipRow(listOf("Abilities", "Lore", "Skins")) { selectedChip = it ?: 0 }

          when (selectedChip) {
            0 -> {
              AbilityCard(
                selectedGod.abilityDetails5,
                isPassiveAbility = true,
                modifier = Modifier
                  .padding(8.dp)
                  .fillMaxWidth(),

                )
              AbilityCard(
                selectedGod.abilityDetails1, modifier = Modifier
                  .padding(8.dp)
                  .fillMaxWidth()
              )
              AbilityCard(
                selectedGod.abilityDetails2, modifier = Modifier
                  .padding(8.dp)
                  .fillMaxWidth()
              )
              AbilityCard(
                selectedGod.abilityDetails3, modifier = Modifier
                  .padding(8.dp)
                  .fillMaxWidth()
              )
              AbilityCard(
                selectedGod.abilityDetails4, modifier = Modifier
                  .padding(8.dp)
                  .fillMaxWidth()
              )
            }
            1 -> {
              Lore(
                god = selectedGod,
                modifier = Modifier.fillMaxSize()
              )
            }
            2 -> GodSkins(godDetailsUiState.godSkins, Modifier.fillMaxSize())
          }
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