package com.feragusper.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.feragusper.home.navigation.HomeNavigator.Companion.START
import com.feragusper.home.navigation.HomeNavigator.Companion.ROUTE
import com.feragusper.home.presentation.HomeView

fun NavGraphBuilder.homeNavigationGraph(homeNavigator: HomeNavigator) {
    navigation(startDestination = START, route = ROUTE) {
        composable(route = START) {
            HomeView(homeNavigator.navigateToMatch)
        }
    }
}
