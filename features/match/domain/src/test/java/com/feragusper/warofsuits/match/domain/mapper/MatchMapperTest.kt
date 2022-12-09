package com.feragusper.warofsuits.match.domain.mapper

import com.feragusper.warofsuits.match.data.entity.CardEntity
import com.feragusper.warofsuits.match.data.entity.MatchEntity
import com.feragusper.warofsuits.match.domain.model.Card
import com.feragusper.warofsuits.match.domain.model.Match
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

class MatchMapperTest {

    private var matchEntity: MatchEntity = mockk()
    private lateinit var matchMapper: MatchMapper

    @Before
    fun setUp() {
        matchMapper = MatchMapper()
    }

    @Test
    fun `WHEN the mapper is invoked THEN should map the proper values`() {
        every { matchEntity.currentRound?.turns?.first?.card } returns CardEntity(3, "spades")
        every { matchEntity.currentRound?.turns?.first?.won } returns false
        every { matchEntity.currentRound?.turns?.second?.card } returns CardEntity(7, "hearts")
        every { matchEntity.currentRound?.turns?.second?.won } returns true
        every { matchEntity.score } returns (3 to 2)
        every { matchEntity.finished } returns false
        every { matchEntity.suitPriority } returns listOf("hearts", "spades")

        val result = matchMapper.transform(matchEntity)

        result shouldBeEqualTo Match(
            currentRound = Match.Round(Match.Round.Turn(Card(3, "spades"), false) to Match.Round.Turn(Card(7, "hearts"), true)),
            score = 3 to 2,
            finished = false,
            suitPriority = listOf("hearts", "spades"),
        )
    }

}