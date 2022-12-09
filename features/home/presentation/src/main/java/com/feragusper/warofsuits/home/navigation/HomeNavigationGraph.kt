package com.feragusper.warofsuits.home.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.feragusper.warofsuits.home.navigation.HomeNavigator.Companion.ROUTE
import com.feragusper.warofsuits.home.navigation.HomeNavigator.Companion.START
import com.feragusper.warofsuits.home.presentation.HomeView
import com.feragusper.warofsuits.home.presentation.HomeViewModel

fun NavGraphBuilder.homeNavigationGraph(homeNavigator: HomeNavigator) {
    navigation(startDestination = START, route = ROUTE) {
        composable(route = START) {
            val viewModel = hiltViewModel<HomeViewModel>()
            viewModel.navigator = homeNavigator
            HomeView(viewModel)
        }
    }
}
