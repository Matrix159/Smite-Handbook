package com.matrix.presentation.ui.gods.goddetails

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
import com.matrix.presentation.utils.getPantheonDrawableResourceId
import com.matrix.presentation.utils.getRoleDrawableResourceId

@Composable
fun GodDetails(
  godDetailsUiState: GodDetailsUiState.Success,
  scrollState: ScrollState,
  modifier: Modifier = Modifier
) {
  val selectedGod = godDetailsUiState.godInformation
  Surface(modifier.statusBarsPadding()) {
    Column(modifier = Modifier.verticalScroll(scrollState)) {
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
                painterResource(getRoleDrawableResourceId(selectedGod.roles)),
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
                painterResource(getPantheonDrawableResourceId(selectedGod.pantheon)),
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
            2 -> GodSkins(godDetailsUiState.skins, Modifier.fillMaxSize())
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