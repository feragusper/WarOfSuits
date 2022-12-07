package com.feragusper.match.domain.datasource

import com.feragusper.match.data.datasource.MatchProcessor
import com.feragusper.match.data.entity.CardEntity
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

class MatchProcessorTest {

    private lateinit var matchProcessor: MatchProcessor
    private val decks = listOf(
        CardEntity(2, "spades"),
        CardEntity(3, "hearts")
    ) to listOf(
        CardEntity(3, "spades"),
        CardEntity(3, "diamonds")
    )
    private val priority = listOf("spades", "diamonds", "hearts", "clubs")

    @Before
    fun setUp() {
        matchProcessor = MatchProcessor()
    }

    @Test
    fun `WHEN a new match is created THEN it should configure proper state`() {

        matchProcessor.newMatch(decks, priority)

        val result = matchProcessor.matchStateEntity

        result.currentRound shouldBeEqualTo null
        result.score shouldBeEqualTo Pair(0, 0)
        result.finished shouldBeEqualTo false
        result.suitPriority shouldBeEqualTo priority

    }

    @Test
    fun `WHEN a next round is requested THEN it should resolve result for a simple win-loose and update proper state`() {

        matchProcessor.newMatch(decks, priority)
        matchProcessor.nextRound()

        val result = matchProcessor.matchStateEntity

        result.currentRound?.turns?.first?.card?.value shouldBeEqualTo 2
        result.currentRound?.turns?.first?.card?.suit shouldBeEqualTo "spades"
        result.currentRound?.turns?.first?.won shouldBeEqualTo false
        result.currentRound?.turns?.second?.card?.value shouldBeEqualTo 3
        result.currentRound?.turns?.second?.card?.suit shouldBeEqualTo "spades"
        result.currentRound?.turns?.second?.won shouldBeEqualTo true
        result.score shouldBeEqualTo Pair(0, 1)
        result.finished shouldBeEqualTo false
    }

    @Test
    fun `WHEN a next round is requested THEN it should resolve result for a draw and update proper state`() {

        matchProcessor.newMatch(decks, priority)
        matchProcessor.nextRound()
        matchProcessor.nextRound()

        val result = matchProcessor.matchStateEntity

        result.currentRound?.turns?.first?.card?.value shouldBeEqualTo 3
        result.currentRound?.turns?.first?.card?.suit shouldBeEqualTo "hearts"
        result.currentRound?.turns?.first?.won shouldBeEqualTo true
        result.currentRound?.turns?.second?.card?.value shouldBeEqualTo 3
        result.currentRound?.turns?.second?.card?.suit shouldBeEqualTo "diamonds"
        result.currentRound?.turns?.second?.won shouldBeEqualTo false
        result.score shouldBeEqualTo Pair(1, 1)
        result.finished shouldBeEqualTo false

    }

    @Test
    fun `WHEN a next round is requested enough times THEN it should resolve finish state`() {

        matchProcessor.newMatch(decks, priority)
        matchProcessor.nextRound()
        matchProcessor.nextRound()
        matchProcessor.nextRound()

        val result = matchProcessor.matchStateEntity

        result.finished shouldBeEqualTo true

    }
}