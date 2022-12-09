package com.feragusper.warofsuits.match.domain.usecase

import com.feragusper.warofsuits.match.data.entity.MatchEntity
import com.feragusper.warofsuits.match.data.repository.MatchRepository
import com.feragusper.warofsuits.match.domain.mapper.MatchMapper
import com.feragusper.warofsuits.match.domain.model.Match
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

class NextRoundUseCaseTest {

    private var repository: MatchRepository = mockk()
    private var matchEntity: MatchEntity = mockk()
    private var mapper: MatchMapper = mockk()
    private var match: Match = mockk()
    private lateinit var useCase: NextRoundUseCase

    @Before
    fun setUp() {
        useCase = NextRoundUseCase(repository, mapper)
    }

    @Test
    fun `WHEN the use case is executed THEN should return the match`() {
        every { repository.nextRound() } returns matchEntity
        every { mapper.transform(matchEntity) } returns match

        val result = useCase.execute()

        result shouldBeEqualTo match
    }

}