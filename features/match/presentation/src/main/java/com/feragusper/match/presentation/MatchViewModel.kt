package com.feragusper.match.presentation

import com.feragusper.architecture.presentation.MVIViewModel
import com.feragusper.match.navigation.MatchNavigator
import com.feragusper.match.presentation.mvi.MatchIntent
import com.feragusper.match.presentation.mvi.MatchResult
import com.feragusper.match.presentation.mvi.MatchViewState
import com.feragusper.match.presentation.process.MatchProcessHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val processHolder: MatchProcessHolder
) : MVIViewModel<MatchIntent, MatchViewState, MatchResult, MatchNavigator>(initialState = MatchViewState(loading = true)) {

    init {
        intents().trySend(MatchIntent.Open)
    }

    override lateinit var navigator: MatchNavigator

    override suspend fun transformer(intent: MatchIntent) = processHolder.processIntent(intent)

    override suspend fun reducer(previous: MatchViewState, result: MatchResult): MatchViewState = when (result) {
        MatchResult.MatchCreated -> previous.copy(loading = false)
        MatchResult.Failure -> previous.copy(error = true)
        is MatchResult.MatchUpdated -> with(result) {
            previous.copy(
                displayFinished = match.finished,
                firstPlayerCard = match.currentRound?.turns?.first?.card,
                secondPlayerCard = match.currentRound?.turns?.second?.card,
                firstPlayerScore = match.score.first.toString(),
                secondPlayerScore = match.score.second.toString(),
            )
        }
    }
}