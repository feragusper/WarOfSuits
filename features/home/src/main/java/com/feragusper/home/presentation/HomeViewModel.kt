package com.feragusper.home.presentation

import com.feragusper.architecture.presentation.MVIViewModel
import com.feragusper.home.navigation.HomeNavigator
import com.feragusper.home.presentation.mvi.HomeIntent
import com.feragusper.home.presentation.mvi.HomeResult
import com.feragusper.home.presentation.mvi.HomeViewState
import com.feragusper.home.presentation.process.HomeProcessHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val processHolder: HomeProcessHolder
) : MVIViewModel<HomeIntent, HomeViewState, HomeResult, HomeNavigator>(initialState = HomeViewState()) {

    override lateinit var navigator: HomeNavigator

    override suspend fun transformer(intent: HomeIntent) = processHolder.processIntent(intent)

    override suspend fun reducer(previous: HomeViewState, result: HomeResult): HomeViewState = when (result) {
        HomeResult.NavigateToQuickMatch -> {
            navigator.navigateToMatch()
            previous
        }

    }
}