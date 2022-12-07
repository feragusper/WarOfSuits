package com.feragusper.match.domain.mapper

import com.feragusper.match.data.entity.CardEntity
import com.feragusper.match.data.entity.MatchEntity
import com.feragusper.match.domain.model.Card
import com.feragusper.match.domain.model.Match
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
        every { matchEntity.currentRound?.turns?.second?.card } returns CardEntity(7, "spades")
        every { matchEntity.score } returns (3 to 2)
        every { matchEntity.finished } returns false

        val result = matchMapper.transform(matchEntity)

        result shouldBeEqualTo Match(
            currentRound = Match.Round(Match.Round.Turn(Card(3, "spades")) to Match.Round.Turn(Card(7, "spades"))),
            score = 3 to 2,
            finished = false
        )
    }

}