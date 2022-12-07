package com.feragusper.match.presentation.mvi

import com.feragusper.architecture.presentation.mvi.MVIIntent

sealed class MatchIntent : MVIIntent {
    object Open : MatchIntent()
    object NextRound : MatchIntent()
}