package com.feragusper.match.domain.datasource

import com.feragusper.match.data.datasource.InMemoryDataSource
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeIn
import org.junit.Before
import org.junit.Test

class InMemoryDataSourceTest {

    private lateinit var dataSource: InMemoryDataSource

    @Before
    fun setUp() {
        dataSource = InMemoryDataSource()
    }

    @Test
    fun `WHEN the match is started and a first round is requested THEN it should return proper match state`() {
        dataSource.createMatch()
        val result = dataSource.nextRound()

        result.currentRound?.turns?.first?.card?.value shouldBeIn (0..13)
        result.currentRound?.turns?.first?.card?.suit shouldBeIn listOf("spades", "hearts", "diamonds", "clubs")
        result.score shouldBeEqualTo Pair(0, 0)
        result.finished shouldBeEqualTo false

    }
}