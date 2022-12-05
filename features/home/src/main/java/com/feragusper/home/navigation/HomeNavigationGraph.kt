package com.feragusper.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.feragusper.home.navigation.HomeNavigator.Companion.ROOT
import com.feragusper.home.navigation.HomeNavigator.Companion.HOME
import com.feragusper.home.presentation.HomeView

fun NavGraphBuilder.homeNavigationGraph() {
    navigation(startDestination = HOME, route = ROOT) {
        composable(route = HOME) {
            HomeView()
        }
    }
}
