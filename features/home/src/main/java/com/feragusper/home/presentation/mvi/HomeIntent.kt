package com.feragusper.home.presentation.mvi

import com.feragusper.architecture.presentation.mvi.MVIIntent

sealed class HomeIntent : MVIIntent {
    object QuickMatch : HomeIntent()
}
