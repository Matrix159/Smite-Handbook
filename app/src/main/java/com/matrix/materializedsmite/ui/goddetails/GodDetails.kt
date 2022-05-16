package com.matrix.materializedsmite.ui.goddetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
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
import com.matrix.materializedsmite.viewmodels.GodViewModel
import kotlinx.coroutines.delay

@Composable
fun GodDetails(
  smiteAppViewModel: GodViewModel,
  scrollState: ScrollState,
  modifier: Modifier = Modifier
) {
//  val url = "https://static.wikia.nocookie.net/smite_gamepedia/images/5/5b/Cthulhu_select.ogg/revision/latest?cb=20200617130449" // your URL here
//  LaunchedEffect(true) {
//    MediaPlayer().apply {
//      setAudioAttributes(
//        AudioAttributes.Builder()
//          .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
//          .setUsage(AudioAttributes.USAGE_MEDIA)
//          .build()
//      )
//      setDataSource(url)
//      prepare() // might take long! (for buffering, etc)
//      start()
//    }
//  }

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
          ChipRow(listOf("Abilities", "Lore", "Skins")) { selectedChip = it ?: 0 }
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
            1 -> {
             Lore(
               god = selectedGod,
               modifier = Modifier.fillMaxSize()
             )
            }
            2 -> GodSkins(godSkins, Modifier.fillMaxSize())
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