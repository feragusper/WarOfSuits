package com.feragusper.warofsuits.match.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.feragusper.warofsuits.match.navigation.MatchNavigator.Companion.ROUTE
import com.feragusper.warofsuits.match.navigation.MatchNavigator.Companion.START
import com.feragusper.warofsuits.match.presentation.MatchView
import com.feragusper.warofsuits.match.presentation.MatchViewModel

fun NavGraphBuilder.matchNavigationGraph(matchNavigator: MatchNavigator) {
    navigation(startDestination = START, route = ROUTE) {
        composable(route = START) {
            val viewModel = hiltViewModel<MatchViewModel>()
            viewModel.navigator = matchNavigator
            MatchView(viewModel)
        }
    }
}
