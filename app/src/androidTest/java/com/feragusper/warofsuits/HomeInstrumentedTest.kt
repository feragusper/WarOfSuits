package com.feragusper.warofsuits

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.feragusper.warofsuits.design.theme.WarOfSuitsTheme
import com.feragusper.warofsuits.home.presentation.mvi.HomeIntent
import com.feragusper.warofsuits.home.presentation.mvi.HomeViewState
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun quickStartShouldTriggerAction() {
        val intents = mutableListOf<HomeIntent>()

        composeTestRule.activity.setContent {
            WarOfSuitsTheme {
                HomeViewState().Compose { intents.add(it) }
            }
        }

        composeTestRule.onNodeWithText("Quick start").performClick()
        assert(intents.contains(HomeIntent.QuickMatch))
    }
}