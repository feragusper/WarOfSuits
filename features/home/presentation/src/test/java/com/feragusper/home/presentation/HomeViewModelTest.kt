package com.feragusper.home.presentation

import com.feragusper.home.navigation.HomeNavigator
import com.feragusper.home.presentation.mvi.HomeIntent
import com.feragusper.home.presentation.mvi.HomeViewState
import com.feragusper.home.presentation.process.HomeProcessHolder
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private var navigator: HomeNavigator = mockk()

    private lateinit var viewModel: HomeViewModel
    private lateinit var chanel: Channel<HomeIntent>
    private lateinit var state: StateFlow<HomeViewState>

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = HomeViewModel(HomeProcessHolder())
        viewModel.navigator = navigator
        chanel = viewModel.intents()
        state = viewModel.states()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `WHEN the quick match button is clicked THEN it should navigate to match`() {
        every { navigator.navigateToMatch() } answers {}

        runBlocking {
            chanel.trySend(HomeIntent.QuickMatch)
            state.first()
        }

        verify { navigator.navigateToMatch() }
    }
}