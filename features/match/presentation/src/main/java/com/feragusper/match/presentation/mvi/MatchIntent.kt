package com.feragusper.match.presentation.mvi

import com.feragusper.architecture.presentation.mvi.MVIIntent

sealed class MatchIntent : MVIIntent {
    object NewMatch : MatchIntent()
    object NextRound : MatchIntent()
    object Exit : MatchIntent()
}
