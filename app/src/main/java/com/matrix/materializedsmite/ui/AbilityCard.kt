package com.matrix.materializedsmite.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.matrix.materializedsmite.data.models.Ability
import com.matrix.materializedsmite.data.models.AbilityDescription
import com.matrix.materializedsmite.data.models.ItemDescription

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AbilityCard(abilityDetails: Ability, modifier: Modifier = Modifier) {
  Card(modifier = modifier) {
    Row {
      Image(
        painter = rememberImagePainter(abilityDetails.url),
        contentDescription = abilityDetails.summary,
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center,
        modifier = Modifier
          .padding(4.dp)
          .clip(CircleShape)
          .size(64.dp)
      )
      Column {
        Text(
          text = abilityDetails.summary,
          style = MaterialTheme.typography.titleLarge,
          modifier = Modifier.padding(4.dp)
        )
        Text(
          text = abilityDetails.description.itemDescription.description,
          style = MaterialTheme.typography.bodyMedium,
          modifier = Modifier.padding(4.dp)
        )
      }
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
  AbilityCard(
    Ability(
      id = 1,
      summary = "Shield of Achilles",
      url = "https://webcdn.hirezstudios.com/smite/god-abilities/shield-of-achilles.jpg",
      description = AbilityDescription(
        itemDescription = ItemDescription(
          cooldown = "14s",
          cost = "60/65/70/75/80",
          description = "Achilles punches forward with the edge of his Shield, inflicting massive damage and stunning enemy targets hit by the impact. The force of his punch continues to radiate past his initial target area, dealing 75% damage to targets farther away.",
          menuitems = listOf(),
          rankitems = listOf()
        )
      )
    )
  )
}