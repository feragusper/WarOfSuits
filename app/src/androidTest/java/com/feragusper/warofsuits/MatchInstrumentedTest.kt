package com.feragusper.warofsuits

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.feragusper.warofsuits.design.theme.WarOfSuitsTheme
import com.feragusper.warofsuits.match.presentation.mvi.MatchIntent
import com.feragusper.warofsuits.match.presentation.mvi.MatchViewState
import org.junit.Rule
import org.junit.Test

class MatchInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun continueShouldTriggerAction() {
        val intents = mutableListOf<MatchIntent>()

        composeTestRule.activity.setContent {
            WarOfSuitsTheme {
                MatchViewState().Compose { intents.add(it) }
            }
        }

        composeTestRule.onNodeWithText("Continue").performClick()

        assert(intents.contains(MatchIntent.NextRound))
    }

    @Test
    fun exitShouldTriggerAction() {
        val intents = mutableListOf<MatchIntent>()

        composeTestRule.activity.setContent {
            WarOfSuitsTheme {
                MatchViewState(
                    displayFinished = true,
                    result = MatchViewState.MatchResult.WIN
                ).Compose { intents.add(it) }
            }
        }

        composeTestRule.onNodeWithText("Exit").performClick()

        assert(intents.contains(MatchIntent.Exit))
    }

    @Test
    fun restartShouldTriggerAction() {
        val intents = mutableListOf<MatchIntent>()

        composeTestRule.activity.setContent {
            WarOfSuitsTheme {
                MatchViewState(
                    displayFinished = true,
                    result = MatchViewState.MatchResult.LOOSE
                ).Compose { intents.add(it) }
            }
        }

        composeTestRule.onNodeWithText("Restart").performClick()

        assert(intents.contains(MatchIntent.NewMatch))
    }
}