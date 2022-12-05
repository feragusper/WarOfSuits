package com.feragusper.match.navigation

import androidx.navigation.NavController

class MatchNavigator(navController: NavController) {

    val actionNavigateToMatch: () -> Unit = { navController.navigate(ROUTE) }

    companion object {
        const val ROUTE = "match_graph"
        const val START = "match"
    }
}