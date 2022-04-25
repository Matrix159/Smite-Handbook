package com.matrix.materializedsmite.ui.goddetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.matrix.materializedsmite.ui.components.Chip
import com.matrix.materializedsmite.ui.components.ChipRow
import com.matrix.materializedsmite.utils.getPantheonResourceId
import com.matrix.materializedsmite.utils.getRoleResourceId
import com.matrix.materializedsmite.viewmodels.SmiteViewModel

@Composable
fun GodDetails(
  smiteAppViewModel: SmiteViewModel,
  scrollState: ScrollState,
  modifier: Modifier = Modifier
) {
  val selectedGod = smiteAppViewModel.selectedGod.value
  Surface(modifier) {
    Column(modifier = Modifier.verticalScroll(scrollState)) {
      selectedGod?.run {
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
          ChipRow(listOf("Abilities", "Stats", "Lore")) { selectedChip = it }

          when (selectedChip) {
            0 -> {
              AbilityCard(
                abilityDetails5,
                isPassiveAbility = true,
                modifier = Modifier
                  .padding(8.dp)
                  .fillMaxWidth(),

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
            2 -> Text(
              selectedGod.lore.replace("\\n", "\r\n"),
              style = MaterialTheme.typography.bodyMedium,
              modifier = Modifier.padding(16.dp)
            )
            // Passive

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