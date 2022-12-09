package com.feragusper.warofsuits.home.presentation.process

import com.feragusper.warofsuits.architecture.presentation.process.MVIProcessHolder
import com.feragusper.warofsuits.home.presentation.mvi.HomeIntent
import com.feragusper.warofsuits.home.presentation.mvi.HomeResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class HomeProcessHolder @Inject constructor() : MVIProcessHolder<HomeIntent, HomeResult> {

    override fun processIntent(intent: HomeIntent): Flow<HomeResult> =
        when (intent) {
            is HomeIntent.QuickMatch -> processQuickMatch()
        }

    private fun processQuickMatch(): Flow<HomeResult> =
        flowOf(HomeResult.NavigateToQuickMatch)

}
