package com.feragusper.warofsuits.match.domain.usecase

import com.feragusper.warofsuits.match.data.repository.MatchRepository
import com.feragusper.warofsuits.match.domain.mapper.MatchMapper
import javax.inject.Inject

class NextRoundUseCase @Inject constructor(
    private val matchRepository: MatchRepository,
    private val cardMapper: MatchMapper,
) {

    fun execute() = cardMapper.transform(matchRepository.nextRound())

}