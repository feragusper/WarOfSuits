package com.feragusper.home.navigation

class HomeNavigator(
    val navigateToMatch: () -> Unit,
) {

    companion object {
        const val ROUTE = "home_graph"
        const val START = "menu"
    }
}