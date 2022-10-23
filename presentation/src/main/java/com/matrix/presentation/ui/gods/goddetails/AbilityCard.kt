package com.matrix.presentation.ui.gods.goddetails

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
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
import com.matrix.domain.models.Ability
import com.matrix.domain.models.AbilityDescription

@Composable
fun AbilityCard(
  abilityDetails: Ability,
  modifier: Modifier = Modifier,
  isPassiveAbility: Boolean = false
) {
  var expanded by remember { mutableStateOf(false) }
  Card(modifier = modifier
    .animateContentSize()
    .clip(MaterialTheme.shapes.large)
    .clickable { expanded = !expanded }
  ) {
    Column {
      Row(verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
          model = abilityDetails.url,
          contentDescription = abilityDetails.summary,
          contentScale = ContentScale.Crop,
          alignment = Alignment.Center,
          modifier = Modifier
            .animateContentSize()
            .clip(MaterialTheme.shapes.small)
            .size(if (expanded) 68.dp else 54.dp)
        )
        Row(
          horizontalArrangement = Arrangement.SpaceBetween,
          modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 0.dp)
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
      }
      if (expanded) {
        Text(
          text = abilityDetails.description.description,
          style = MaterialTheme.typography.bodyMedium,
          modifier = Modifier.padding(8.dp)
        )

        val cooldown = abilityDetails.description.cooldown
        val cost = abilityDetails.description.cost
        Row(
          horizontalArrangement = Arrangement.SpaceBetween,
          modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 8.dp)
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

        for (rankItem in abilityDetails.description.rankItems) {
          Text(
            "${rankItem.description} ${rankItem.value}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(8.dp, 4.dp)
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
          cooldown = "14s",
          cost = "60/65/70/75/80",
          description = "Achilles punches forward with the edge of his Shield, inflicting massive damage and stunning enemy targets hit by the impact. The force of his punch continues to radiate past his initial target area, dealing 75% damage to targets farther away.",
          menuItems = listOf(),
          rankItems = listOf()
        )
      )
    )
  }
}