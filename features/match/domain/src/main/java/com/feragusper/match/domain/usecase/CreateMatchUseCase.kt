package com.feragusper.match.domain.usecase

import com.feragusper.match.data.repository.MatchRepository
import com.feragusper.match.domain.mapper.MatchMapper
import javax.inject.Inject

class CreateMatchUseCase @Inject constructor(
    private val matchRepository: MatchRepository,
    private val cardMapper: MatchMapper,
) {

    fun execute() = cardMapper.transform(matchRepository.createMatch())

}