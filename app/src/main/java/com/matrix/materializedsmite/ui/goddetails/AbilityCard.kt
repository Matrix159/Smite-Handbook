package com.matrix.materializedsmite.ui.goddetails

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.matrix.materializedsmite.data.models.Ability
import com.matrix.materializedsmite.data.models.AbilityDescription
import com.matrix.materializedsmite.data.models.AbilityItemDescription

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AbilityCard(
  abilityDetails: Ability,
  isPassiveAbility: Boolean = false,
  modifier: Modifier = Modifier
) {
  var expanded by remember { mutableStateOf(false) }
  Card(modifier = modifier
    .animateContentSize()
    .clip(MaterialTheme.shapes.large)
    .clickable { expanded = !expanded }
  ) {
    Row {
      AsyncImage(
        model = abilityDetails.url,
        contentDescription = abilityDetails.summary,
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center,
        modifier = Modifier
          .animateContentSize()
          .clip(MaterialTheme.shapes.small)
          .size(if (expanded) 72.dp else 54.dp)
      )
      Column {
        Row(
          horizontalArrangement = Arrangement.SpaceBetween,
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, end = 8.dp, bottom = 4.dp, start = 4.dp)
        ) {
          Text(
            text = abilityDetails.summary,
            style = MaterialTheme.typography.titleMedium,
          )
          if (isPassiveAbility) {
            Text(
              text = "Passive",
              fontStyle = FontStyle.Italic,
              style = MaterialTheme.typography.titleMedium
            )
          }
        }

        if (expanded) {
          Text(
            text = abilityDetails.description.itemDescription.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(4.dp)
          )

          val cooldown = abilityDetails.description.itemDescription.cooldown
          val cost = abilityDetails.description.itemDescription.cost
          Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
              .fillMaxWidth()
              .padding(top = 8.dp, end = 8.dp)
          ) {
            if (cooldown.isNotBlank()) {
              Column {
                Text("Cooldown", style = MaterialTheme.typography.labelMedium)
                Text(cooldown, style = MaterialTheme.typography.bodyMedium)
              }
            }
            if (cost.isNotBlank()) {
              Column(horizontalAlignment = Alignment.End) {
                Text("Cost", style = MaterialTheme.typography.labelMedium)
                Text(cost, style = MaterialTheme.typography.bodyMedium)
              }
            }
          }

          for (rankItem in abilityDetails.description.itemDescription.rankitems) {
            Text(
              "${rankItem.description} ${rankItem.value}",
              style = MaterialTheme.typography.bodyMedium,
              modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            )
          }
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
          itemDescription = AbilityItemDescription(
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