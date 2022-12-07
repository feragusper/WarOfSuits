package com.feragusper.match.data.repository

import com.feragusper.match.data.datasource.MatchDataSource
import javax.inject.Inject

class MatchRepository @Inject constructor(private val matchDataSource: MatchDataSource) {

    fun createMatch() = matchDataSource.createMatch()

    fun nextRound() = matchDataSource.nextRound()

}