package com.feragusper.warofsuits.home.presentation.mvi

import com.feragusper.warofsuits.architecture.presentation.mvi.MVIResult

sealed class HomeResult : MVIResult {
    object NavigateToQuickMatch : HomeResult()
}
