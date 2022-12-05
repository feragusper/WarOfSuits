package com.feragusper.warofsuits.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.feragusper.home.navigation.HomeNavigator
import com.feragusper.home.navigation.homeNavigationGraph
import com.feragusper.match.navigation.MatchNavigator
import com.feragusper.match.navigation.matchNavigationGraph

@Composable
fun MainNavigationGraph(startDestination: String = HomeNavigator.ROUTE) {
    val navController = rememberNavController()

    val matchNavigator = MatchNavigator(navController)

    NavHost(navController, startDestination) {
        homeNavigationGraph(HomeNavigator(matchNavigator.actionNavigateToMatch))
        matchNavigationGraph()
    }
}
