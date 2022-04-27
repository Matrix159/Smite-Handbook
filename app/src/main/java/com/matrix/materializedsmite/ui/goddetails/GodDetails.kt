package com.matrix.materializedsmite.ui.goddetails

import android.util.Log
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
  val selectedGodState by smiteAppViewModel.selectedGod.collectAsState()
  Surface(modifier) {
    Column(modifier = Modifier.verticalScroll(scrollState)) {
      selectedGodState?.let { selectedGod ->
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
          ChipRow(listOf("Abilities", "Lore", "Skins")) { selectedChip = it }
          val godSkins by smiteAppViewModel.selectedGodSkins.collectAsState()

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
            1 -> Text(
              selectedGod.lore.replace("\\n", "\r\n"),
              style = MaterialTheme.typography.bodyMedium,
              modifier = Modifier.padding(16.dp)
            )
            2 -> GodSkins(godSkins, Modifier.fillMaxSize())
            // This composable doesn't work as it uses a scrollable element within a scrollable
            //
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