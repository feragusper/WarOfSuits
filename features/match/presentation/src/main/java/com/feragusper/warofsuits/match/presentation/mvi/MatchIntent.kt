package com.feragusper.warofsuits.match.presentation.mvi

import com.feragusper.warofsuits.architecture.presentation.mvi.MVIIntent

sealed class MatchIntent : MVIIntent {
    object NewMatch : MatchIntent()
    object NextRound : MatchIntent()
    object Exit : MatchIntent()
}
