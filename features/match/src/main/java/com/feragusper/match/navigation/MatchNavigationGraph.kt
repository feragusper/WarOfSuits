package com.feragusper.match.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.feragusper.match.navigation.MatchNavigator.Companion.START
import com.feragusper.match.navigation.MatchNavigator.Companion.ROUTE
import com.feragusper.match.presentation.MatchView

fun NavGraphBuilder.matchNavigationGraph() {
    navigation(startDestination = START, route = ROUTE) {
        composable(route = START) {
            MatchView()
        }
    }
}
