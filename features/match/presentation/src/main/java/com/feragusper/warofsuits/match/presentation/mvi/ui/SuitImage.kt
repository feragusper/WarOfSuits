package com.feragusper.warofsuits.match.presentation.mvi.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.feragusper.design.R

@Composable
fun SuitImage(
    suit: String,
    modifier: Modifier = Modifier
) {
    val resource = when (suit) {
        "spades" -> R.drawable.ic_spades
        "hearts" -> R.drawable.ic_hearts
        "diamonds" -> R.drawable.ic_diamonds
        "clubs" -> R.drawable.ic_clubs
        else -> throw IllegalStateException("Unknown suit parameter: $suit")
    }
    Image(
        painterResource(resource),
        contentDescription = suit,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}