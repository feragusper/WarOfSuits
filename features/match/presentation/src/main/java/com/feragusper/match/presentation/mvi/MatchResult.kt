package com.feragusper.match.presentation.mvi

import com.feragusper.architecture.presentation.mvi.MVIResult
import com.feragusper.match.domain.model.Match

sealed class MatchResult : MVIResult {
    class MatchUpdated(val match: Match) : MatchResult()
    class MatchCreated(val match: Match) : MatchResult()
    object Failure : MatchResult()
    object NavigateUp : MatchResult()
}