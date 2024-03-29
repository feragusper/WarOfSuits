package com.feragusper.warofsuits.match.presentation

import com.feragusper.warofsuits.architecture.presentation.MVIViewModel
import com.feragusper.warofsuits.match.navigation.MatchNavigator
import com.feragusper.warofsuits.match.presentation.mvi.MatchIntent
import com.feragusper.warofsuits.match.presentation.mvi.MatchResult
import com.feragusper.warofsuits.match.presentation.mvi.MatchViewState
import com.feragusper.warofsuits.match.presentation.process.MatchProcessHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val processHolder: MatchProcessHolder
) : MVIViewModel<MatchIntent, MatchViewState, MatchResult, MatchNavigator>(initialState = MatchViewState(loading = true)) {

    init {
        intents().trySend(MatchIntent.NewMatch)
    }

    override lateinit var navigator: MatchNavigator

    override suspend fun transformer(intent: MatchIntent) = processHolder.processIntent(intent)

    override suspend fun reducer(previous: MatchViewState, result: MatchResult): MatchViewState = when (result) {
        is MatchResult.MatchCreated -> MatchViewState(
            loading = false,
            suitPriority = result.match.suitPriority
        )
        MatchResult.Failure -> previous.copy(error = true)
        is MatchResult.MatchUpdated -> with(result) {
            previous.copy(
                suitPriority = match.suitPriority,
                displayFinished = match.finished,
                result = if (match.score.first > match.score.second) {
                    MatchViewState.MatchResult.WIN
                } else if (match.score.first == match.score.second) {
                    MatchViewState.MatchResult.DRAW
                } else {
                    MatchViewState.MatchResult.LOOSE
                },
                secondPlayerCard = match.currentRound?.turns?.second?.card?.let { MatchViewState.Card(it.value, it.suit) },
                firstPlayerWon = match.currentRound?.turns?.first?.won,
                firstPlayerCard = match.currentRound?.turns?.first?.card?.let { MatchViewState.Card(it.value, it.suit) },
                secondPlayerWon = match.currentRound?.turns?.second?.won,
                firstPlayerScore = match.score.first.toString(),
                secondPlayerScore = match.score.second.toString(),
            )
        }
        MatchResult.NavigateUp -> {
            navigator.navigateUp()
            previous
        }
    }
}