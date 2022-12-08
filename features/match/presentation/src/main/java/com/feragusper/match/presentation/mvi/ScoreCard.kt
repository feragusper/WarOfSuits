package com.feragusper.match.presentation.mvi

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Score(
    modifier: Modifier = Modifier,
    pointsPlayer: String,
    pointsOpponent: String,
    playerLabel: String,
    opponentLabel: String,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {

        Row {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = pointsPlayer,
                    style = MaterialTheme.typography.h2
                )
                Text(
                    text = playerLabel,
                    modifier = Modifier.padding(bottom = 16.dp),
                    style = MaterialTheme.typography.body2,
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = pointsOpponent,
                    style = MaterialTheme.typography.h2,
                )
                Text(
                    text = opponentLabel,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(bottom = 16.dp),
                )
            }
        }
    }
}