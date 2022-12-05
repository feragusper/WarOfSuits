package com.feragusper.home.navigation

import com.feragusper.architecture.presentation.navigation.MVINavigator

class HomeNavigator(
    val navigateToMatch: () -> Unit,
) : MVINavigator {

    companion object {
        const val ROUTE = "home_graph"
        const val START = "menu"
    }
}