package com.feragusper.warofsuits.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.feragusper.home.navigation.HomeNavigator
import com.feragusper.home.navigation.homeNavigationGraph

@Composable
fun MainNavigationGraph(startDestination: String = HomeNavigator.ROOT) {
    val navController = rememberNavController()

    NavHost(navController, startDestination) {
        homeNavigationGraph()
    }
}
