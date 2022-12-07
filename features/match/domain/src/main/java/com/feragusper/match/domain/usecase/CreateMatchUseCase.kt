package com.feragusper.match.domain.usecase

import com.feragusper.match.data.repository.MatchRepository
import javax.inject.Inject

class CreateMatchUseCase @Inject constructor(private val matchRepository: MatchRepository) {

    fun execute() {
        matchRepository.createMatch()
    }

}