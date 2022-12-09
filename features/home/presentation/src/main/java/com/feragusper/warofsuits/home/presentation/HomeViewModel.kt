package com.feragusper.warofsuits.home.presentation

import com.feragusper.warofsuits.architecture.presentation.MVIViewModel
import com.feragusper.warofsuits.home.navigation.HomeNavigator
import com.feragusper.warofsuits.home.presentation.mvi.HomeIntent
import com.feragusper.warofsuits.home.presentation.mvi.HomeResult
import com.feragusper.warofsuits.home.presentation.mvi.HomeViewState
import com.feragusper.warofsuits.home.presentation.process.HomeProcessHolder
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