package com.feragusper.home.presentation.mvi

import com.feragusper.architecture.presentation.mvi.MVIResult

sealed class HomeResult : MVIResult {
    object NavigateToQuickMatch : HomeResult()
}
