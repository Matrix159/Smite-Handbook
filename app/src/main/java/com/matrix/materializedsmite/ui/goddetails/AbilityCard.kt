package com.matrix.materializedsmite.ui.goddetails

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.matrix.materializedsmite.data.models.Ability
import com.matrix.materializedsmite.data.models.AbilityDescription
import com.matrix.materializedsmite.data.models.ItemDescription

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AbilityCard(abilityDetails: Ability, modifier: Modifier = Modifier) {
  var expanded by remember { mutableStateOf(false) }
  Card(modifier = modifier
    .animateContentSize()
    .clip(MaterialTheme.shapes.extraLarge)
    .clickable { expanded = !expanded }
  ) {
    Row {
      AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
          .data(abilityDetails.url)
          .crossfade(true)
          .build(),
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
          modifier = Modifier.padding(top = 20.dp, end = 4.dp, bottom = 4.dp, start = 4.dp)
        )
        if (expanded) {
          Text(
            text = abilityDetails.description.itemDescription.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(4.dp)
          )
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
  MaterialTheme {
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
}