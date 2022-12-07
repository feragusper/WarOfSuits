package com.feragusper.match.domain.usecase

import com.feragusper.match.data.repository.MatchRepository
import com.feragusper.match.domain.mapper.MatchMapper
import com.feragusper.match.domain.model.Match
import javax.inject.Inject

class NextRoundUseCase @Inject constructor(
    private val matchRepository: MatchRepository,
    private val cardMapper: MatchMapper,
) {

    fun execute(): Match = cardMapper.transform(matchRepository.nextRound())

}