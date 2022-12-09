package com.feragusper.match.presentation

import com.feragusper.match.domain.model.Card
import com.feragusper.match.domain.model.Match
import com.feragusper.match.domain.usecase.CreateMatchUseCase
import com.feragusper.match.domain.usecase.NextRoundUseCase
import com.feragusper.match.navigation.MatchNavigator
import com.feragusper.match.presentation.mvi.MatchIntent
import com.feragusper.match.presentation.mvi.MatchViewState
import com.feragusper.match.presentation.process.MatchProcessHolder
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
import org.amshove.kluent.shouldBeEqualTo
import org.junit.After
import org.junit.Before
import org.junit.Test

class MatchViewModelTest {

    private var navigator: MatchNavigator = mockk()
    private var match: Match = mockk()
    private var createMatchUseCase: CreateMatchUseCase = mockk()
    private var nextRoundUseCase: NextRoundUseCase = mockk()

    private lateinit var viewModel: MatchViewModel
    private lateinit var chanel: Channel<MatchIntent>
    private lateinit var state: StateFlow<MatchViewState>

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = MatchViewModel(MatchProcessHolder(createMatchUseCase, nextRoundUseCase, TestDispatcherProvider))
        viewModel.navigator = navigator
        chanel = viewModel.intents()
        state = viewModel.states()

        every { createMatchUseCase.execute() } returns match
        every { match.suitPriority } returns listOf("spades", "hearts")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `WHEN the view is opened THEN it create a match`() {
        val state = runBlocking {
            chanel.trySend(MatchIntent.NewMatch)
            state.first()
        }

        state.loading shouldBeEqualTo false
        state.error shouldBeEqualTo false
        state.suitPriority shouldBeEqualTo listOf("spades", "hearts")
    }

    @Test
    fun `WHEN the next round is requested THEN it update the state with proper values`() {
        every { nextRoundUseCase.execute() } returns match
        every { match.finished } returns false
        every { match.currentRound?.turns?.first?.card } returns Card(3, "spades")
        every { match.currentRound?.turns?.first?.won } returns false
        every { match.currentRound?.turns?.second?.card } returns Card(6, "hearts")
        every { match.currentRound?.turns?.second?.won } returns true
        every { match.score.first } returns 0
        every { match.score.second } returns 1

        val state = runBlocking {
            chanel.trySend(MatchIntent.NextRound)
            state.first()
        }

        state.loading shouldBeEqualTo false
        state.error shouldBeEqualTo false
        state.displayFinished shouldBeEqualTo false
        state.firstPlayerCard shouldBeEqualTo MatchViewState.Card(3, "spades")
        state.firstPlayerWon shouldBeEqualTo false
        state.secondPlayerCard shouldBeEqualTo MatchViewState.Card(6, "hearts")
        state.secondPlayerWon shouldBeEqualTo true
        state.firstPlayerScore shouldBeEqualTo "0"
        state.secondPlayerScore shouldBeEqualTo "1"
    }

    @Test
    fun `WHEN the exit is requested THEN it navigates up`() {
        every { navigator.navigateUp() } answers {}

        runBlocking {
            chanel.trySend(MatchIntent.Exit)
            state.first()
        }

        verify { navigator.navigateUp() }
    }
}