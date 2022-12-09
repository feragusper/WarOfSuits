package com.feragusper.warofsuits.home.presentation.mvi

import com.feragusper.warofsuits.architecture.presentation.mvi.MVIIntent

sealed class HomeIntent : MVIIntent {
    object QuickMatch : HomeIntent()
}
