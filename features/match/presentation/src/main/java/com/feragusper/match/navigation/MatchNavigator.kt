package com.feragusper.match.navigation

import androidx.navigation.NavController
import com.feragusper.architecture.presentation.navigation.MVINavigator

class MatchNavigator(navController: NavController) : MVINavigator {

    val navigateToMatch: () -> Unit = { navController.navigate(ROUTE) }
    val navigateUp: () -> Unit = { navController.navigateUp() }

    companion object {
        const val ROUTE = "match_graph"
        const val START = "match"
    }
}