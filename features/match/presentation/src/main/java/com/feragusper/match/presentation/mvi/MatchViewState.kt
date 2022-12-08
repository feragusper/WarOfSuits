package com.feragusper.match.presentation.mvi

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feragusper.architecture.presentation.mvi.MVIViewState
import com.feragusper.design.theme.WarOfSuitsTheme
import com.feragusper.design.ui.PrimaryButton
import com.feragusper.match.domain.model.Card
import com.feragusper.match.presentation.R

data class MatchViewState(
    val suitPriority: List<String> = listOf(),
    val loading: Boolean = false,
    val error: Boolean = false,
    val displayFinished: Boolean = false,
    val firstPlayerCard: Card? = null,
    val firstPlayerWon: Boolean? = null,
    val secondPlayerCard: Card? = null,
    val firstPlayerScore: String = "0",
    val secondPlayerWon: Boolean? = null,
    val secondPlayerScore: String = "0"
) : MVIViewState<MatchIntent> {

    @Composable
    override fun Compose(intent: (MatchIntent) -> Unit) {
        val scaffoldState = rememberScaffoldState()
        Scaffold(
            scaffoldState = scaffoldState,
        ) {
            Column(
                modifier = Modifier.padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Score(
                    modifier = Modifier.padding(top = 30.dp),
                    pointsPlayer = firstPlayerScore,
                    pointsOpponent = secondPlayerScore,
                    playerLabel = stringResource(R.string.match_score_label_you),
                    opponentLabel = stringResource(R.string.match_score_label_opponent),
                )
                if (displayFinished) {
                    EndSlates(intent)
                } else {
                    PlayingMatch(intent)
                }
            }
        }
    }

    @Composable
    private fun EndSlates(intent: (MatchIntent) -> Unit) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 48.dp),
                text = stringResource(
                    if (firstPlayerWon == true)
                        R.string.match_you_won else R.string.match_you_loose
                ),
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.secondary,
                textAlign = TextAlign.Center
            )
            PrimaryButton(
                modifier = Modifier.padding(top = 32.dp),
                onClick = { intent(MatchIntent.NewMatch) },
                text = stringResource(R.string.match_button_restart)
            )
            PrimaryButton(
                modifier = Modifier.padding(top = 16.dp),
                onClick = { intent(MatchIntent.Exit) },
                text = stringResource(R.string.match_button_exit)
            )
        }
    }

    @Composable
    private fun PlayingMatch(intent: (MatchIntent) -> Unit) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SuitPriority(Modifier.padding(top = 16.dp))
            PlayingRound(
                Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
            )
            PrimaryButton(
                modifier = Modifier.padding(vertical = 48.dp),
                text = stringResource(R.string.match_button_continue),
                onClick = { intent(MatchIntent.NextRound) }
            )
        }
    }

    @Composable
    private fun SuitPriority(modifier: Modifier) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = stringResource(id = R.string.match_suit_priority_title))
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 8.dp),
                ) {
                    suitPriority.forEach { suit ->
                        SuitIcon(suit)
                    }
                }
            }
        }
    }

    @Composable
    private fun PlayingRound(modifier: Modifier = Modifier) {
        Row(modifier = modifier) {
            Box(Modifier.weight(1f)) {
                WinningBox(firstPlayerWon == true)
                PlayingCard(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .padding(vertical = 10.dp),
                    revealed = firstPlayerCard != null,
                    value = firstPlayerCard?.value,
                    suit = firstPlayerCard?.suit
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                WinningBox(secondPlayerWon == true)
                PlayingCard(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .padding(vertical = 10.dp),
                    revealed = secondPlayerCard != null,
                    value = secondPlayerCard?.value,
                    suit = secondPlayerCard?.suit
                )
            }
        }
    }

    @Composable
    private fun WinningBox(visible: Boolean) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(0.66f)
                    .background(MaterialTheme.colors.primary)
                    .clip(RoundedCornerShape(10.dp)),
            )
        }
    }
}

@Preview
@Composable
private fun MatchOngoingViewPreview() {
    WarOfSuitsTheme {
        MatchViewState(
            firstPlayerCard = Card(2, "spades"),
            firstPlayerWon = true,
            secondPlayerCard = Card(3, "hearts"),
            firstPlayerScore = "3",
            secondPlayerWon = false,
            secondPlayerScore = "2",
            suitPriority = listOf("hearts", "spades", "diamonds", "clubs")
        ).Compose {}
    }
}

@Preview
@Composable
private fun MatchFinishedViewPreview() {
    WarOfSuitsTheme {
        MatchViewState(
            firstPlayerWon = true,
            firstPlayerScore = "3",
            secondPlayerScore = "2",
            displayFinished = true
        ).Compose {}
    }
}
