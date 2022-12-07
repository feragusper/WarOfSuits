package com.feragusper.match.navigation

import androidx.navigation.NavController
import com.feragusper.architecture.presentation.navigation.MVINavigator

class MatchNavigator(navController: NavController) : MVINavigator {

    val actionNavigateToMatch: () -> Unit = { navController.navigate(ROUTE) }

    companion object {
        const val ROUTE = "match_graph"
        const val START = "match"
    }
}