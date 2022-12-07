package com.feragusper.match.presentation.mvi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.feragusper.architecture.presentation.mvi.MVIViewState
import com.feragusper.match.domain.model.Card
import com.feragusper.match.presentation.R

data class MatchViewState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val displayFinished: Boolean = false,
    val firstPlayerCard: Card? = null,
    val secondPlayerCard: Card? = null,
    val firstPlayerScore: String = "0",
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
                if (displayFinished) {
                    Text(
                        text = stringResource(R.string.match_finished),
                        style = MaterialTheme.typography.h1,
                        textAlign = TextAlign.Center
                    )
                } else {
                    Text(
                        text = stringResource(R.string.match_title),
                        style = MaterialTheme.typography.h1,
                        textAlign = TextAlign.Center
                    )
                    Row {
                        Text(
                            text = "$firstPlayerScore - $secondPlayerScore",
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center
                        )
                    }
                    Row {
                        Text(
                            text = "$firstPlayerCard - $secondPlayerCard",
                            style = MaterialTheme.typography.body2,
                            textAlign = TextAlign.Center
                        )
                    }
                    Button({ intent(MatchIntent.NextRound) }) {
                        Text(
                            text = stringResource(R.string.match_button_continue),
                            style = MaterialTheme.typography.button
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeViewPreview() {
    MatchViewState().Compose {}
}
